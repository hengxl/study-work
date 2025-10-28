package com.hxl.domain.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RoomListDTO {

    private String roomName;

    private Integer roomId;

    private Long departId;
}
