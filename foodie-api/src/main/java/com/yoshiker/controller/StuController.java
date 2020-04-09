package com.yoshiker.controller;

import com.yoshiker.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@ApiIgnore // 不在api文档中显示该接口
public class StuController {

    @Autowired
    private StuService stuService;

    @GetMapping("/getStu")
    public Object getStu(int id) {

        return stuService.getStuInfo(id);
    }
}
