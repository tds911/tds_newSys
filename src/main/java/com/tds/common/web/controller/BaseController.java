package com.tds.common.web.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tds.common.constant.HttpStatus;
import com.tds.common.utils.DateUtils;
import com.tds.common.utils.Sql.SqlUtil;
import com.tds.common.utils.StringUtils;
import com.tds.common.utils.page.PageDomain;
import com.tds.common.utils.page.TableDateInfo;
import com.tds.common.utils.page.TableSupport;
import com.tds.common.web.domain.server.AjaxResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.WebDataBinder;

import java.beans.PropertyEditorSupport;
import java.util.Date;
import java.util.List;

public class BaseController {
    protected final Logger logger = LoggerFactory.getLogger(BaseController.class);


    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(DateUtils.parseDate(text));
            }
        });
    }

    protected void startPage() {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        if (StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize)) {
            String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
            PageHelper.startPage(pageNum, pageSize, orderBy);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    protected TableDateInfo getDataTable(List<?> list) {
        TableDateInfo rspData = new TableDateInfo();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setMsg("查询成功");
        rspData.setRows(list);
        rspData.setTotal(new PageInfo(list).getTotal());
        return rspData;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    protected TableDateInfo getDtaTable2(List<?> list, int csTotal) {
        TableDateInfo rspData = new TableDateInfo();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setMsg("查询成功");
        rspData.setRows(list);
        rspData.setTotal(new PageInfo(list).getTotal());
        rspData.setCsTotal(csTotal);
        return rspData;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    protected TableDateInfo getDataTable3(List<?> list, Object list2) {
        TableDateInfo rspDate = new TableDateInfo();
        rspDate.setCode(HttpStatus.SUCCESS);
        rspDate.setMsg("查询成功");
        rspDate.setRows(list);
        rspDate.setTotal(new PageInfo(list).getTotal());
        rspDate.setRows2(list2);
        return rspDate;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    protected TableDateInfo getDataTable4(List<?> list, Long total, Object list2) {
        TableDateInfo rspDate = new TableDateInfo();
        rspDate.setCode(HttpStatus.SUCCESS);
        rspDate.setMsg("查询成功");
        rspDate.setRows(list);
        rspDate.setTotal(total);
        rspDate.setRows2(list2);
        return rspDate;
    }

    public AjaxResult toAjax(int rows) {
        return rows > 0 ? AjaxResult.success() :AjaxResult.error();
    }

}
