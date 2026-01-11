package com.hxl.aspect.service.impl;

import com.hxl.aspect.entity.Room;
import com.hxl.aspect.mapper.RoomMapper;
import com.hxl.aspect.service.RoomService;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class RoomServiceImpl implements RoomService {

    @Resource
    private RoomMapper roomMapper;

    @Override
    public List<Room> getRoom(String tableSuffix) {
        String tableName = validAndGetName(tableSuffix);

        Room room = new Room();
        room.setTableName(tableName);

        return roomMapper.queryRoom(room);
    }


    private static @NonNull String validAndGetName(String tableName) {
        // 校验表名是否合法
        if (!StringUtils.hasText(tableName)) {
            throw new IllegalArgumentException("表名不能为空");
        }
        log.info("校验表名完成...");

        return "t_room_" + tableName;
    }
}
