package com.tds.project.domain;

import java.util.Arrays;
import java.util.Date;

public class SysOperLog extends com.tds.project.domain.BaseEntity {
    private static final long serialVersionUID=1L;
    private long operId;
    private String title;
    private Integer businessType;
    private Integer[] businessTYpe;
    private String method;
    private String requestMethod;
    private String operName;
    private Integer status;
    private String operIp;
    private String operUrl;
    private String operLocation;
    private String operParam;
    private Date operTime;
    private String methodTitle;
    private String jsonResult;
    private String errorMsg;
    private Integer OperatorType;

    public long getOperId() {
        return operId;
    }

    public void setOperId(long operId) {
        this.operId = operId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
    }

    public Integer[] getBusinessTYpe() {
        return businessTYpe;
    }

    public void setBusinessTYpe(Integer[] businessTYpe) {
        this.businessTYpe = businessTYpe;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getOperName() {
        return operName;
    }

    public void setOperName(String operName) {
        this.operName = operName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getOperIp() {
        return operIp;
    }

    public void setOperIp(String operIp) {
        this.operIp = operIp;
    }

    public String getOperUrl() {
        return operUrl;
    }

    public void setOperUrl(String operUrl) {
        this.operUrl = operUrl;
    }

    public String getJsonResult() {
        return jsonResult;
    }

    public void setJsonResult(String jsonResult) {
        this.jsonResult = jsonResult;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Integer getOperatorType() {
        return OperatorType;
    }

    public void setOperatorType(Integer operatorType) {
        OperatorType = operatorType;
    }

    public String getOperLocation() {
        return operLocation;
    }

    public void setOperLocation(String operLocation) {
        this.operLocation = operLocation;
    }

    public String getOperParam() {
        return operParam;
    }

    public void setOperParam(String operParam) {
        this.operParam = operParam;
    }

    public Date getOperTime() {
        return operTime;
    }

    public void setOperTime(Date operTime) {
        this.operTime = operTime;
    }

    public String getMethodTitle() {
        return methodTitle;
    }

    public void setMethodTitle(String methodTitle) {
        this.methodTitle = methodTitle;
    }

    @Override
    public String toString() {
        return "SysOperLog{" +
                "operId=" + operId +
                ", title='" + title + '\'' +
                ", businessType=" + businessType +
                ", businessTYpe=" + Arrays.toString(businessTYpe) +
                ", method='" + method + '\'' +
                ", requestMethod='" + requestMethod + '\'' +
                ", operName='" + operName + '\'' +
                ", status=" + status +
                ", operIp='" + operIp + '\'' +
                ", operUrl='" + operUrl + '\'' +
                ", operLocation='" + operLocation + '\'' +
                ", operParam='" + operParam + '\'' +
                ", operTime=" + operTime +
                ", methodTitle='" + methodTitle + '\'' +
                ", jsonResult='" + jsonResult + '\'' +
                ", errorMsg='" + errorMsg + '\'' +
                ", OperatorType=" + OperatorType +
                '}';
    }
}
