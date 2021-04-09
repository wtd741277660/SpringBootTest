package com.wtd.gephi;

import java.awt.Color;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.gephi.graph.api.Graph;
import org.gephi.graph.api.GraphModel;
import org.gephi.graph.api.Node;
import org.gephi.io.exporter.api.ExportController;
import org.gephi.io.importer.api.Container;
import org.gephi.io.importer.api.ContainerLoader;
import org.gephi.io.importer.api.EdgeDefault;
import org.gephi.io.importer.api.ImportController;
import org.gephi.io.importer.impl.NodeDraftImpl;
import org.gephi.io.processor.plugin.DefaultProcessor;
import org.gephi.layout.plugin.forceAtlas.ForceAtlasLayout;
import org.gephi.layout.plugin.forceAtlas2.ForceAtlas2;
import org.gephi.layout.plugin.forceAtlas2.ForceAtlas2Builder;
import org.gephi.preview.api.PreviewController;
import org.gephi.preview.api.PreviewModel;
import org.gephi.preview.api.PreviewProperty;
import org.gephi.preview.types.DependantOriginalColor;
import org.gephi.project.api.ProjectController;
import org.gephi.project.api.Workspace;
import org.openide.util.Lookup;

import com.jb.f1.dnaecp.algorithm.powerLoss.TopoEntity;
import com.jb.util.GUID;

/*
 * 解析线、杆塔的关系及成图（没有坐标，需要自动布局）
 */
@SuppressWarnings(value={"unchecked", "rawtypes"})
public class TopoPosition {

	private Hashtable<String, Map> lineList = new Hashtable<String, Map>();// 架空线
	private Hashtable<String, Map> jointLineList = new Hashtable<String, Map>();// 连接线
	private Hashtable<String, Map> poleList = new Hashtable<String, Map>();
	private Hashtable<String, Map> transformerList = new Hashtable<String, Map>();
	private Hashtable<String, Map> cabelJointList = new Hashtable<String, Map>();// 电缆中间接头
	private Hashtable<String, Map> poleEquipmentList = new Hashtable<String, Map>();// 柱上设备

	private Map startOut;// 进出线

	private String outSymbolId = "9325F5FE-43EA-4CEB-A6CA-3D9EC2B3EDF1";// 进出线
	private String poleSymbolId = "C6E3ABC6-AEA0-0001-36BE-4A5E164D1FB4";// 杆塔
	private String aclineSymbolId = "23CB125A-C640-4A96-9803-8728EA52A520";// 架空线
	private String cabelLineSymbolId = "C667CCD3-DD80-0001-1F16-D710138E1002";// 电缆
	private String jointLineSymbolId = "68339D1B-8FDC-4DB4-8D25-401C71437F85";// 连接线
	private String cabelJointSymbolId = "C70DE9F3-D3F0-0001-D846-DEE04C501202";// 电缆中间接头
	private String cabelTerminalSymbolId = "C70DEA0E-2740-0001-6A53-E62E68705740";// 电缆终端
	private String loadSymbolId = "3bd4fee2-78c6-11e7-9c88-f01faf10ac69-00001";// 等值负荷（柱上设备）
	private String transformerSymbolId = "C6E2B304-8580-0001-A818-96A05C309030";// 公用变压器（柱上设备）
	private String transformerSymbolId2 = "C6E2B5C3-E140-0001-F46A-188044E01E8C";// 专用变压器（柱上设备）



	private int transformerSize = 20;
	private int cabelTerminalSize = 20;
	private int startoutSize = 20;
	private int drqSize = 20;
	private int dkqSize = 20;
	private int loadSize = 20;

	private Hashtable<String, int[]> positionList = new Hashtable<String, int[]>();
	private int poleStep = 100;// 杆塔的间隔
	private int transStep = 20;
	private ArrayList<String> tempFidList = new ArrayList<String>();// 临时存放fid
	private ArrayList<String> tempXYList = new ArrayList<String>();// 临时存放坐标

	// gephi算法相关的变量
	public Graph gehpiGraph = null;
	public ArrayList<String> cNodeList = new ArrayList<String>();// 连接节点
	public Hashtable<String, ArrayList<String>> startendLines = new Hashtable<String, ArrayList<String>>();
	private Hashtable<String, Map> cNodePositionUsed = null;// 存放节点上下左右位置是否被占用的信息
	private Hashtable<String, Integer> nodePosition = new Hashtable<String, Integer>();
	private ArrayList<String> flagNodeList = new ArrayList<String>();

	public TopoPosition(List<TopoEntity> topoList) {
		this.putList(topoList);
	}
	
