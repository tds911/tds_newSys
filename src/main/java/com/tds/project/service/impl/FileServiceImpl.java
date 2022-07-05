package com.tds.project.service.impl;


import com.tds.project.service.FileService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static org.springframework.util.StreamUtils.BUFFER_SIZE;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public void toZip(List<File> srcFiles, OutputStream out) {
        ZipOutputStream zos=null;
        try {
            zos = new ZipOutputStream(out);
            for (File srcFile : srcFiles) {
                byte[] buf = new byte[BUFFER_SIZE];
                zos.putNextEntry(new ZipEntry(srcFile.getName()));
                int len;
                FileInputStream in = new FileInputStream(srcFile);
                while ((len = in.read(buf)) != -1) {
                    zos.write(buf, 0, len);
                }
                zos.closeEntry();
                in.close();
            }
        }catch (Exception e){
            throw new RuntimeException("压缩失败",e);
        }finally {
            if (zos!=null){
                try{
                    zos.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
