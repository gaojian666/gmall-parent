package com.atguigu.gmall.view;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PageBean<T> implements Serializable{
    private long pageSize;
    private long  totalPage;
    private long total;
    private Integer pageNum;
    private List<T> list;
}
