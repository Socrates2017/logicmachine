package com.zrzhen.logicmachine.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("")
    public String index() {
        return "index";
    }


    @GetMapping("/factTree/{factId}")
    public String factTree() {
        return "factTree";
    }

    @GetMapping("/factTreeGo/{factId}")
    public String factTreeGo() {
        return "factTreeGo";
    }
}
