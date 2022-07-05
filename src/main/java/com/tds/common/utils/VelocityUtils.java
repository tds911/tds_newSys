package com.tds.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.tds.common.constant.GenConstants;
import com.tds.project.domain.GenTable;
import com.tds.project.domain.GenTableColumn;
import org.apache.velocity.VelocityContext;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class VelocityUtils {
    private static final String PROJECT_PATH="main/java";
    private static final String MYBATIS_PATH="main/resources/mybatis";

    public static VelocityContext prepareContext(GenTable genTable){
        String moduleName=genTable.getModuleName();
        String businessName=genTable.getBusinessName();
        String packageName = genTable.getPackageName();
        String tplCategory = genTable.getTplCategory();
        String functionName = genTable.getFunctionName();

        VelocityContext velocityContext=new VelocityContext();
        velocityContext.put("tplCategory",genTable.getTplCategory());
        velocityContext.put("tableName",genTable.getTableName());
        velocityContext.put("functionName",StringUtils.isNotEmpty(functionName)?functionName:"【请填写功能名称】");
        velocityContext.put("ClassName",genTable.getClassName());
        velocityContext.put("className",StringUtils.uncapitalize(genTable.getClassName()));
        velocityContext.put("moduleName",genTable.getModuleName());
        velocityContext.put("BusinessName",StringUtils.capitalize(genTable.getBusinessName()));
        velocityContext.put("businessName",genTable.getBusinessName());
        velocityContext.put("basePackage",getPackagePrefix(packageName));
        velocityContext.put("packageName",packageName);
        velocityContext.put("author",genTable.getFunctionAuthor());
        velocityContext.put("datetime",DateUtils.getDate());
        velocityContext.put("pkColumn",genTable.getPkColumn());
        velocityContext.put("importList",getImportList(genTable.getColumns()));
        velocityContext.put("permissionPrefix",getPermissionPrefix(moduleName,businessName));
        velocityContext.put("columns",genTable.getColumns());
        velocityContext.put("table",genTable);
        if (GenConstants.TPL_TREE.equals(tplCategory)){
            setTreeVelocityContext(velocityContext,genTable);
        }
        return velocityContext;
    }


    public static List<String> getTemplateList(String tplCategory){
        List<String> templates=new ArrayList<String>();
        templates.add("vm/java/domain.java.vm");
        templates.add("vm/java/mapper.java.vm");
        templates.add("vm/java/service.java.vm");
        templates.add("vm/java/serviceImpl.java.vm");
        templates.add("vm/java/controller.java.vm");
        templates.add("vm/xml/mapper.xml.vm");
        if (GenConstants.TPL_CRUD.equals(tplCategory)){
            templates.add("vm/vue/index-tree.vue.vm");
        }
        return templates;
    }

    public static String getFileName(String template,GenTable genTable){
        String fileName="";
        String packageName=genTable.getPackageName();
        String moduleName=genTable.getModuleName();
        String className=genTable.getClassName();
        String businessName = genTable.getBusinessName();
        String javaPath=PROJECT_PATH+"/"+StringUtils.replace(packageName,".","/");
        String mybatisPath=MYBATIS_PATH+"/"+moduleName;
        String vuePath="vue";
        if (template.contains("domain.java.vm")){
            fileName=StringUtils.format("{}/domain/{}.java",javaPath,className);
        }
        else if (template.contains("mapper.java.vm")){
            fileName=StringUtils.format("{}/mapper/{}Mapper.java",javaPath,className);
        }else if (template.contains("service.java.vm")){
            fileName=StringUtils.format("{}/service/{}Service.java",javaPath,className);
        }else if (template.contains("serviceImpl.java.vm")){
            fileName=StringUtils.format("{}/service/impl/{}ServiceImpl.java",javaPath,className);
        }else if (template.contains("controller.java.vm")){
            fileName=StringUtils.format("{}/controller/{}Controller.java",javaPath,className);
        }else if (template.contains("mapper.xml.vm")){
            fileName=StringUtils.format("{}/{}Mapper.xml",mybatisPath,className);
        }else if (template.contains("sql.vm")){
            fileName=businessName+"Menu.sql";
        }else if (template.contains("api.js.vm")){
            fileName=StringUtils.format("{}/api/{}/{}.js",vuePath,moduleName,businessName);
        }else if (template.contains("index.vue.vm")){
            fileName=StringUtils.format("{}/views/{}/{}/index.vue",vuePath,moduleName,businessName);
        }else if (template.contains("index-tree.vue.vm")){
            fileName=StringUtils.format("{}/views/{}/{}/index.vue",vuePath,moduleName,businessName);
        }
        return fileName;
    }


    public static String getPackagePrefix(String packageName){
        int lastIndex=packageName.lastIndexOf(".");
        String basePackage=StringUtils.substring(packageName,0,lastIndex);
        return basePackage;
    }


    public static HashSet<String> getImportList(List<GenTableColumn>columns){
        HashSet<String> importList=new HashSet<String>();
        for (GenTableColumn column : columns) {
            if (!column.isSuperColumn()&& GenConstants.TYPE_DATE.equals(column.getJavaType())){
                importList.add("java.util.Date");
                importList.add("com.fasterxml.jackson.annotation.JsonFormat");
            }
            else if (!column.isSuperColumn()&&GenConstants.TYPE_BIGDECIMAL.equals(column.getJavaType())){
                importList.add("java.math.BigDecimal");
            }
        }
        return importList;
    }

    public static String getPermissionPrefix(String moduleName,String businessName){
        return StringUtils.format("{}:{}",moduleName,businessName);
    }


    public static void setTreeVelocityContext(VelocityContext context,GenTable genTable){
        String options=genTable.getOptions();
        JSONObject paramsObj=JSONObject.parseObject(options);
        String treeCode=getTreeCode(paramsObj);
        String treeParentCode=getTreeParentCode(paramsObj);
        String treeName=getTreeName(paramsObj);

        context.put("treeCode",treeCode);
        context.put("treeParentCode",treeParentCode);
        context.put("treeName",treeName);
        context.put("expandColumn",getExpandColumn(genTable));
        if (paramsObj.containsKey(GenConstants.TREE_PARENT_CODE)){
            context.put("tree_parent_code",paramsObj.getString(GenConstants.TREE_PARENT_CODE));
        }
        if (paramsObj.containsKey(GenConstants.TREE_NAME)){
            context.put("tree_name",paramsObj.getString(GenConstants.TREE_NAME));
        }
    }

    public static String getTreeCode(JSONObject paramsObj){
        if (paramsObj.containsKey(GenConstants.TREE_CODE)){
            return StringUtils.toCamelCase(paramsObj.getString(GenConstants.TREE_CODE));
        }
        return "";
    }

    public static String getTreeParentCode(JSONObject paramsObj){
        if (paramsObj.containsKey(GenConstants.TREE_PARENT_CODE)){
            return StringUtils.toCamelCase(paramsObj.getString(GenConstants.TREE_PARENT_CODE));
        }
        return "";
    }

    public static String getTreeName(JSONObject paramsObj){
        if (paramsObj.containsKey(GenConstants.TREE_NAME)){
            return StringUtils.toCamelCase(paramsObj.getString(GenConstants.TREE_NAME));
        }
        return "";
    }

    public static int getExpandColumn(GenTable genTable){
        String options=genTable.getOptions();
        JSONObject paramsObj=JSONObject.parseObject(options);
        String treeName=paramsObj.getString(GenConstants.TREE_NAME);
        int num=0;
        for (GenTableColumn column : genTable.getColumns()) {
            if (column.isList()){
                num++;
                String columnName=column.getColumnName();
                if (columnName.equals(treeName)){
                    break;
                }
            }
        }
        return num;
    }
}
