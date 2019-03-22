package com.atguigu.gmall.pms.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.gmall.pms.entity.ProductAttributeCategory;
import com.atguigu.gmall.pms.mapper.ProductAttributeCategoryMapper;
import com.atguigu.gmall.pms.service.ProductAttributeCategoryService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


/**
 * <p>
 * 产品属性分类表 服务实现类
 * </p>
 *
 * @author Lfy
 * @since 2019-03-19
 */
@Service
@Component
public class ProductAttributeCategoryServiceImpl extends ServiceImpl<ProductAttributeCategoryMapper, ProductAttributeCategory> implements ProductAttributeCategoryService {

    @Override
    public Map<String, Object> getPageList(Integer pageNum, Integer pageSize) {
        HashMap<String, Object> map = new HashMap<>();
        Page<ProductAttributeCategory> page = new Page<>(pageNum,pageSize);
        IPage<ProductAttributeCategory> productIPage = getBaseMapper().selectPage(page, null);
        map.put("pageSize", pageSize);
        map.put("totalPage", productIPage.getPages());
        map.put("total", productIPage.getTotal());
        map.put("pageNum", pageNum);
        map.put("list", productIPage.getRecords());
        return map;
    }
}
