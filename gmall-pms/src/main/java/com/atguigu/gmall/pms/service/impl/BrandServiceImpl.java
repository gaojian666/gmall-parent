package com.atguigu.gmall.pms.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.gmall.pms.entity.Brand;
import com.atguigu.gmall.pms.mapper.BrandMapper;
import com.atguigu.gmall.pms.service.BrandService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 品牌表 服务实现类
 * </p>
 *
 * @author Lfy
 * @since 2019-03-19
 */
@Service
@Component
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements BrandService {

    @Override
    public Map<String, Object> getListByBrand(String keyword, Integer pageNum, Integer pageSize) {
        BrandMapper brandMapper = getBaseMapper();
        Page<Brand> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Brand> eq = null;
        if(!StringUtils.isEmpty(keyword)) {
            eq = new QueryWrapper<Brand>().like("name", keyword).eq("first_letter", keyword);
        }
            IPage<Brand> productIPage = brandMapper.selectPage(page, eq);
            Map<String, Object> map = new HashMap<>();
        map.put("pageSize", pageSize);
        map.put("totalPage", productIPage.getPages());
        map.put("total", productIPage.getTotal());
        map.put("pageNum", pageNum);
        map.put("list", productIPage.getRecords());

        return map;
    }

    @Override
    public List<Brand> getListAll() {
        List<Brand> list = getBaseMapper().selectList(null);
        return list;
    }
}
