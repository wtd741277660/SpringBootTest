package com.wtd.gephi;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public class TopoEntity implements Serializable {

	private static final long serialVersionUID = 762432352417504605L;

	boolean isVirtual = false;

	String guid;

	// 设备编码
	String sbbm;

	// 拓扑表中的节点信息
	String[] jd;

	// 拓扑表中的端子号信息
	String[] dzh;

	String sbmc;

	String sblx;

	String[] sssb;

	// 图形使用:端点
	String[] ports;

	// json显示名称
	String name;

	// json显示的值
	String value;

	// 上级连接设备编码
	String parentId;

	// 自定义属性
	Object attr;

	// 下级设备集合
	List<TopoEntity> children;

	// 设备-节点
	HashMap<String, TopoEntity> device_cons;

	// 节点-设备
	HashMap<String, String> con_devices;

	// 开关状态
	HashMap<String, Integer> breakerStatus;

	public Object getAttr() {
		return attr;
	}

	public void setAttr(Object attr) {
		this.attr = attr;
	}

	public boolean getIsVirtual() {
		return isVirtual;
	}

	public void setIsVirtual(boolean isVirtual) {
		this.isVirtual = isVirtual;
	}

	public HashMap<String, TopoEntity> getDevice_cons() {
		return device_cons;
	}

	public void setDevice_cons(HashMap<String, TopoEntity> device_cons) {
		this.device_cons = device_cons;
	}

	public HashMap<String, String> getCon_devices() {
		return con_devices;
	}

	public void setCon_devices(HashMap<String, String> con_devices) {
		this.con_devices = con_devices;
	}

	public HashMap<String, Integer> getBreakerStatus() {
		return breakerStatus;
	}

	public void setBreakerStatus(HashMap<String, Integer> breakerStatus) {
		this.breakerStatus = breakerStatus;
	}

	public String[] getPorts() {
		return ports;
	}

	public void setPorts(String[] ports) {
		this.ports = ports;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String[] getJd() {
		return jd;
	}

	public void setJd(String[] jd) {
		this.jd = jd;
	}

	public String getSbmc() {
		return sbmc;
	}

	public void setSbmc(String sbmc) {
		this.sbmc = sbmc;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSbbm() {
		return sbbm;
	}

	public void setSbbm(String sbbm) {
		this.sbbm = sbbm;
	}

	public List<TopoEntity> getChildren() {
		return children;
	}

	public void setChildren(List<TopoEntity> children) {
		this.children = children;
	}

	public String[] getDzh() {
		return dzh;
	}

	public void setDzh(String[] dzh) {
		this.dzh = dzh;
	}

	public String getSblx() {
		return sblx;
	}

	public void setSblx(String sblx) {
		this.sblx = sblx;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String[] getSssb() {
		return sssb;
	}

	public void setSssb(String[] sssb) {
		this.sssb = sssb;
	}

	public TopoEntity clone() {
		TopoEntity topoEntity = null;
		try { // 将该对象序列化成流,因为写在流里的是对象的一个拷贝，而原对象仍然存在于JVM里面。所以利用这个特性可以实现对象的深拷贝
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(this);
			// 将流序列化成对象
			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			ObjectInputStream ois = new ObjectInputStream(bais);
			topoEntity = (TopoEntity) ois.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return topoEntity;
	}

}
