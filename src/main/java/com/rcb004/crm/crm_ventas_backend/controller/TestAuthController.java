package com.rcb004.crm.crm_ventas_backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/auth")
public class TestAuthController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello from Auth Controller!";
    }
    

    @GetMapping("/hello-secured")
    public String helloSecured() {
        return "Hello from Secured Auth Controller!";
    }

}
