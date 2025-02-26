package com.example.Simple_API.Controller;

import com.example.Simple_API.Service.Hello_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hello_Controller {
    @Autowired
    Hello_Service service;

    @GetMapping("/")
    public String hello(){
        return service.hello();
    }

    @GetMapping("/about")
    public String about(){
        return "content of about page \n";
    }

}
