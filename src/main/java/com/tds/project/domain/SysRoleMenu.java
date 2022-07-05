package com.tds.project.domain;

public class SysRoleMenu {
    private Long roleId;
    private Long menuId;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    @Override
    public String toString() {
        return "SysRoleMenu{" +
                "roleId=" + roleId +
                ", menuId=" + menuId +
                '}';
    }
}
