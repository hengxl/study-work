package com.hxl.aspect.service;

import com.hxl.aspect.entity.Room;

import java.util.List;

public interface RoomService {
    /**
     * 获取 Room 信息
     */
    List<Room> getRoom(String tableSuffix);

}