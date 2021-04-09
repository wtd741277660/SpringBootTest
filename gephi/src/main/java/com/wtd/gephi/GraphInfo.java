package com.wtd.gephi;

public class GraphInfo {

    /**
     * 版本信息
     */
    private static final long serialVersionUID = 1L;

    /**
     * 图元状态
     */
    private String state;

    /**
     * 标示id
     */
    private String guid_;

    /**
     * 图元ID
     */
    private String graphId;

    /**
     * 图元名称
     */
    private String graphName;

    /**
     * 关联图层（跨图层关联）
     */
    private String relatedLayerId;

    /**
     * 关联图形（跨图层关联）
     */
    private String relatedGraphId;

    /**
     * 图元类型
     */
    private String graphType;

    /**
     * 图元所属版本名称
     */
    private String layerId;

    /**
     * 图元几何信息
     */
    private String geometry;

    /**
     * 图元显示样式
     */
    private String style;

    /**
     * 图元属性保留字段(Map<String, Object>)
     */
    private Object graphAttrExtra;

    /**
     * 规划状态
     */
    private String planningState;

    /**
     * 适应字段
     */
    private String applicableField;

    /**
     * 场站图图层Id
     */
    private String stationLayerID;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getGuid_() {
        return guid_;
    }

    public void setGuid_(String guid_) {
        this.guid_ = guid_;
    }

    public String getGraphId() {
        return graphId;
    }

    public void setGraphId(String graphId) {
        this.graphId = graphId;
    }

    public String getGraphName() {
        return graphName;
    }

    public void setGraphName(String graphName) {
        this.graphName = graphName;
    }

    public String getRelatedLayerId() {
        return relatedLayerId;
    }

    public void setRelatedLayerId(String relatedLayerId) {
        this.relatedLayerId = relatedLayerId;
    }

    public String getRelatedGraphId() {
        return relatedGraphId;
    }

    public void setRelatedGraphId(String relatedGraphId) {
        this.relatedGraphId = relatedGraphId;
    }

    public String getGraphType() {
        return graphType;
    }

    public void setGraphType(String graphType) {
        this.graphType = graphType;
    }

    public String getLayerId() {
        return layerId;
    }

    public void setLayerId(String layerId) {
        this.layerId = layerId;
    }

    public String getGeometry() {
        return geometry;
    }

    public void setGeometry(String geometry) {
        this.geometry = geometry;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public Object getGraphAttrExtra() {
        return graphAttrExtra;
    }

    public void setGraphAttrExtra(Object graphAttrExtra) {
        this.graphAttrExtra = graphAttrExtra;
    }

    public String getPlanningState() {
        return planningState;
    }

    public void setPlanningState(String planningState) {
        this.planningState = planningState;
    }

    public String getApplicableField() {
        return applicableField;
    }

    public void setApplicableField(String applicableField) {
        this.applicableField = applicableField;
    }

    public String getStationLayerID() {
        return stationLayerID;
    }

    public void setStationLayerID(String stationLayerID) {
        this.stationLayerID = stationLayerID;
    }
}
