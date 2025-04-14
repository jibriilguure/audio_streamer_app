package com.audiostreamer.app.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class hollowApi {
    @GetMapping("/hellow")
 public String hollowApi() {
    return " testing ";
 }


}
