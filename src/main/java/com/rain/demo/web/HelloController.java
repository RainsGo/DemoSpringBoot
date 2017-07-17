package com.rain.demo.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by rain on 17-7-13.
 */
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String index(){
        return "Hello World!";
    }
}
