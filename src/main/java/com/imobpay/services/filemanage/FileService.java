package com.imobpay.services.filemanage;

import com.imobpay.model.filemanage.MyFile;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alucard on 2014/12/26.
 */
@Service
public class FileService {
    public List<MyFile> getFile(String path){
        File baseFile = new File(path);
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
}
