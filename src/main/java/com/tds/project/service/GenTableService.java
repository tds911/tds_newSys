package com.tds.project.service;


import com.tds.project.domain.GenTable;

import java.util.List;
import java.util.Map;

public interface GenTableService {

    public List<GenTable> selectDbTableListByNames(String[] tableNames);
    public void importGenTable(List<GenTable>tableList);

    public List<GenTable> selectGenTableList(GenTable genTable);

    public GenTable selectGenTableById(Long id);

    public List<GenTable> selectDbTableList(GenTable genTable);

    public void validateEdit(GenTable genTable);

    public void updateGenTable(GenTable genTable);

    public void deleteGenTableByIds(Long[] tableIds);

    public byte[] generatorCode(String tableName);
    public byte[] generatorCode(String[] tableName);

    public Map<String,String>previewCode(Long tableId);



}
