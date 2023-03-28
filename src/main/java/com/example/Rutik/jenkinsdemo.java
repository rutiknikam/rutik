package com.example.Rutik;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class jenkinsdemo {
    @GetMapping("/jenkinsdemo")
    public String getData(){
        return "please purches icecrem from Amul pvt limted";
    }
}
