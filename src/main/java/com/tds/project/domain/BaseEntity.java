package com.tds.project.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class BaseEntity implements Serializable {
    private static final long serialVersionUID=1L;
    private String createBy;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    private String createName;
    private String updateBy;
    private Date updateTime;
    private String remark;
    private String beginTime;
    private String endTime;
    private Map<String,Object> params;
    private String publicParams;

    public Map<String, Object> getParams() {
        if (params==null){
            params=new HashMap<>();
        }
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public String getPublicParams() {
        return publicParams;
    }

    public void setPublicParams(String publicParms) {
        this.publicParams = publicParms;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "createBy='" + createBy + '\'' +
                ", createTime=" + createTime +
                ", createName='" + createName + '\'' +
                ", updateBy='" + updateBy + '\'' +
                ", updateTime=" + updateTime +
                ", remark='" + remark + '\'' +
                ", beginTime='" + beginTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", params=" + params +
                ", publicParams='" + publicParams + '\'' +
                '}';
    }
}
