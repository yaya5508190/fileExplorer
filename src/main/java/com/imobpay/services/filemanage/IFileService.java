package com.imobpay.services.filemanage;

import com.imobpay.model.filemanage.MyFile;

import java.util.List;

/**
 * Created by Alucard on 2015/2/3.
 */
public interface IFileService {
    public List<MyFile> getFile(MyFile param);
    public String getContent(MyFile param);
}
