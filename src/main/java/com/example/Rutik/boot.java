package com.example.Rutik;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class boot {
    @GetMapping("/myboot")
    public String getData(){
        return "plz check error";
    }

}
