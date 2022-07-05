package com.tds.project.mapper;


import com.tds.project.domain.GenTable;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GenTableMapper {
    public List<GenTable> selectDbTableListByNames(String[] tableNames);

    public int insertGenTable(GenTable genTable);

    public List<GenTable> selectGenTableList(GenTable genTable);

    public GenTable selectGenTableById(Long id);

    public List<GenTable>selectDbTableList(GenTable genTable);

    public int updateGenTable(GenTable genTable);

    public int deleteGenTableByIds(Long[] ids);


    public GenTable selectGenTableByName(String tableName);

    public long selectMenuId();
}
