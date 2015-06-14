package com.github.larchaon.loanapp.util;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String hello() {
        return "Hello World";
    }
}
