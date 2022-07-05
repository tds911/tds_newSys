package com.tds.project.domain;

import com.tds.common.utils.StringUtils;

public class GenTableColumn extends BaseEntity {
    private static final long serialVersionUID = 1L;
    private Long columnId;
    private Long tableId;
    private String columnName;
    private String columnComment;
    private String columnType;
    private String javaType;
    private String javaField;
    private String isPk;
    private String isIncrement;
    private String isRequired;
    private String isInsert;
    private String isEdit;
    private String isList;
    private String isQuery;
    private String queryType;
    private String htmlType;
    private String dictType;
    private Integer sort;

    public Long getColumnId() {
        return columnId;
    }

    public void setColumnId(Long columnId) {
        this.columnId = columnId;
    }

    public Long getTableId() {
        return tableId;
    }

    public void setTableId(Long tableId) {
        this.tableId = tableId;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnComment() {
        return columnComment;
    }

    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public String getJavaType() {
        return javaType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }

    public String getJavaField() {
        return javaField;
    }

    public void setJavaField(String javaFiled) {
        this.javaField = javaFiled;
    }

    public String getIsPk() {
        return isPk;
    }

    public void setIsPk(String isPk) {
        this.isPk = isPk;
    }

    public boolean isPK() {
        return isPk(this.isPk);
    }
    public boolean isPk(String isPk){
        return isPk!=null && StringUtils.equals("1",isPk);
    }

    public String getIsIncrement() {
        return isIncrement;
    }

    public void setIsIncrement(String isIncrement) {
        this.isIncrement = isIncrement;
    }

    public String getIsRequired() {
        return isRequired;
    }

    public void setIsRequired(String isRequired) {
        this.isRequired = isRequired;
    }

    public String getIsInsert() {
        return isInsert;
    }

    public void setIsInsert(String isInsert) {
        this.isInsert = isInsert;
    }

    public String getIsEdit() {
        return isEdit;
    }

    public boolean isList(){
        return isList(this.isList);
    }
    public boolean isList(String isList){
        return isList !=null &&StringUtils.equals("1",isList);
    }

    public void setIsEdit(String isEdit) {
        this.isEdit = isEdit;
    }

    public String getIsList() {
        return isList;
    }

    public void setIsList(String isList) {
        this.isList = isList;
    }

    public String getIsQuery() {
        return isQuery;
    }

    public void setIsQuery(String isQuery) {
        this.isQuery = isQuery;
    }

    public String getQueryType() {
        return queryType;
    }

    public void setQueryType(String queryType) {
        this.queryType = queryType;
    }

    public String getHtmlType() {
        return htmlType;
    }

    public void setHtmlType(String htmlType) {
        this.htmlType = htmlType;
    }

    public String getDictType() {
        if (StringUtils.isEmpty(dictType)){
            return StringUtils.EMPTY;
        }else {
            return dictType;
        }

    }

    public void setDictType(String dictType) {
        this.dictType = dictType;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
    public boolean isSuperColumn(){
        return isSuperColumn(this.javaField);
    }
    public static boolean isSuperColumn(String javaFiled){
        return StringUtils.equalsAnyIgnoreCase(javaFiled,
                "createBy","createTime","updateBy","updateTime","remark",
                "parentName","parentId","orderNum","ancestors");
    }
    public boolean isUsableColumn(){
        return isUsableColumn(javaField);
    }
    public static boolean isUsableColumn(String javaFiled){
        return StringUtils.equalsAnyIgnoreCase(javaFiled,"parentId","oderNum");
    }
    public String readConverterExp(){
        String remarks=StringUtils.substringBetween(this.columnComment,"(",")");
        StringBuffer sb=new StringBuffer();
        if (StringUtils.isNotEmpty(remarks)){
            for (String value:remarks.split(" ")){
                if (StringUtils.isNotEmpty(value)){
                    Object startStr=value.subSequence(0,1);
                    String endStr=value.substring(1);
                    sb.append("").append(startStr).append("=").append(endStr).append(",");
                }
            }
            return sb.deleteCharAt(sb.length()-1).toString();
        }
        else {
            return this.columnComment;
        }
    }

    @Override
    public String toString() {
        return "GenTableColumn{" +
                "columnId=" + columnId +
                ", tableId=" + tableId +
                ", columnName='" + columnName + '\'' +
                ", columnComment='" + columnComment + '\'' +
                ", columnType='" + columnType + '\'' +
                ", javaType='" + javaType + '\'' +
                ", javaFiled='" + javaField + '\'' +
                ", isPk='" + isPk + '\'' +
                ", isIncrement='" + isIncrement + '\'' +
                ", isRequired='" + isRequired + '\'' +
                ", isInsert='" + isInsert + '\'' +
                ", isEdit='" + isEdit + '\'' +
                ", isList='" + isList + '\'' +
                ", isQuery='" + isQuery + '\'' +
                ", queryType='" + queryType + '\'' +
                ", htmlType='" + htmlType + '\'' +
                ", dictType='" + dictType + '\'' +
                ", sort=" + sort +
                '}';
    }
}
