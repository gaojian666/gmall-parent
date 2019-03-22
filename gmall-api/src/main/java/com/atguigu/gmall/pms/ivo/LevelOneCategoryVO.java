package com.atguigu.gmall.pms.ivo;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class LevelOneCategoryVO implements Serializable{
    private Long id;
    private Long parentId;
    private Integer level;
    private String name;
    private List<LevelTwoCategoryVO> children = new ArrayList<LevelTwoCategoryVO>();
}
