package com.tds.project.controller;

import com.tds.common.core.text.Convert;
import com.tds.common.utils.page.TableDateInfo;
import com.tds.common.web.controller.BaseController;
import com.tds.common.web.domain.server.AjaxResult;
import com.tds.common.web.page.TableDataInfo;

import com.tds.project.domain.GenTable;
import com.tds.project.domain.GenTableColumn;
import com.tds.project.service.GenTableColumnService;
import com.tds.project.service.GenTableService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tool/gen")
public class GenController extends BaseController {

    @Autowired
    private GenTableColumnService genTableColumnService;

    @Autowired
    private GenTableService genTableService;

    /**
     * 查询代码生成列表
     * @param genTable
     * @return
     */
    @GetMapping("/list")
    public TableDataInfo genList(GenTable genTable){
        startPage();
        List<GenTable> list=genTableService.selectGenTableList(genTable);
        return getDataTable(list);
    }

    /**
     *  查询代码生成业务
     * @param tableId
     * @return
     */
    @GetMapping(value = "/{tableId}")
    public AjaxResult genInfo(@PathVariable Long tableId){
        GenTable genTable=genTableService.selectGenTableById(tableId);
        List<GenTableColumn> list=genTableColumnService.selectGenTableColumnListByTableId(tableId);
        Map<String ,Object>map=new HashMap<String,Object>();
        map.put("info",genTable);
        map.put("rows",list);
        return AjaxResult.success(map);
    }

    /**
     * 查询书籍库列表
     * @param genTable
     * @return
     */
    @GetMapping("/db/list")
    public TableDataInfo dataList(GenTable genTable){
        startPage();
        List<GenTable> list=genTableService.selectDbTableList(genTable);
        return getDataTable(list);
    }


    /**
     * 导入表结构保存
     * @param tableId
     * @return
     */
    @GetMapping("/column/{tableId}")
    public TableDataInfo columnList(Long tableId){
        TableDataInfo dataInfo=new TableDataInfo();
        List<GenTableColumn> list=genTableColumnService.selectGenTableColumnListByTableId(tableId);
        dataInfo.setRows(list);
        dataInfo.setTotal(list.size());
        return dataInfo;
    }

    /**
     * 导入表结构
     * @param tables
     * @return
     */
    @PostMapping("/importTable")
    public AjaxResult importTableSave(String tables){
        String[] tableNames= Convert.toStrArray(tables);
        //查询表信息
        List<GenTable> tableList=genTableService.selectDbTableListByNames(tableNames);
        genTableService.importGenTable(tableList);
        return AjaxResult.success();
    }

    /**
     * 修改保存代码生成业务
     * @param genTable
     * @return
     */
    @PutMapping
    public AjaxResult editSave(@Validated @RequestBody GenTable genTable){
        genTableService.validateEdit(genTable);
        genTableService.updateGenTable(genTable);
        return AjaxResult.success();
    }

    /**
     *删除代码生成
     * @param tableIds
     * @return
     */
    @DeleteMapping("/{tableIds}")
    public AjaxResult remove(@PathVariable Long[] tableIds){
        genTableService.deleteGenTableByIds(tableIds);
        return AjaxResult.success();
    }

    /**
     * 预览代码
     * @param tableId
     * @return
     * @throws IOException
     */
    @GetMapping("/preview/{tableId}")
    public AjaxResult preview(@PathVariable("tableId")Long tableId)throws IOException{
        Map<String,String> dataMap=genTableService.previewCode(tableId);
        return AjaxResult.success(dataMap);
    }

    /**
     * 生成代码
     * @param response
     * @param tableName
     * @throws IOException
     */
    @GetMapping("/genCode/{tableName}")
    public void genCode(HttpServletResponse response,@PathVariable("tableName")String tableName)throws IOException{
        byte[] data=genTableService.generatorCode(tableName);
        genCode(response,data);
    }

    /**
     * 批量生成代码
     * @param response
     * @param tables
     * @throws IOException
     */
    @GetMapping("/batchGenCode")
    public void batchGenCode(HttpServletResponse response,String tables) throws IOException {
        String[] tableNames=Convert.toStrArray(tables);
        byte[] data=genTableService.generatorCode(tableNames);
        genCode(response,data);
    }


    /**
     * 生成zip文件
     * @param response
     * @param data
     * @throws IOException
     */
    private void genCode(HttpServletResponse response,byte[] data)throws IOException{
        response.reset();
        response.setHeader("Content-Disposition","attachment; filename=\"tds.zip\"");
        response.addHeader("Content-Length",""+data.length);
        response.setContentType("application/octet-stream;charset=UTF-8");
        IOUtils.write(data,response.getOutputStream());
    }
}
