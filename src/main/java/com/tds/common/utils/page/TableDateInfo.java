package com.tds.common.utils.page;

import java.io.Serializable;
import java.util.List;

public class TableDateInfo implements Serializable {
    private static final long serialVersionUID=1L;
    private long total;
    private List<?> rows;
    private int code;
    private String msg;
    private long csTotal;
    private Object rows2;

    public TableDateInfo() {
    }
    public TableDateInfo(List<?>list,int total){
        this.rows=list;
        this.total=total;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public long getCsTotal() {
        return csTotal;
    }

    public void setCsTotal(long csTotal) {
        this.csTotal = csTotal;
    }

    public Object getRows2() {
        return rows2;
    }

    public void setRows2(Object rows2) {
        this.rows2 = rows2;
    }
}
