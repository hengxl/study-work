package com.hxl.aspect.mapper;

import com.hxl.aspect.entity.Room;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoomMapper {
    /**
     * 查询房间信息
     */
    List<Room> queryRoom(Room room);
}
