package com.hxl.remote.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Score implements Serializable {

    private static final long serialVersionUID = 1L;

    private String scoreName;

    private Double grade;
}
