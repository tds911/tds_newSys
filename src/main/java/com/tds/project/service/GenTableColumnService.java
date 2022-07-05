package com.tds.project.service;


import com.tds.project.domain.GenTableColumn;

import java.util.List;

public interface GenTableColumnService {
    List<GenTableColumn> selectGenTableColumnListByTableId(Long tableId);
}
