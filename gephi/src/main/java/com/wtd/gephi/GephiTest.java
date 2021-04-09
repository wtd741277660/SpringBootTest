package com.wtd.gephi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GephiTest {


    public static void main(String[] args) {
        new GephiTest().generatorLinePoleNoPosition();
        System.out.println("over");
    }
    public String generatorLinePoleNoPosition(){
        TopoMatLinePoleNoPosition topoMat = new TopoMatLinePoleNoPosition();
        topoMat.layoutLinePole();
        ArrayList<Map> layoutResultList = topoMat.getLayoutResult();

        for(Map map:layoutResultList){
//            System.out.println(map);
            String type = (String) map.get("type");
            if("point".equals(type)){
                System.out.println("{name:'" + map.get("name") + "',x:" + map.get("x") + ",y:" + map.get("y") + "},");
            }else if("line".equals(type)){
                List<String> points = (List<String>) map.get("points");
//                System.out.println("{source:'" + points.get(0) + "',target:'" + points.get(1) + "'},");
            }
        }

//        ArrayList<GraphInfo> gisGraphList = getGraphInfoByMapList(layoutResultList);

//        for(GraphInfo map:gisGraphList){
//            System.out.println(map);
//        }

//                    String layerid = svg.saveGisGraphic(layerId, "1800103", gisGraphList);
        return null;
    }

    public ArrayList<GraphInfo>  getGraphInfoByMapList(ArrayList<Map>mapList){
        ArrayList<GraphInfo> graphInfoList=new ArrayList<GraphInfo>();
        for(int i=0;i<mapList.size();i++){
            Map feature=mapList.get(i);
            GraphInfo gisgraph = new GraphInfo();
            String fid=feature.get("fid").toString();
            String mrid=feature.containsKey("mrid")?feature.get("mrid").toString():"";
            String name=feature.containsKey("name")?feature.get("name").toString():"";
            String stype=feature.get("stype").toString();
            String state=feature.get("state").toString();
            StringBuilder styleSB=new StringBuilder();
            styleSB.append(String.format(" \"label\":\"%s\"",name));
            if(feature.get("type").equals("line")){
                if(!feature.containsKey("points")){
                    return null;
                }
                String color=feature.get("color")==null?"default":feature.get("color").toString();
                styleSB.append(String.format(",\"strokeColor\":\"%s\"",color));
                ArrayList<float[]> points=(ArrayList<float[]>) feature.get("points");
                StringBuilder lineSB = new StringBuilder();
                for(int j=0;j<points.size();j++){
                    lineSB.append(String.format("%s %s,", points.get(j)[0], points.get(j)[1]));
                }
                String wktString= String.format("LINESTRING(%s)", lineSB.substring(0, lineSB.length()-1));
                gisgraph.setGeometry(wktString);
                if(feature.containsKey("topoExtra")){
                    gisgraph.setGraphAttrExtra(feature.get("topoExtra"));
                }
            }else{
                String color=feature.get("color")==null?"default":feature.get("color").toString();
                styleSB.append(String.format(",\"strokeColor\":\"%s\"",color));
                String group1="",group2="",group3="";
                if(feature.containsKey("group1")){
                    group1=feature.get("group1")==null?"default":feature.get("group1").toString();
                }
                if(!group1.equals("")){
                    styleSB.append(String.format(",\"group_1\":\"%s\"",group1));
                }
                if(feature.containsKey("group2")){
                    group2=feature.get("group2")==null?"default":feature.get("group2").toString();
                }
                if(!group2.equals("")){
                    styleSB.append(String.format(",\"group_2\":\"%s\"",group2));
                }
                if(feature.containsKey("group3")){
                    group3=feature.get("group3")==null?"default":feature.get("group3").toString();
                }
                if(!group3.equals("")){
                    styleSB.append(String.format(",\"group_3\":\"%s\"",group3));
                }
                String wktString=String.format("POINT(%s %s)", feature.get("x"), feature.get("y"));
                gisgraph.setGeometry(wktString);
                float angle=0;
                int size=0;
                if(feature.containsKey("rotate")){
                    angle=Float.valueOf(feature.get("rotate").toString());
                }
                if(feature.containsKey("size")){
                    size=(Integer) feature.get("size");
                }
                if(angle!=0){
                    styleSB.append(String.format(",\"rotation\":%s",(int)angle));
                }
                if(size!=0){
                    styleSB.append(String.format(",\"pointRadius\":%s",size/2));
                }
            }
            String style="{"+styleSB.toString()+"}";
            gisgraph.setGraphId(fid);
            gisgraph.setGraphName(name);
            gisgraph.setGraphType(stype);
            gisgraph.setStyle(style);
            gisgraph.setApplicableField(mrid);
            gisgraph.setPlanningState(state);
            graphInfoList.add(gisgraph);
        }

        return graphInfoList;
    }
}
