package com.tds.project.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ContentStyle;
import com.alibaba.excel.enums.BooleanEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ContentStyle(wrapped = BooleanEnum.TRUE)
public class ExportData {

    @ExcelProperty(value = "id")
    private String id;

    @ExcelProperty(value = "姓名")
    private String a;

    @ExcelProperty(value = "年龄")
    private String b;

    @ExcelProperty(value = "性别")
    private String cc;


    @ExcelProperty(value = "学校")
    private String d;

    @ExcelProperty(value = "地址")
    private String e;

    @ExcelProperty(value = "时间")
    private String f;


}
