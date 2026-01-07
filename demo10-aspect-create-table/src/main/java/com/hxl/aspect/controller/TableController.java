package com.hxl.aspect.controller;

import com.hxl.aspect.entity.User;
import com.hxl.aspect.entity.Room;
import com.hxl.aspect.entity.UserDTO;
import com.hxl.aspect.service.RoomService;
import com.hxl.aspect.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class TableController {

    @Resource
    private UserService userService;

    @Resource
    private RoomService roomService;

    @GetMapping("/user")
    public List<User> getUser(@RequestParam("tableSuffix") String tableSuffix) {
        return userService.getUser(tableSuffix);
    }

    @GetMapping("/userDto")
    public List<UserDTO> getUserDto(@RequestParam("tableSuffix") String tableSuffix) {
        return userService.getUserDto(tableSuffix);
    }

    @GetMapping("/room")
    public List<Room> getRoom(@RequestParam("tableSuffix") String tableSuffix) {
        return roomService.getRoom(tableSuffix);
    }
}
