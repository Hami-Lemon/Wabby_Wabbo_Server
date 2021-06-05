package com.github.lemon.wabby.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p> 创建时间 2021/6/1 </p>
 *
 * @author Hami Lemon
 * @version v1.0
 */
@RestController
public class Demo {
    @RequestMapping(value = "/demo", produces = "application/json;charset=UTF-8")
    public String demo() {
        return "demo";
    }
}
