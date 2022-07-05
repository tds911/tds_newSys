package com.tds.project.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tds.common.constant.Constants;
import com.tds.common.constant.GenConstants;
import com.tds.common.exception.CustomException;
import com.tds.common.utils.*;

import com.tds.project.domain.GenTable;
import com.tds.project.domain.GenTableColumn;
import com.tds.project.mapper.GenTableColumnMapper;
import com.tds.project.mapper.GenTableMapper;
import com.tds.project.service.GenTableService;
import org.apache.commons.io.IOUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class GenTableServiceImpl implements GenTableService {
    private  static final Logger log= LoggerFactory.getLogger(GenTableServiceImpl.class);
    @Autowired
    private GenTableMapper genTableMapper;
    @Autowired
    private GenTableColumnMapper genTableColumnMapper;

    @Override
    public List<GenTable> selectDbTableListByNames(String[] tableNames) {
        return genTableMapper.selectDbTableListByNames(tableNames);
    }

    @Override
    @Transactional
    public void importGenTable(List<GenTable> tableList) {
        String operName = SecurityUtils.getUsername();
        try {
            for (GenTable table : tableList) {
                String tableName = table.getTableName();
                GenUtils.initTable(table, operName);
                int i = genTableMapper.insertGenTable(table);
                if (i > 0) {
                    List<GenTableColumn> genTableColumns = genTableColumnMapper.selectDbTableColumnsByName(tableName);
                    for (GenTableColumn genTableColumn : genTableColumns) {
                        GenUtils.initColumnField(genTableColumn, table);
                        genTableColumnMapper.insertGenTableColumn(genTableColumn);
                    }
                }
            }
        } catch (Exception e) {
            throw new CustomException("导入失败:" + e.getMessage());
        }

    }

    @Override
    public List<GenTable> selectGenTableList(GenTable genTable) {
        return genTableMapper.selectGenTableList(genTable);
    }

    @Override
    public GenTable selectGenTableById(Long id) {
        GenTable genTable = genTableMapper.selectGenTableById(id);
        setTableFromOptions(genTable);
        return genTable;
    }

    @Override
    public List<GenTable> selectDbTableList(GenTable genTable) {
        return genTableMapper.selectDbTableList(genTable);
    }

    @Override
    public void validateEdit(GenTable genTable) {
        if (GenConstants.TPL_TREE.equals(genTable.getTplCategory())) {
            String options = JSON.toJSONString(genTable.getParams());
            JSONObject paramsObj = JSONObject.parseObject(options);
            if (StringUtils.isEmpty(paramsObj.getString(GenConstants.TREE_CODE))) {
                throw new CustomException("树编码字段不能为空");
            } else if (StringUtils.isEmpty(paramsObj.getString(GenConstants.TREE_PARENT_CODE))) {
                throw new CustomException("树父编码字段不能为空");
            } else if (StringUtils.isEmpty(paramsObj.getString(GenConstants.TREE_NAME))) {
                throw new CustomException("树名称字段不能为空");
            }
        }
    }

    @Override
    public void updateGenTable(GenTable genTable) {
        String options=JSON.toJSONString(genTable.getParams());
        genTable.setOptions(options);
        int row=genTableMapper.updateGenTable(genTable);
        if (row>0){
            for (GenTableColumn genTableColumn:genTable.getColumns())
                genTableColumnMapper.updateGenTableColumn(genTableColumn);
        }
    }

    @Override
    public void deleteGenTableByIds(Long[] tableIds) {
        genTableMapper.deleteGenTableByIds(tableIds);
        genTableColumnMapper.deleteGenTableColumnByIds(tableIds);
    }

    @Override
    public byte[] generatorCode(String tableName) {
        ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
        ZipOutputStream zip=new ZipOutputStream(outputStream);
        generatorCode(tableName,zip);
        IOUtils.closeQuietly(zip);
        return outputStream.toByteArray();
    }

    @Override
    public byte[] generatorCode(String[] tableName) {
        ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
        ZipOutputStream zip=new ZipOutputStream(outputStream);
        for (String s : tableName) {
            generatorCode(s,zip);
        }
        IOUtils.closeQuietly(zip);
        return outputStream.toByteArray();
    }

    @Override
    public Map<String, String> previewCode(Long tableId) {
        Map<String,String> dataMap=new LinkedHashMap<>();
        GenTable table=genTableMapper.selectGenTableById(tableId);

        List<GenTableColumn> columns=table.getColumns();
        setPKColumn(table,columns);
        VelocityInitializer.initVelocity();

        VelocityContext context= VelocityUtils.prepareContext(table);
        List<String> templateList = VelocityUtils.getTemplateList(table.getTplCategory());
        for (String template : templateList) {
            StringWriter stringWriter = new StringWriter();
            Template template1 = Velocity.getTemplate(template, Constants.UTF8);
            template1.merge(context,stringWriter);
            dataMap.put(template,stringWriter.toString());
        }

        return dataMap;
    }


    public void setTableFromOptions(GenTable genTable) {
        JSONObject paramsObj = JSONObject.parseObject(genTable.getOptions());
        if (StringUtils.isNotNull(paramsObj)) {
            String treeCode = paramsObj.getString(GenConstants.TREE_CODE);
            String treeParentCode = paramsObj.getString(GenConstants.TREE_PARENT_CODE);
            String treeName = paramsObj.getString(GenConstants.TREE_NAME);
            genTable.setTreeCode(treeCode);
            genTable.setTreeParentCode(treeParentCode);
            genTable.setTreeName(treeName);
        }
    }

    public void generatorCode(String tableName,ZipOutputStream zip){
        GenTable table=genTableMapper.selectGenTableByName(tableName);
        long menuId=genTableMapper.selectMenuId();
        table.setMenuId(menuId);
        List<GenTableColumn> columns=table.getColumns();
        setPKColumn(table,columns);
        VelocityInitializer.initVelocity();
        VelocityContext context=VelocityUtils.prepareContext(table);
        //获取模板列表
        List<String> templates=VelocityUtils.getTemplateList(table.getTplCategory());
        for (String template : templates) {
            StringWriter sw=new StringWriter();
            Template tl=Velocity.getTemplate(template,Constants.UTF8);
            tl.merge(context,sw);
            try{
                //添加zip
                zip.putNextEntry(new ZipEntry(VelocityUtils.getFileName(template,table)));
                IOUtils.write(sw.toString(),zip,Constants.UTF8);
                IOUtils.closeQuietly(sw);
//                IOUtils.close(sw);
                zip.flush();
                zip.closeEntry();
            }catch (IOException e){
                log.error("渲染模板失败,表名:"+table.getTableName(),e);
            }
            System.out.println(template);
        }


    }

    public void setPKColumn(GenTable table,List<GenTableColumn>columns){
        for (GenTableColumn column : columns) {
            if (column.isPK()){
                table.setPkColumn(column);
                break;
            }
        }
        if (StringUtils.isNull(table.getPkColumn())){
            table.setPkColumn(columns.get(0));
        }
    }


}
