package com.hxl.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class TestController {

    @GetMapping
    public void getMapping(@RequestParam(value = "roomID", required = false) Integer roomID,
                           @RequestParam(value = "roomName", required = false) String roomName,
                           @RequestParam(value = "departID", required = false) Long departID) {
        System.out.println(roomID);
        System.out.println(roomName);
        System.out.println(departID);
    }
}
