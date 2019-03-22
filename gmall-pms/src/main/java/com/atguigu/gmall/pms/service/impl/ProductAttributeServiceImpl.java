package com.atguigu.gmall.pms.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.gmall.pms.entity.ProductAttribute;
import com.atguigu.gmall.pms.mapper.ProductAttributeMapper;
import com.atguigu.gmall.pms.service.ProductAttributeService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 商品属性参数表 服务实现类
 * </p>
 *
 * @author Lfy
 * @since 2019-03-19
 */
@Service
@Component
public class ProductAttributeServiceImpl extends ServiceImpl<ProductAttributeMapper, ProductAttribute> implements ProductAttributeService {

    @Override
    public Map<String,Object> getAttributeList(Long cid, Integer type, Integer pageNum, Integer pageSize) {
        QueryWrapper<ProductAttribute> wrapper = new QueryWrapper<>();
        wrapper.eq("product_attribute_category_id",cid).eq("type",type);
        Page<ProductAttribute> page = new Page<>(pageNum, pageSize);
        IPage<ProductAttribute> productIPage = getBaseMapper().selectPage(page, wrapper);
        Map<String, Object> map = new HashMap<>();
        map.put("pageSize", pageSize);
        map.put("totalPage", productIPage.getPages());
        map.put("total", productIPage.getTotal());
        map.put("pageNum", pageNum);
        map.put("list", productIPage.getRecords());

        return map;
    }
}
