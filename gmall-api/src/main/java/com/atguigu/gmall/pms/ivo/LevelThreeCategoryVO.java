package com.atguigu.gmall.pms.ivo;

import lombok.Data;

import java.io.Serializable;

@Data
public class LevelThreeCategoryVO implements Serializable{
    private Long id;
    private Long parentId;
    private Integer level;
    private String name;
}
