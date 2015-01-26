package com.imobpay;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Alucard on 2015/1/9.
 */
@Controller
@RequestMapping("/")
public class mainController {
    @RequestMapping
    public String index(){
        return "filemanage/filemanageList";
    }
}
