package com.tds.common.utils;

import com.tds.common.constant.GenConstants;
import com.tds.common.framework.config.GenConfig;

import com.tds.project.domain.GenTable;
import com.tds.project.domain.GenTableColumn;
import org.apache.commons.lang3.RegExUtils;

import java.util.Arrays;

public class GenUtils {
    public static void initTable(GenTable genTable, String operName){
        genTable.setClassName(convertClassName(genTable.getTableName()));
        genTable.setPackageName(GenConfig.getPackageName());
        genTable.setModuleName(getModuleName(GenConfig.getPackageName()));
        genTable.setBusinessName(getBusinessName(genTable.getTableName()));
        genTable.setFunctionName(replaceText(genTable.getTableComment()));
        genTable.setFunctionAuthor(GenConfig.getAuthor());
        genTable.setCreateBy(operName);

    }

    public static void initColumnField(GenTableColumn column, GenTable table) {
        String dataType = getDbType(column.getColumnType());
        String columnName = column.getColumnName();
        column.setTableId(table.getTableId());
        column.setCreateBy(table.getCreateBy());
        column.setJavaField(StringUtils.toCamelCase(columnName));
        if (arraysContains(GenConstants.COLUMNTYPE_STR, dataType)) {
            column.setJavaType(GenConstants.TYPE_STRING);
            Integer columnLength = getColumnLength(column.getColumnType());
            String htmlType = columnLength >= 500 ? GenConstants.HTML_TEXTAREA : GenConstants.HTML_INPUT;
            column.setHtmlType(htmlType);
        } else if (arraysContains(GenConstants.COLUMNTYPE_TIME, dataType)) {
            column.setJavaType(GenConstants.TYPE_DATE);
            column.setHtmlType(GenConstants.HTML_DATETIME);
        } else if (arraysContains(GenConstants.COLUMNNTYPE_NUMBER, dataType)) {
            column.setHtmlType(GenConstants.HTML_INPUT);

            String[] str = StringUtils.split(StringUtils.substringBetween(column.getColumnType(), "(",")"),",");
            if (str != null && str.length == 2 && Integer.parseInt(str[1]) > 0) {
                column.setJavaType(GenConstants.TYPE_DOUBLE);

            } else if (str != null && str.length == 1 && Integer.parseInt(str[0]) <= 10) {
                column.setJavaType(GenConstants.TYPE_INTEGER);
            } else {
                column.setJavaType(GenConstants.TYPE_LONG);
            }
        }
        column.setIsInsert(GenConstants.REQUIRE);

        if (!arraysContains(GenConstants.COLUMNNAME_NOT_EDIT,columnName)&&!column.isPK()){
            column.setIsEdit(GenConstants.REQUIRE);
        }
        if (!arraysContains(GenConstants.COLUMMNAME_NOT_LIST,columnName)&&!column.isPK()){
            column.setIsList(GenConstants.REQUIRE);
        }

        if (!arraysContains(GenConstants.COLUMMNAME_NOT_QUERY,columnName)&&column.isPK()){
            column.setIsInsert(GenConstants.REQUIRE);
        }
        if (StringUtils.endsWithIgnoreCase(columnName, "name")) {
            column.setQueryType(GenConstants.QUERY_LIKE);
        }
        if (StringUtils.endsWithIgnoreCase(columnName, "status")) {
            column.setHtmlType(GenConstants.HTML_RADIO);
        } else if (StringUtils.endsWithIgnoreCase(columnName, "type") ||
        StringUtils.endsWithIgnoreCase(columnName,"sex")) {
            column.setHtmlType(GenConstants.HTML_SELECT);
        }
    }

    public static String convertClassName(String tableName){
        boolean autoRemovePre= GenConfig.getAutoRemovePre();
//        System.out.println(GenConfig.getAuthor());
        String tablePrefix=GenConfig.getTablePrefix();
        if (autoRemovePre&&StringUtils.isNotEmpty(tablePrefix));
        {
            String[] searchList=StringUtils.split(tablePrefix,",");
            tableName=replaceFirst(tableName,searchList);
        }
        return StringUtils.convertToCamelCase(tableName);
    }

    public static String replaceFirst(String replacementmn,String[] searchList){
        String text=replacementmn;
        for (String s : searchList) {
            if (replacementmn.startsWith(s)){
                text=replacementmn.replaceFirst(s,"");
                break;
            }
        }
        return text;
    }

    public static String getModuleName(String packageName){
        int lastIndex=packageName.lastIndexOf(".");
        int nameLength=packageName.length();
        String moduleName=StringUtils.substring(packageName,lastIndex+1,nameLength);
        return moduleName;
    }
    public static String getBusinessName(String tableName){
        int lastIndex=tableName.lastIndexOf("_");
        int nameLength=tableName.length();
        String businessName=StringUtils.substring(tableName,lastIndex+1,nameLength);
        return businessName;
    }

    public static String replaceText(String text){
        return RegExUtils.replaceAll(text,"(?:表|若依)","");
    }

    public static String getDbType(String columnType){
        if (StringUtils.indexOf(columnType,"(")>0){
            return StringUtils.substringBefore(columnType,"(");
        }else {
            return columnType;
        }
    }

    public static boolean arraysContains(String[] arr,String tar){
        return Arrays.asList(arr).contains(tar);
    }
    public static Integer getColumnLength(String columnType){
        if (StringUtils.indexOf(columnType,"(")>0){
            String length=StringUtils.substringBetween(columnType,"(",")");
            return Integer.valueOf(length);
        }else {
            return 0;
        }
    }
}
