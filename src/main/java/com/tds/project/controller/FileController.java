package com.tds.project.controller;

import com.tds.common.utils.ConfigPropertiesUtils;
import com.tds.common.web.domain.server.AjaxResult;

import com.tds.project.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "tds/file/")
public class FileController {
    @Autowired
    private ConfigPropertiesUtils configPropertiesUtils;
    @Autowired
    private FileService fileService;

    public AjaxResult toZip(){
        String toZipPath=configPropertiesUtils.toZipFilePath;
        List<File> listZip=new ArrayList<>();
        String userFile=System.getProperty("user.dir");
        String path="files/toZip/";
        
        //数据库保存路径
        List<String>filelj=null;
        if (!CollectionUtils.isEmpty(filelj)){
            for (String s : filelj) {
                String Commonwjpath=s.replaceAll("/files/tbcommonfj","./tdsFile/tbcommonfjpath");
                String tpPath=userFile+Commonwjpath;
                listZip.add(new File(tpPath));
            }
        }


        String FileName="cs"+".zip";
        String fileZip=toZipPath+FileName;
        File dest=new File(fileZip);
        if (!dest.getParentFile().exists()){
            dest.getParentFile().mkdirs();
        }
        String returnPath=path+FileName;
        OutputStream os=null;
        try{
            os=new FileOutputStream(fileZip);
            if (!CollectionUtils.isEmpty(listZip)){
                fileService.toZip(listZip,os);
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
            return AjaxResult.error("失败");
        }
        return AjaxResult.success("下载成功",returnPath);

    }
}
