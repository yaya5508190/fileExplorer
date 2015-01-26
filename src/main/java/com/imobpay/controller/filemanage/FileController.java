package com.imobpay.controller.filemanage;

import com.imobpay.services.filemanage.FileService;
import com.imobpay.model.filemanage.MyFile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/fileManage")
public class FileController {
    FileService fileService;

    @RequestMapping("/page")
    public String page(ModelMap model) {
        return "filemanage/filemanageList";
    }

    @ResponseBody
    @RequestMapping("/queryFileAsList")
    public List<MyFile> queryFileAsList(MyFile file) {
        return fileService.getFile(file.getFilepath());
    }

    @Resource
    public void setFileService(FileService fileService) {
        this.fileService = fileService;
    }
}