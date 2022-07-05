package com.tds.project.domain;

import javax.validation.Valid;
import java.util.List;

public class GenTable extends com.tds.project.domain.BaseEntity {
    private static final long serialVersionUID=1L;

    private Long tableId;
    private String tableName;
    private String tableComment;
    private String className;
    private String tplCategory;
    private String packageName;
    private String moduleName;
    private String businessName;
    private String functionName;
    private String functionAuthor;
    private com.tds.project.domain.GenTableColumn pkColumn;
    @Valid
    private List<com.tds.project.domain.GenTableColumn> columns;

    private String options;
    private String treeCode;
    private String treeParentCode;
    private String treeName;
    private Long menuId;

    public Long getTableId() {
        return tableId;
    }

    public void setTableId(Long tableId) {
        this.tableId = tableId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableComment() {
        return tableComment;
    }

    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getTplCategory() {
        return tplCategory;
    }

    public void setTplCategory(String tplCategory) {
        this.tplCategory = tplCategory;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public com.tds.project.domain.GenTableColumn getPkColumn() {
        return pkColumn;
    }

    public void setPkColumn(com.tds.project.domain.GenTableColumn pkColumn) {
        this.pkColumn = pkColumn;
    }

    public List<com.tds.project.domain.GenTableColumn> getColumns() {
        return columns;
    }

    public void setColumns(List<com.tds.project.domain.GenTableColumn> columns) {
        this.columns = columns;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public String getTreeParentCode() {
        return treeParentCode;
    }

    public void setTreeParentCode(String treeParentCode) {
        this.treeParentCode = treeParentCode;
    }

    public String getTreeName() {
        return treeName;
    }

    public void setTreeName(String treeName) {
        this.treeName = treeName;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public String getFunctionAuthor() {
        return functionAuthor;
    }

    public void setFunctionAuthor(String functionAuthor) {
        this.functionAuthor = functionAuthor;
    }

    public String getTreeCode() {
        return treeCode;
    }

    public void setTreeCode(String treeCode) {
        this.treeCode = treeCode;
    }

    @Override
    public String toString() {
        return "GenTable{" +
                "tableId=" + tableId +
                ", tableName='" + tableName + '\'' +
                ", tableComment='" + tableComment + '\'' +
                ", className='" + className + '\'' +
                ", tplCategory='" + tplCategory + '\'' +
                ", packageName='" + packageName + '\'' +
                ", moduleName='" + moduleName + '\'' +
                ", businessName='" + businessName + '\'' +
                ", functionName='" + functionName + '\'' +
                ", functionAuthor='" + functionAuthor + '\'' +
                ", pkColumn=" + pkColumn +
                ", columns=" + columns +
                ", options='" + options + '\'' +
                ", treeCode='" + treeCode + '\'' +
                ", treeParentCode='" + treeParentCode + '\'' +
                ", treeName='" + treeName + '\'' +
                ", menuId=" + menuId +
                '}';
    }
}