	public void putList(List<TopoEntity> topoList) {
		for(TopoEntity topo:topoList) {
			String sblx = topo.getSblx();

			Map<String, String> obj=new HashMap<String,String>();
			obj.put("mrid", topo.getSbbm());
			obj.put("name", topo.getSbmc());
			obj.put("fid", topo.getGuid());
			if(topo.getParentId() == null) {
				obj.put("stype", outSymbolId);
				obj.put("type", "point");

				if(topo.getChildren() != null && topo.getChildren().size() > 0) {
					obj.put("zddz", topo.getChildren().get(0).getSbbm());
				}
				startOut = obj;
				continue;
			}

			/*if(UtilType_GH.byqTwoCode.equals(sblx)) {//双卷变压器
				obj.put("stype", transformerSymbolId);
				obj.put("type", "point");

				obj.put("ssgt", PlanningUtil.isNull(topo.getParentId())?"":topo.getParentId());
				obj.put("stype", transformerSymbolId);
				obj.put("type", "point");
				poleEquipmentList.put(topo.getSbbm(), obj);
				transformerList.put(topo.getSbbm(), obj);
			}else */if(UtilType_GH.znDlqCode.equals(sblx)) {//站内断路器
				obj.put("stype", zndlqSymbolId);
				obj.put("type", "point");
				poleList.put(topo.getSbbm(), obj);
			}else if(UtilType_GH.zsDlqCode.equals(sblx)) {//柱上断路器
				obj.put("stype", zsdlqSymbolId);
				obj.put("type", "point");
				poleList.put(topo.getSbbm(), obj);
			}else if(UtilType_GH.loadCode.equals(sblx)) {//等值负荷
				obj.put("stype", loadSymbolId);
				obj.put("type", "point");
				obj.put("ssgt", PlanningUtil.isNull(topo.getParentId())?"":topo.getParentId());
				poleEquipmentList.put(topo.getSbbm(), obj);
			}else if(UtilType_GH.fdDlCode.equals(sblx)) {//电缆
				obj.put("stype", cabelLineSymbolId);
				obj.put("type", "line");
				obj.put("qddz", topo.getParentId());
				if(topo.getChildren() != null && topo.getChildren().size() > 0) {
					obj.put("zddz", topo.getChildren().get(0).getSbbm());
				}
				lineList.put(topo.getSbbm(), obj);
			}else if(UtilType_GH.fdJkCode.equals(sblx) || UtilType_GH.fdLineType.equals(sblx)) {//架空线,分段线路
				obj.put("stype", aclineSymbolId);
				obj.put("type", "line");
				obj.put("qddz", topo.getParentId());
				if(topo.getChildren() != null && topo.getChildren().size() > 0) {
					obj.put("zddz", topo.getChildren().get(0).getSbbm());
				}
				lineList.put(topo.getSbbm(), obj);
			}else if(UtilType_GH.towerCode.equals(sblx)) {//杆塔
				obj.put("stype", poleSymbolId);
				obj.put("type", "point");
				poleList.put(topo.getSbbm(), obj);
			}else if(UtilType_GH.dlzdCode.equals(sblx)) {//电缆终端
				obj.put("stype", cabelTerminalSymbolId);
				obj.put("type", "point");
				poleList.put(topo.getSbbm(), obj);
			}else if(UtilType_GH.dljtCode.equals(sblx)) {//电缆接头
				obj.put("stype", cabelJointSymbolId);
				obj.put("type", "point");
				cabelJointList.put(topo.getSbbm(),obj);
			}else if(UtilType_GH.byqZsCode.equals(sblx)) {//柱上变压器
				obj.put("ssgt", PlanningUtil.isNull(topo.getParentId())?"":topo.getParentId());
				obj.put("stype", transformerSymbolId);
				obj.put("type", "point");
				poleEquipmentList.put(topo.getSbbm(), obj);
				transformerList.put(topo.getSbbm(), obj);
			}
		}
	}

	/************************
	 * 用gephi计算
	 *************************************************/
	public void analyseTopo() {

		for (String key : lineList.keySet()) {
			Map _lineObject = lineList.get(key);
			String id = key;
			String nodeid1 = _lineObject.get("qddz").toString();
			String nodeid2 = _lineObject.get("zddz").toString();
			if (!cNodeList.contains(nodeid1)) {
				cNodeList.add(nodeid1);
			}
			if (!cNodeList.contains(nodeid2)) {
				cNodeList.add(nodeid2);
			}
			String node12 = nodeid1 + "@" + nodeid2;
			ArrayList<String> keyList = new ArrayList<String>();
			if (startendLines.containsKey(node12)) {
				keyList = startendLines.get(node12);
			}
			keyList.add(id);
			startendLines.put(node12, keyList);
		}
		gephiFeeder();

	}

