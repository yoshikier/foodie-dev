package com.yoshiker.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@ApiIgnore // 不在api文档中显示该接口
public class HelloController {

    @GetMapping("/hello")
    public Object hello() {
        return "hello world!";
    }
}
