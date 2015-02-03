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

    @ResponseBody
    @RequestMapping("/queryFileAsList")
    public List<MyFile> queryFileAsList(MyFile file) {
        return fileService.getFile(file);
    }

    @ResponseBody
    @RequestMapping("/getContent.ajax")
    public String getContent(MyFile file) {
        return fileService.getContent(file);
    }

    @Resource
    public void setFileService(FileService fileService) {
        this.fileService = fileService;
    }
}