	public void gephiFeeder() {
		try {
			// 判断系统，使用不同路径格式
			String os = System.getProperty("os.name");
			String dirPath = "";
			if (os.toLowerCase().startsWith("win")) {
				dirPath = com.jb.config.util.PlatformConfigUtil.getString("GIS_EXPORT_FILE_PATH_WIN");
			} else {
				dirPath = com.jb.config.util.PlatformConfigUtil.getString("GIS_EXPORT_FILE_PATH_LINUX");
			}

			String dotFilePath = dirPath + "/layout_feeder.dot";
			FileWriter writer = new FileWriter(new File(dotFilePath));
			BufferedWriter bw = new BufferedWriter(writer);
			int size = cNodeList.size();
			cNodePositionUsed = new Hashtable<String, Map>();
			bw.write("graph graphname{");
			for (int i = 0; i < size; i++) {
				String nodeid1 = cNodeList.get(i);
				Map usedMap = new HashMap();
				usedMap.put("pos0", false);
				usedMap.put("pos1", false);
				usedMap.put("pos2", false);
				usedMap.put("pos3", false);
				cNodePositionUsed.put(nodeid1, usedMap);
				for (int j = 0; j < cNodeList.size(); j++) {
					String nodeid2 = cNodeList.get(j);
					String key = nodeid1 + "@" + nodeid2;
					if (startendLines.containsKey(key)) {
						bw.newLine();
						bw.write(nodeid1 + "--" + nodeid2 + ";");
					}
				}
			}
			bw.newLine();
			bw.write("};");
			bw.close();
			writer.close();

			gephiFeeder(5, dotFilePath);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void gephiFeeder(int forceSeconds, String dotFilePath) {// 调用Gephi中的布局算法
		ProjectController pc = Lookup.getDefault().lookup(ProjectController.class);
		try {
			pc.newProject();
		} catch (Exception ex) {
			System.out.println("gephi error");
		}

		Workspace workspace = pc.getCurrentWorkspace();
		ImportController importController = Lookup.getDefault().lookup(ImportController.class);
		Container container = null;
		ContainerLoader containerLoader = null;
		File file;
		try {
			file = new File(dotFilePath);
			container = importController.importFile(file);
			containerLoader = container.getLoader();
			containerLoader.setEdgeDefault(EdgeDefault.UNDIRECTED);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		importController.process(container, new DefaultProcessor(), workspace);
		GraphModel graphModel = Lookup.getDefault().lookup(org.gephi.graph.api.GraphController.class).getModel();
		PreviewController previewController = Lookup.getDefault().lookup(PreviewController.class);
		PreviewModel previewModel = previewController.getModel();
		previewModel.getProperties().putValue(PreviewProperty.NODE_LABEL_COLOR, new DependantOriginalColor(Color.WHITE));
		previewModel.getProperties().putValue(PreviewProperty.EDGE_CURVED, Boolean.FALSE);
		// previewModel.getProperties().putValue(PreviewProperty.EDGE_RADIUS, 10f);
		previewModel.getProperties().putValue(PreviewProperty.BACKGROUND_COLOR, Color.BLACK);
		ExportController ec = Lookup.getDefault().lookup(ExportController.class);

		gehpiGraph = graphModel.getGraph();

		NodeDraftImpl firstNode = null;
		// 布局起始的设备
		String startfid = startOut.get("zddz").toString();
		firstNode = (NodeDraftImpl) containerLoader.getNode(startfid);
		ForceAtlas2Builder layout_fa2_builder = new ForceAtlas2Builder();
		ForceAtlas2 secondLayout = new ForceAtlas2(layout_fa2_builder);
		secondLayout = new ForceAtlas2(layout_fa2_builder);
		secondLayout.setGraphModel(graphModel);
		secondLayout.initAlgo();
		secondLayout.resetPropertiesValues();
		secondLayout.setThreadsCount(3);
		secondLayout.setJitterTolerance(1.0);
		secondLayout.setGravity(1.0);
		secondLayout.setScalingRatio(5.0);
		for (int i = 0; i < 2000; i++) {
			float xmin = firstNode.getX();
			float ymin = firstNode.getY();
			float ymax = firstNode.getY();
			for (int j = 0; j < cNodeList.size(); j++) {
				String _id = cNodeList.get(j);
				NodeDraftImpl _node = (NodeDraftImpl) containerLoader.getNode(_id);
				if (xmin > _node.getX()) {
					xmin = _node.getX();
				}
				if (ymin > _node.getY()) {
					ymin = _node.getY();
				}
				if (ymax < _node.getY()) {
					ymax = _node.getY();
				}
			}
			firstNode.setX(xmin);
			firstNode.setY((ymin + ymax) / 2);
			secondLayout.goAlgo();
		}
		secondLayout.endAlgo();

		ForceAtlasLayout thirdLayout = new ForceAtlasLayout(null);
		thirdLayout.setGraphModel(graphModel);
		thirdLayout.initAlgo();
		thirdLayout.resetPropertiesValues();
		thirdLayout.setAdjustSizes(true);
		thirdLayout.setRepulsionStrength((double) 800);
		thirdLayout.setAttractionStrength((double) 10);
		thirdLayout.setOutboundAttractionDistribution(true);
		thirdLayout.setGravity((double) 30);
		thirdLayout.setMaxDisplacement((double) 10);
		for (int i = 0; i < 2000; i++) {
			float xmin = firstNode.getX();
			float ymin = firstNode.getY();
			float ymax = firstNode.getY();
			for (int j = 0; j < cNodeList.size(); j++) {
				String _id = cNodeList.get(j);
				NodeDraftImpl _node = (NodeDraftImpl) containerLoader.getNode(_id);
				if (xmin > _node.getX()) {
					xmin = _node.getX();
				}
				if (ymin > _node.getY()) {
					ymin = _node.getY();
				}
				if (ymax < _node.getY()) {
					ymax = _node.getY();
				}
			}
			firstNode.setX(xmin);
			firstNode.setY((ymin + ymax) / 2);
			thirdLayout.goAlgo();
		}
		thirdLayout.endAlgo();
		Node _firstNode = gehpiGraph.getNode(firstNode.getId());
		_firstNode.getNodeData().setFixed(true);
		fixFeederNextNode(gehpiGraph, _firstNode);
		setNodeFixedFalse();
		_firstNode.getNodeData().setFixed(true);
		vhFeederNextNode(gehpiGraph, _firstNode);
		setNodeFixedFalse();
	}

	public void setNodeFixedFalse() {
		for (int i = 0; i < cNodeList.size(); i++) {
			String _id = cNodeList.get(i);
			Node _node = gehpiGraph.getNode(_id);
			_node.getNodeData().setFixed(false);
		}
	}

	public void fixFeederNextNode(Graph graph, Node curNode) {
		Iterator<Node> nextNodes = graph.getNeighbors(curNode).iterator();

		while (nextNodes.hasNext()) {
			Node nextNode = nextNodes.next();
			if (!nextNode.getNodeData().isFixed()) {
				setFeederNextNodePosition(curNode, nextNode);
				fixFeederNextNode(graph, nextNode);
			}
		}

	}

	public void fixFeederNextNodeGD(Graph graph, Node curNode) {
		Iterator<Node> nextNodes = graph.getNeighbors(curNode).iterator();
		ArrayList<Node> nodes = new ArrayList<Node>();
		while (nextNodes.hasNext()) {
			Node nextNode = nextNodes.next();
			if (!nextNode.getNodeData().isFixed()) {
				setFeederNextNodePosition(curNode, nextNode);
				nodes.add(nextNode);
			}
		}
		for (int i = 0; i < nodes.size(); i++) {
			fixFeederNextNodeGD(graph, nodes.get(i));
		}
	}

	public void setFeederNextNodePosition(Node curNode, Node nextNode) {
		nextNode.getNodeData().setFixed(true);
		float dx = nextNode.getNodeData().x() - curNode.getNodeData().x();
		float dy = nextNode.getNodeData().y() - curNode.getNodeData().y();
		String curNodeId = curNode.getAttributes().getValue(0).toString();
		String nextNodeId = nextNode.getAttributes().getValue(0).toString();
		boolean pos0Used = (Boolean) cNodePositionUsed.get(curNodeId).get("pos0");
		boolean pos1Used = (Boolean) cNodePositionUsed.get(curNodeId).get("pos1");
		boolean pos2Used = (Boolean) cNodePositionUsed.get(curNodeId).get("pos2");
		boolean pos3Used = (Boolean) cNodePositionUsed.get(curNodeId).get("pos3");
		boolean flag = false;
		int pos = -1;
		double arc = (180 * Math.atan(dy / dx)) / Math.PI;
		if (arc < 0) {
			arc = arc + 360;
		}
		if (Math.abs(dx) < Math.abs(dy)) {
			if (dy < 0 && pos3Used == false) {
				pos = 3;
				nodePosition.put(nextNodeId, 3);
				cNodePositionUsed.get(curNodeId).put("pos3", true);
				cNodePositionUsed.get(nextNodeId).put("pos1", true);
			} else if (dy > 0 && pos1Used == false) {
				pos = 1;
				nodePosition.put(nextNodeId, 1);
				cNodePositionUsed.get(curNodeId).put("pos1", true);
				cNodePositionUsed.get(nextNodeId).put("pos3", true);
			} else {
				if (dx > 0 && pos0Used == false) {
					pos = 0;
					nodePosition.put(nextNodeId, 0);
					cNodePositionUsed.get(curNodeId).put("pos0", true);
					cNodePositionUsed.get(nextNodeId).put("pos2", true);
				} else if (dx < 0 && pos2Used == false) {
					pos = 2;
					nodePosition.put(nextNodeId, 2);
					cNodePositionUsed.get(curNodeId).put("pos2", true);
					cNodePositionUsed.get(nextNodeId).put("pos0", true);
				} else {
					if (pos0Used == false) {
						pos = 0;
						nodePosition.put(nextNodeId, 0);
						cNodePositionUsed.get(curNodeId).put("pos0", true);
						cNodePositionUsed.get(nextNodeId).put("pos2", true);
					} else if (pos2Used == false) {
						pos = 2;
						nodePosition.put(nextNodeId, 2);
						cNodePositionUsed.get(curNodeId).put("pos2", true);
						cNodePositionUsed.get(nextNodeId).put("pos0", true);
					} else if (pos1Used == false) {
						pos = 1;
						nodePosition.put(nextNodeId, 1);
						cNodePositionUsed.get(curNodeId).put("pos1", true);
						cNodePositionUsed.get(nextNodeId).put("pos3", true);
					} else if (pos3Used == false) {
						pos = 3;
						nodePosition.put(nextNodeId, 3);
						cNodePositionUsed.get(curNodeId).put("pos3", true);
						cNodePositionUsed.get(nextNodeId).put("pos1", true);
					}
				}
			}
		} else {
			if (dx > 0 && pos0Used == false) {
				pos = 0;
				nodePosition.put(nextNodeId, 0);
				cNodePositionUsed.get(curNodeId).put("pos0", true);
				cNodePositionUsed.get(nextNodeId).put("pos2", true);
			} else if (dx < 0 && pos2Used == false) {
				pos = 2;
				nodePosition.put(nextNodeId, 2);
				cNodePositionUsed.get(curNodeId).put("pos2", true);
				cNodePositionUsed.get(nextNodeId).put("pos0", true);
			} else {
				if (dy > 0 && pos1Used == false) {
					pos = 1;
					nodePosition.put(nextNodeId, 1);
					cNodePositionUsed.get(curNodeId).put("pos1", true);
					cNodePositionUsed.get(nextNodeId).put("pos3", true);
				} else if (dy < 0 && pos3Used == false) {
					pos = 3;
					nodePosition.put(nextNodeId, 3);
					cNodePositionUsed.get(curNodeId).put("pos3", true);
					cNodePositionUsed.get(nextNodeId).put("pos1", true);
				} else {
					if (pos1Used == false) {
						pos = 1;
						nodePosition.put(nextNodeId, 1);
						cNodePositionUsed.get(curNodeId).put("pos1", true);
						cNodePositionUsed.get(nextNodeId).put("pos3", true);
					} else if (pos3Used == false) {
						pos = 3;
						nodePosition.put(nextNodeId, 3);
						cNodePositionUsed.get(curNodeId).put("pos3", true);
						cNodePositionUsed.get(nextNodeId).put("pos1", true);
					} else if (pos2Used == false) {
						pos = 2;
						nodePosition.put(nextNodeId, 2);
						cNodePositionUsed.get(curNodeId).put("pos2", true);
						cNodePositionUsed.get(nextNodeId).put("pos0", true);
					} else if (pos0Used == false) {
						pos = 0;
						nodePosition.put(nextNodeId, 0);
						cNodePositionUsed.get(curNodeId).put("pos0", true);
						cNodePositionUsed.get(nextNodeId).put("pos2", true);
					}
				}
			}
		}
		// System.out.println(nextNodeId+"@"+curNodeId+"@"+pos);
	}

	public void vhFeederNextNode(Graph graph, Node curNode) {
		Iterator<Node> nextNodes = graph.getNeighbors(curNode).iterator();
		while (nextNodes.hasNext()) {
			Node nextNode = nextNodes.next();

			if (!nextNode.getNodeData().isFixed()) {
				setFeederNextNodeVH(curNode, nextNode);
				vhFeederNextNode(graph, nextNode);
			}
		}
	}

	public void setFeederNextNodeVH(Node curNode, Node nextNode) {
		nextNode.getNodeData().setFixed(true);
		float dx = Math.abs(nextNode.getNodeData().x() - curNode.getNodeData().x());
		float dy = Math.abs(nextNode.getNodeData().y() - curNode.getNodeData().y());
		try {
			int nextPosition = nodePosition.get(nextNode.getAttributes().getValue(0).toString());
			if (!nodePosition.containsKey(nextNode.getAttributes().getValue(0).toString())) {
				return;
			}
			if (nextPosition == 0) {
				nextNode.getNodeData().setY(curNode.getNodeData().y());
			} else if (nextPosition == 2) {
				nextNode.getNodeData().setY(curNode.getNodeData().y());
				// nextNode.getNodeData().setX(2*curNode.getNodeData().x()-nextNode.getNodeData().x());
			} else if (nextPosition == 1) {
				nextNode.getNodeData().setX(curNode.getNodeData().x());
			} else if (nextPosition == 3) {
				nextNode.getNodeData().setX(curNode.getNodeData().x());
				// nextNode.getNodeData().setY(2*curNode.getNodeData().y()-nextNode.getNodeData().y());
			}
		} catch (Exception ex) {
			Node node1 = curNode;
			Node node2 = nextNode;
		}

	}

	public void modifyFeederNextNode(Graph graph, Node curNode) {
		flagNodeList.add(curNode.getAttributes().getValue(0).toString());
		Iterator<Node> nextNodes = graph.getNeighbors(curNode).iterator();
		while (nextNodes.hasNext()) {
			Node nextNode = nextNodes.next();
			if (!flagNodeList.contains(nextNode.getAttributes().getValue(0).toString())) {
				flagNodeList.add(nextNode.getAttributes().getValue(0).toString());
				modifyNextNodePosition(graph, curNode, nextNode);
				modifyFeederNextNode(graph, nextNode);
			}
		}
	}

	public void modifyNextNodePosition(Graph graph, Node curNode, Node nextNode) {
		setNodeFixedFalse();
		float edgeLength = this.poleStep;// getEdgeLength(curNode, nextNode);
		float dx = nextNode.getNodeData().x() - curNode.getNodeData().x();
		float dy = nextNode.getNodeData().y() - curNode.getNodeData().y();
		float offsetx = 0, offsety = 0;
		if (!nodePosition.containsKey(nextNode.getAttributes().getValue(0).toString())) {
			return;
		}
		int nextPosition = nodePosition.get(nextNode.getAttributes().getValue(0).toString());
		if (dx == 0 && edgeLength != 0) {
			if (nextPosition == 1) {
				offsety = (curNode.getNodeData().y() + edgeLength) - nextNode.getNodeData().y();
			} else if (nextPosition == 3) {
				offsety = (curNode.getNodeData().y() - edgeLength) - nextNode.getNodeData().y();
			}
		} else if (dy == 0 && edgeLength != 0) {
			if (nextPosition == 0) {
				offsetx = (curNode.getNodeData().x() + edgeLength) - nextNode.getNodeData().x();
			} else if (nextPosition == 2) {
				offsetx = (curNode.getNodeData().x() - edgeLength) - nextNode.getNodeData().x();
			}
		}
		offsetNodeList(graph, nextNode, offsetx, offsety);
	}

	public void offsetNodeList(Graph graph, Node curNode, float offsetx, float offsety) {
		curNode.getNodeData().setFixed(true);
		curNode.getNodeData().setX(curNode.getNodeData().x() + offsetx);
		curNode.getNodeData().setY(curNode.getNodeData().y() + offsety);
		Iterator<Node> nextNodes = graph.getNeighbors(curNode).iterator();
		while (nextNodes.hasNext()) {
			Node nextNode = nextNodes.next();
			if (!nextNode.getNodeData().isFixed() && !flagNodeList.contains(nextNode.getAttributes().getValue(0).toString())) {
				offsetNodeList(graph, nextNode, offsetx, offsety);
			}
		}
	}

	/***************************************************************************************/

	public void layoutLinePole() {
		analyseTopo();
		for (int i = 0; i < cNodeList.size(); i++) {
			String _id = cNodeList.get(i);
			Node _node = gehpiGraph.getNode(_id);
			Map poleEquipment = this.getLayoutEquipment(_id);
			if (poleEquipment == null) {
				continue;
			}
			poleEquipment.put("x", _node.getNodeData().x());
			poleEquipment.put("y", _node.getNodeData().y());
		}
		// 布局进出线
		String startfid = startOut.get("zddz").toString();
		Map startCabelJoint = getLayoutEquipment(startfid);
		float startX = Float.parseFloat(startCabelJoint.get("x").toString());
		float startY = Float.parseFloat(startCabelJoint.get("y").toString());
		boolean pos0Used = (Boolean) cNodePositionUsed.get(startCabelJoint.get("mrid")).get("pos0");
		boolean pos1Used = (Boolean) cNodePositionUsed.get(startCabelJoint.get("mrid")).get("pos1");
		boolean pos2Used = (Boolean) cNodePositionUsed.get(startCabelJoint.get("mrid")).get("pos2");
		boolean pos3Used = (Boolean) cNodePositionUsed.get(startCabelJoint.get("mrid")).get("pos3");
		float[] ablepos = new float[3];
		if (pos0Used == false) {
			ablepos[0] = startX + poleStep;
			ablepos[1] = startY;
			ablepos[2] = 0;
		} else if (pos1Used == false) {
			ablepos[0] = startX;
			ablepos[1] = startY + poleStep;
			ablepos[2] = 1;
		} else if (pos2Used == false) {
			ablepos[0] = startX - poleStep;
			ablepos[1] = startY;
			ablepos[2] = 2;
		} else if (pos3Used == false) {
			ablepos[0] = startX;
			ablepos[1] = startY - poleStep;
			ablepos[2] = 3;
		}
		if (ablepos != null) {
			float pos = ablepos[2];
			float ableX = ablepos[0];
			float ableY = ablepos[1];
			if (pos == 0) {// 左右两边，需要旋转
				ableX = ableX - poleStep / 2;
				startOut.put("rotate", -90);
				startOut.put("x", ableX + startoutSize);
				startOut.put("y", ablepos[1]);
			} else if (pos == 1) {
				startOut.put("rotate", 180);
				ableY = ableY - poleStep / 2;
				startOut.put("x", ablepos[0]);
				startOut.put("y", ableY + startoutSize);
			} else if (pos == 2) {
				startOut.put("rotate", 90);
				ableX = ableX + poleStep / 2;
				startOut.put("x", ableX - startoutSize);
				startOut.put("y", ablepos[1]);
			} else if (pos == 3) {
				ableY = ableY + poleStep / 2;
				startOut.put("x", ablepos[0]);
				startOut.put("y", ableY - startoutSize);
			}
		}
		// 进出线和中间接头之间增加线
		ArrayList<float[]> points = new ArrayList<float[]>();
		points.add(new float[] { startX, startY });
		points.add(new float[] { Float.parseFloat(startOut.get("x").toString()), Float.parseFloat(startOut.get("y").toString()) });

		Map topoExtra = new HashMap();
		topoExtra.put("1", startCabelJoint.get("fid") + "@1");
		topoExtra.put("2", startOut.get("fid") + "@1");

		Map _line = new HashMap();
		_line.put("fid", GUID.newGUID());
		_line.put("mrid", startOut.get("fid") + "_line");
		_line.put("name", "");
		_line.put("type", "line");
		_line.put("state", "1");
		_line.put("stype", this.jointLineSymbolId);// 图元id
		_line.put("color", startOut.get("color"));
		_line.put("points", points);
		_line.put("topoExtra", topoExtra);
		jointLineList.put(_line.get("fid").toString(), _line);
		layoutTransformer();
		layoutLines();// 布局架空线
	}

	public void layoutTransformer() {
		for (String key : poleEquipmentList.keySet()) {
			Map transformer = poleEquipmentList.get(key);
			String ssgt = transformer.get("ssgt").toString();
			Map equipObj = getLayoutEquipment(ssgt);
			String symbolid = transformer.get("stype").toString();
			int symbolSize = this.transformerSize;
			if (symbolid.equals(this.loadSymbolId)) {
				symbolSize = this.loadSize;
			}
			if (equipObj != null && equipObj.containsKey("x") && equipObj.containsKey("y")) {
				float equipX = Float.parseFloat(equipObj.get("x").toString());
				float equipY = Float.parseFloat(equipObj.get("y").toString());
				boolean pos0Used = (Boolean) cNodePositionUsed.get(equipObj.get("mrid")).get("pos0");
				boolean pos1Used = (Boolean) cNodePositionUsed.get(equipObj.get("mrid")).get("pos1");
				boolean pos2Used = (Boolean) cNodePositionUsed.get(equipObj.get("mrid")).get("pos2");
				boolean pos3Used = (Boolean) cNodePositionUsed.get(equipObj.get("mrid")).get("pos3");
				float[] ablepos = new float[3];
				if (pos0Used == false) {
					ablepos[0] = equipX + poleStep;
					ablepos[1] = equipY;
					ablepos[2] = 0;
				} else if (pos1Used == false) {
					ablepos[0] = equipX;
					ablepos[1] = equipY + poleStep;
					ablepos[2] = 1;
				} else if (pos2Used == false) {
					ablepos[0] = equipX - poleStep;
					ablepos[1] = equipY;
					ablepos[2] = 2;
				} else if (pos3Used == false) {
					ablepos[0] = equipX;
					ablepos[1] = equipY - poleStep;
					ablepos[2] = 3;
				}
				// int[] ablepos=getAblePosition(equipObj,false);
				if (ablepos != null) {
					float pos = ablepos[2];
					float ableX = ablepos[0];
					float ableY = ablepos[1];
					if (pos == 0) {// 左右两边，需要旋转
						ableX = ableX - poleStep / 2;
						if (symbolid.equals(this.transformerSymbolId)) {
							transformer.put("rotate", 90);
						} else if (symbolid.equals(this.loadSymbolId)) {
							transformer.put("rotate", -90);
						}
						transformer.put("x", ableX + symbolSize);
						transformer.put("y", ablepos[1]);
						if (positionList.containsKey(key)) {
							positionList.get(key)[2] = 1;
						}
					} else if (pos == 1) {
						ableY = ableY - poleStep / 2;
						if (symbolid.equals(this.loadSymbolId)) {
							transformer.put("rotate", 180);
						}
						transformer.put("x", ablepos[0]);
						transformer.put("y", ableY + symbolSize);
						if (positionList.containsKey(key)) {
							positionList.get(key)[3] = 1;
						}
					} else if (pos == 2) {
						ableX = ableX + poleStep / 2;
						if (symbolid.equals(this.transformerSymbolId)) {
							transformer.put("rotate", 270);
						} else if (symbolid.equals(this.loadSymbolId)) {
							transformer.put("rotate", 90);
						}
						transformer.put("x", ableX - symbolSize);
						transformer.put("y", ablepos[1]);
						if (positionList.containsKey(key)) {
							positionList.get(key)[0] = 1;
						}
					} else if (pos == 3) {
						ableY = ableY + poleStep / 2;
						if (symbolid.equals(this.transformerSymbolId)) {
							transformer.put("rotate", 180);
						}
						transformer.put("x", ablepos[0]);
						transformer.put("y", ableY - symbolSize);
						if (positionList.containsKey(key)) {
							positionList.get(key)[1] = 1;
						}
					}
					// 杆塔和变压器之间增加线
					ArrayList<float[]> points = new ArrayList<float[]>();
					points.add(new float[] { ableX, ableY });
					points.add(new float[] { Float.valueOf(equipObj.get("x").toString()), Float.valueOf(equipObj.get("y").toString()) });

					Map topoExtra = new HashMap();
					topoExtra.put("1", transformer.get("fid") + "@1");
					topoExtra.put("2", equipObj.get("fid") + "@1");

					Map _line = new HashMap();
					_line.put("fid", GUID.newGUID());
					_line.put("mrid", transformer.get("fid") + "_line");
					_line.put("name", "");
					_line.put("type", "line");
					_line.put("state", "1");
					_line.put("stype", this.jointLineSymbolId);// 图元id
					_line.put("color", equipObj.get("color"));
					_line.put("points", points);
					_line.put("topoExtra", topoExtra);
					jointLineList.put(_line.get("fid").toString(), _line);
				}
			}
		}
	}

	public ArrayList<String> getConnectedEquipment(String mrid) {
		ArrayList<String> connectedList = new ArrayList<String>();
		for (String key : lineList.keySet()) {
			Map _lineObject = lineList.get(key);
			String qddz = _lineObject.get("qddz").toString();
			String zddz = _lineObject.get("zddz").toString();
			if (qddz.equals(mrid)) {
				connectedList.add(zddz);
			} else if (zddz.equals(mrid)) {
				connectedList.add(qddz);
			}
		}
		return connectedList;
	}

	public void layoutLinePole_bak() {
		// 初始化杆塔、电缆中间接头右、下、左、上四个方向的占位情况
		for (String key : poleList.keySet()) {
			int[] _position = { 0, 0, 0, 0 };
			positionList.put(key, _position);
		}
		for (String key : cabelJointList.keySet()) {
			int[] _position = { 0, 0, 0, 0 };
			positionList.put(key, _position);
		}
		for (String key : transformerList.keySet()) {
			int[] _position = { 0, 0, 0, 0 };
			positionList.put(key, _position);
		}
		// 开始布局，起始位置（0,0）
		// 开始布局进出线
		startOut.put("x", -startoutSize);
		startOut.put("y", 0);
		startOut.put("rotate", 90);
		tempXYList.add(String.valueOf(0) + "," + String.valueOf(0));
		this.tempFidList.add(startOut.get("mrid").toString());

		// 布局起始的中间接头设备
		String startfid = startOut.get("zddz").toString();
		Map startCabelJoint = getLayoutEquipment(startfid);// cabelJointList.get(startfid);
		int[] startPos = positionList.get(startfid);
		startPos[2] = 1;
		positionList.put(startfid, startPos);
		startCabelJoint.put("x", 0 + this.poleStep);
		startCabelJoint.put("y", 0);
		tempXYList.add(String.valueOf(0 + this.poleStep) + "," + String.valueOf(0));
		this.tempFidList.add(startCabelJoint.get("mrid").toString());

		// 进出线和中间接头之间增加线
		ArrayList<float[]> points = new ArrayList<float[]>();
		points.add(new float[] { 0, 0 });
		points.add(new float[] { 0 + this.poleStep, 0 });

		Map topoExtra = new HashMap();
		topoExtra.put("1", startOut.get("fid") + "@1");
		topoExtra.put("2", startCabelJoint.get("fid") + "@1");

		Map _line = new HashMap();
		_line.put("fid", GUID.newGUID());
		_line.put("mrid", startOut.get("fid") + "_line");
		_line.put("name", "");
		_line.put("type", "line");
		_line.put("state", "1");
		_line.put("stype", this.jointLineSymbolId);// 图元id
		_line.put("color", startOut.get("color"));
		_line.put("points", points);
		_line.put("topoExtra", topoExtra);
		jointLineList.put(_line.get("fid").toString(), _line);

		layoutEquipment(startfid);// 布局杆塔
		layoutTransformer();// 布局柱上变压器
		layoutLines();// 布局架空线
	}

	// 布局柱上设备，包括(变压器)
	public void layoutTransformer_bak() {
		for (String key : poleEquipmentList.keySet()) {
			Map transformer = poleEquipmentList.get(key);
			String ssgt = transformer.get("ssgt").toString();
			Map equipObj = getLayoutEquipment(ssgt);
			String symbolid = transformer.get("stype").toString();
			int symbolSize = this.transformerSize;
			if (symbolid.equals(this.loadSymbolId)) {
				symbolSize = this.loadSize;
			}
			if (equipObj != null) {
				int[] ablepos = getAblePosition(equipObj, false);
				if (ablepos != null) {
					int pos = ablepos[2];
					int ableX = ablepos[0];
					int ableY = ablepos[1];
					if (pos == 0) {// 左右两边，需要旋转
						ableX = ableX - poleStep / 2;
						if (symbolid.equals(this.transformerSymbolId)) {
							transformer.put("rotate", 90);
						} else if (symbolid.equals(this.loadSymbolId)) {
							transformer.put("rotate", -90);
						}
						transformer.put("x", ableX + symbolSize);
						transformer.put("y", ablepos[1]);
						if (positionList.containsKey(key)) {
							positionList.get(key)[2] = 1;
						}
					} else if (pos == 1) {
						ableY = ableY - poleStep / 2;
						if (symbolid.equals(this.loadSymbolId)) {
							transformer.put("rotate", 180);
						}
						transformer.put("x", ablepos[0]);
						transformer.put("y", ableY + symbolSize);
						if (positionList.containsKey(key)) {
							positionList.get(key)[3] = 1;
						}
					} else if (pos == 2) {
						ableX = ableX + poleStep / 2;
						if (symbolid.equals(this.transformerSymbolId)) {
							transformer.put("rotate", 270);
						} else if (symbolid.equals(this.loadSymbolId)) {
							transformer.put("rotate", 90);
						}
						transformer.put("x", ableX - symbolSize);
						transformer.put("y", ablepos[1]);
						if (positionList.containsKey(key)) {
							positionList.get(key)[0] = 1;
						}
					} else if (pos == 3) {
						ableY = ableY + poleStep / 2;
						if (symbolid.equals(this.transformerSymbolId)) {
							transformer.put("rotate", 180);
						}
						transformer.put("x", ablepos[0]);
						transformer.put("y", ableY - symbolSize);
						if (positionList.containsKey(key)) {
							positionList.get(key)[1] = 1;
						}
					}
					// 杆塔和变压器之间增加线
					ArrayList<float[]> points = new ArrayList<float[]>();
					points.add(new float[] { ableX, ableY });
					points.add(new float[] { Float.valueOf(equipObj.get("x").toString()), Float.valueOf(equipObj.get("y").toString()) });

					Map topoExtra = new HashMap();
					topoExtra.put("1", transformer.get("fid") + "@1");
					topoExtra.put("2", equipObj.get("fid") + "@1");

					Map _line = new HashMap();
					_line.put("fid", GUID.newGUID());
					_line.put("mrid", transformer.get("fid") + "_line");
					_line.put("name", "");
					_line.put("type", "line");
					_line.put("state", "1");
					_line.put("stype", this.jointLineSymbolId);// 图元id
					_line.put("color", equipObj.get("color"));
					_line.put("points", points);
					_line.put("topoExtra", topoExtra);
					jointLineList.put(_line.get("fid").toString(), _line);
				}
			}
		}
	}

	// 布局线
	public void layoutLines() {
		for (String key : lineList.keySet()) {
			Map _lineObject = lineList.get(key);
			String qddz = _lineObject.get("qddz").toString();
			String zddz = _lineObject.get("zddz").toString();
			Map qdObject = getLayoutEquipment(qddz);
			Map zdObject = getLayoutEquipment(zddz);
			if (qdObject == null || zdObject == null || !qdObject.containsKey("x") || !zdObject.containsKey("x")) {
				continue;
			}
			float qdx = Float.parseFloat(qdObject.get("x").toString());
			float dqy = Float.parseFloat(qdObject.get("y").toString());
			float zdx = Float.parseFloat(zdObject.get("x").toString());
			float zdy = Float.parseFloat(zdObject.get("y").toString());
			ArrayList<float[]> points = new ArrayList<float[]>();
			points.add(new float[] { qdx, dqy });
			points.add(new float[] { zdx, zdy });
			Map topoExtra = new HashMap();
			topoExtra.put("1", qdObject.get("fid").toString() + "@1");
			topoExtra.put("2", zdObject.get("fid").toString() + "@1");

			_lineObject.put("points", points);
			_lineObject.put("topoExtra", topoExtra);
		}
	}

	public void layoutEquipment(String mrid) {
		Map equipObj = getLayoutEquipment(mrid);
		if (equipObj == null)
			return;
		ArrayList<String> connectedfid = getConnectedEquipment(mrid);
		for (int i = 0; i < connectedfid.size(); i++) {
			String cfid = connectedfid.get(i);
			if (this.tempFidList.contains(cfid)) {
				continue;
			} else {
				this.tempFidList.add(cfid);
			}
			Map cObject = getLayoutEquipment(cfid);
			if (cObject != null) {
				int[] ablepos = getAblePosition(equipObj, true);
				if (ablepos != null) {
					cObject.put("x", ablepos[0]);
					cObject.put("y", ablepos[1]);
				}
				layoutEquipment(cfid);
			}
		}
	}

	// 获取布局的设备（杆塔或者中间头）
	public Map getLayoutEquipment(String mrid) {
		Map equipment = null;
		if (poleList.containsKey(mrid)) {
			equipment = poleList.get(mrid);
		} else if (cabelJointList.containsKey(mrid)) {
			equipment = cabelJointList.get(mrid);
		} else if (poleEquipmentList.containsKey(mrid)) {
			equipment = poleEquipmentList.get(mrid);
		}
		return equipment;
	}

	// 获取设备的可用位置（没有被占用）
	public int[] getAblePosition(Map equipObj, boolean isPole) {
		int[] ablePos = null;// new int[2];
		String equipmrid = equipObj.get("mrid").toString();
		if (!equipObj.containsKey("x") || !equipObj.containsKey("y")) {
			return null;
		}
		int equipX = Integer.valueOf(equipObj.get("x").toString());
		int equipY = Integer.valueOf(equipObj.get("y").toString());
		int[] position = positionList.get(equipmrid);
		int step = isPole ? poleStep : transStep;
		boolean positionFlag = false;
		for (int i = 0; i < position.length; i++) {
			if (position[i] == 0) {
				positionFlag = true;
				int x = equipX;
				int y = equipY;
				if (i == 0) {
					x = equipX + poleStep;
					y = equipY;
				} else if (i == 1) {
					x = equipX;
					y = equipY + poleStep;
				} else if (i == 2) {
					x = equipX - poleStep;
					y = equipY;
				} else if (i == 3) {
					x = equipX;
					y = equipY - poleStep;
				}
				if (!tempXYList.contains(String.valueOf(x) + "," + String.valueOf(y))) {
					ablePos = new int[3];
					ablePos[0] = x;
					ablePos[1] = y;
					ablePos[2] = i;
					tempXYList.add(String.valueOf(ablePos[0]) + "," + String.valueOf(ablePos[1]));
					position[i] = 1;
					break;
				}
			}
		}
		if (ablePos == null) {
			if (positionFlag) {// 上下左右有可以布局的地方
				for (int i = 0; i < position.length; i++) {
					if (position[i] == 0) {
						int x = equipX;
						int y = equipY;
						if (i == 0) {
							x = (int) (equipX + poleStep * 0.8);
							y = equipY;
						} else if (i == 1) {
							x = equipX;
							y = (int) (equipY + poleStep * 0.8);
						} else if (i == 2) {
							x = (int) (equipX - poleStep * 0.8);
							y = equipY;
						} else if (i == 3) {
							x = equipX;
							y = (int) (equipY - poleStep * 0.8);
						}
						if (!tempXYList.contains(String.valueOf(x) + "," + String.valueOf(y))) {
							ablePos = new int[3];
							ablePos[0] = x;
							ablePos[1] = y;
							ablePos[2] = i;
							tempXYList.add(String.valueOf(ablePos[0]) + "," + String.valueOf(ablePos[1]));
							position[i] = 1;
							break;
						}
					}
				}
			} else {
				int x = (int) (equipX + poleStep * 0.8);
				int y = (int) (equipY + poleStep * 0.8);
				ablePos = new int[3];
				ablePos[0] = x;
				ablePos[1] = y;
				ablePos[2] = 0;
				tempXYList.add(String.valueOf(ablePos[0]) + "," + String.valueOf(ablePos[1]));
			}

		}
		return ablePos;
	}

	public ArrayList<Map> getLayoutResult() {
		ArrayList<Map> graphList = new ArrayList<Map>();
		graphList.add(startOut);
		for (String key : poleList.keySet()) {
			graphList.add(poleList.get(key));
		}
		for (String key : poleEquipmentList.keySet()) {
			graphList.add(poleEquipmentList.get(key));
		}
		for (String key : cabelJointList.keySet()) {
			graphList.add(cabelJointList.get(key));
		}
		for (String key : lineList.keySet()) {
			if (!lineList.get(key).containsKey("points")) {
				continue;
			}
			graphList.add(lineList.get(key));
		}
		for (String key : jointLineList.keySet()) {
			graphList.add(jointLineList.get(key));
		}
		return graphList;
	}
}