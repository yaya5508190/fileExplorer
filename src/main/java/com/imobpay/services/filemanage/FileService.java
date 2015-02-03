package com.imobpay.services.filemanage;

import com.imobpay.model.filemanage.MyFile;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alucard on 2014/12/26.
 */
@Service
public class FileService implements IFileService{
    @Override
    public List<MyFile> getFile(MyFile param){
        File baseFile = new File(param.getFilepath());
        List<MyFile> files = new ArrayList<MyFile>();
        MyFile file = null;
        if(baseFile.exists()){
            for(File subFile : baseFile.listFiles()) {
                if (!subFile.isHidden()) {
                    file = new MyFile();
                    file.setFilename(subFile.getName());
                    file.setFilepath(subFile.getPath());
                    if (subFile.isFile()) {
                        file.setFiletype("file");
                        file.setIsParent("false");
                    } else {
                        file.setFiletype("dir");
                        file.setIsParent("true");
                    }
                    files.add(file);
                }
            }
        }
        return files;
    }

    @Override
    public String getContent(MyFile param) {
        File file = new File(param.getFilepath());
        BufferedReader br = null;
        FileReader fr = null;

        String filename = file.getName();
        String[] extend = filename.split("\\.");
        String content = null;
        FileInputStream fis= null;
        byte[] result = null;

        ArrayList<String> arr = new ArrayList<String>();
        arr.add("sql");
        arr.add("csv");
        arr.add("txt");
        arr.add("ini");
        arr.add("java");
        arr.add("properties");
        arr.add("html");

        if(!arr.contains(extend[extend.length-1].toLowerCase())){
            return "不支持该文件";
        }

        try{
             fis = new FileInputStream(file);
             result = new byte[fis.available()];
             fis.read(result);
             content = new String(result);
        }catch (FileNotFoundException e1){
            e1.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                fr.close();
                br.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return content;
    }
}
