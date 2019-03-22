package com.atguigu.gmall.pms.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.gmall.pms.entity.Product;
import com.atguigu.gmall.pms.mapper.ProductMapper;
import com.atguigu.gmall.pms.service.ProductService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Component;


import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 商品信息 服务实现类
 * </p>
 *
 * @author Lfy
 * @since 2019-03-19
 */
@Service
@Component
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    @Override
    public Map<String, Object> getPageList(Integer pageSize, Integer pageNum) {
        ProductMapper productMapper = getBaseMapper();
        Page<Product> page = new Page<>(pageNum, pageSize);
        IPage<Product> productIPage = productMapper.selectPage(page, null);
        Map<String,Object> map = new HashMap<>();
        map.put("pageSize",pageSize);
        map.put("totalPage",productIPage.getPages());
        map.put("total",productIPage.getTotal());
        map.put("pageNum",pageNum);
        map.put("list",productIPage.getRecords());
        return map;
    }
}
