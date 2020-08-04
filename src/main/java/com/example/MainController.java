package com.example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    private AnnotationConfigApplicationContext ctx;

    public MainController()
    {
        ctx = new AnnotationConfigApplicationContext(JavaConfig.class);
    }


    @GetMapping(value="/main")
    public String login()
    {
        return "login";
    }

}
