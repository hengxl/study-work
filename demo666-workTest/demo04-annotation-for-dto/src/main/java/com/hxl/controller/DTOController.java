package com.hxl.controller;

import com.hxl.domain.dto.UserDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class DTOController {

    @GetMapping("/dto")
    public void getDTO(@Valid @RequestBody UserDTO dto) {
        System.out.println(dto);
    }
}
