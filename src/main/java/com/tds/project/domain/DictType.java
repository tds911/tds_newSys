package com.tds.project.domain;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.tds.common.framework.aspectj.lang.annotation.Excel;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 字典类型对象sys_dict_type
 *
 * @author:tds
 * @date:2022-07-15
 */
public class DictType extends BaseEntity
{
    private static final long serialVersionUID=1L;

    /**主键seq_sys_dict_type,nextval*/
    private Long dictId;
    /**字典名称*/
    @Excel(name = "字典名称")
    private String dictName;
    /**字典类型*/
    @Excel(name = "字典类型")
    private String dictType;
    /**状态*/
    @Excel(name = "状态")
    private String status;
    /**$column.columnComment*/
    private String createBy;
    /**$column.columnComment*/
    private Date createTime;
    /**$column.columnComment*/
    private String updateBy;
    /**$column.columnComment*/
    private Date updateTime;
    /**备注*/
    @Excel(name = "备注")
    private String remark;
    public void setDictId(Long dictId)
        {
            this.dictId = dictId;
            }
    public Long getDictId(){
            return dictId;
            }
    public void setDictName(String dictName)
        {
            this.dictName = dictName;
            }
    public String getDictName(){
            return dictName;
            }
    public void setDictType(String dictType)
        {
            this.dictType = dictType;
            }
    public String getDictType(){
            return dictType;
            }
    public void setStatus(String status)
        {
            this.status = status;
            }
    public String getStatus(){
            return status;
            }
    public void setCreateBy(String createBy)
        {
            this.createBy = createBy;
            }
    public String getCreateBy(){
            return createBy;
            }
    public void setCreateTime(Date createTime)
        {
            this.createTime = createTime;
            }
    public Date getCreateTime(){
            return createTime;
            }
    public void setUpdateBy(String updateBy)
        {
            this.updateBy = updateBy;
            }
    public String getUpdateBy(){
            return updateBy;
            }
    public void setUpdateTime(Date updateTime)
        {
            this.updateTime = updateTime;
            }
    public Date getUpdateTime(){
            return updateTime;
            }
    public void setRemark(String remark)
        {
            this.remark = remark;
            }
    public String getRemark(){
            return remark;
            }

    @Override
    public String toString() {
        return "DictType{" +
                "dictId=" + dictId +
                ", dictName='" + dictName + '\'' +
                ", dictType='" + dictType + '\'' +
                ", status='" + status + '\'' +
                ", createBy='" + createBy + '\'' +
                ", createTime=" + createTime +
                ", updateBy='" + updateBy + '\'' +
                ", updateTime=" + updateTime +
                ", remark='" + remark + '\'' +
                '}';
    }
}