package com.tds.common.web.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tds.project.domain.SysMenu;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class TreeSelect implements Serializable {
    private static final long serialVersionUID=1L;
    private Long id;
    private String label;
    private String zxbflg;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getZxbflg() {
        return zxbflg;
    }

    public void setZxbflg(String zxbflg) {
        this.zxbflg = zxbflg;
    }
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<TreeSelect>children;

    public TreeSelect() {
    }

    public TreeSelect(SysMenu menu) {
        this.id = menu.getMenuId();
        this.label = menu.getMenuName();
        this.children = menu.getChildren().stream().map(TreeSelect::new).collect(Collectors.toList());
    }

//    public TreeSelect(SysDept dept) {
//        this.id = dept.getDeptId();
//        this.label = dept.getMenuName();
//        this.children = dept.getChildren().stream().map(TreeSelect::new).collect(Collectors.toList());
//    }



    public List<TreeSelect> getChildren() {
        return children;
    }

    public void setChildren(List<TreeSelect> children) {
        this.children = children;
    }
}
