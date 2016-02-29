package com.github.eljah.tulpar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.github.eljah.tulpar.service.UserService;

@Controller
public class IndexController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/")
    public String getIndexPage() {
        return "index";
    }


}
