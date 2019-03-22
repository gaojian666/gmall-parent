package com.atguigu.gmall.view;

import java.util.HashMap;
import java.util.Map;

public class PageMap {

    public static Map<String,Object> getMap(Integer pageNum,PageBean pageBean){
        Map<String, Object> map = new HashMap<>();
        map.put("pageSize", pageBean.getPageSize());
        map.put("totalPage", pageBean.getTotalPage());
        map.put("total", pageBean.getTotal());
        map.put("pageNum", pageNum);
        map.put("list", pageBean.getList());
        return map;
    }
}
