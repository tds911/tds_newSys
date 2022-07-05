package com.tds.project.domain;


import com.tds.common.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class SysMenu extends BaseEntity{
    private static final long serialVersionUID=1L;

    private Long menuId;
    private String parentName;
    private String menuName;
    private Long parentId;
    private String orderNum;
    private String path;
    private String component;
    private String isFrame;
    private String menuType;
    private String visible;
    private String status;
    private String perms;
    private String icon;
    private List<SysMenu> children=new ArrayList<SysMenu>();

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getIsFrame() {
        return isFrame;
    }

    public void setIsFrame(String isFrame) {
        this.isFrame = isFrame;
    }

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    public String getVisible() {
        return visible;
    }

    public void setVisible(String visible) {
        this.visible = visible;
    }

    public String getPerms() {
        if (StringUtils.isEmpty(perms)){
            return StringUtils.EMPTY;
        }
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<SysMenu> getChildren() {
        return children;
    }

    public void setChildren(List<SysMenu> children) {
        this.children = children;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "SysMenu{" +
                "menuId=" + menuId +
                ", parentName='" + parentName + '\'' +
                ", menuName='" + menuName + '\'' +
                ", parentId=" + parentId +
                ", orderNum='" + orderNum + '\'' +
                ", path='" + path + '\'' +
                ", component='" + component + '\'' +
                ", isFrame='" + isFrame + '\'' +
                ", menuType='" + menuType + '\'' +
                ", visible='" + visible + '\'' +
                ", status='" + status + '\'' +
                ", perms='" + perms + '\'' +
                ", icon='" + icon + '\'' +
                ", children=" + children +
                '}';
    }
}
