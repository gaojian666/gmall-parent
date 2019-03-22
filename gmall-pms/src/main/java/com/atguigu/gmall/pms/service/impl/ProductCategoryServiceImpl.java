package com.atguigu.gmall.pms.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.gmall.pms.entity.ProductCategory;
import com.atguigu.gmall.pms.ivo.LevelOneCategoryVO;
import com.atguigu.gmall.pms.ivo.LevelThreeCategoryVO;
import com.atguigu.gmall.pms.ivo.LevelTwoCategoryVO;
import com.atguigu.gmall.pms.mapper.ProductCategoryMapper;
import com.atguigu.gmall.pms.service.ProductCategoryService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 产品分类 服务实现类
 * </p>
 *
 * @author Lfy
 * @since 2019-03-19
 */
@Service
@Component
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryMapper, ProductCategory> implements ProductCategoryService {


    @Override
    public List<LevelOneCategoryVO> getList() {
        List<LevelOneCategoryVO> list = new ArrayList<>();
        //先查询一级分类
        QueryWrapper<ProductCategory> wrapper = new QueryWrapper<>();
        wrapper.eq("level",0);
        ProductCategoryMapper productCategoryMapper = getBaseMapper();
        List<ProductCategory> productCategoriesLevelOne = productCategoryMapper.selectList(wrapper);

        //查询二级分类
        QueryWrapper<ProductCategory> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("level",1);
        List<ProductCategory> productCategoriesLevelTwo = productCategoryMapper.selectList(wrapper1);

        //查询三级分类
        QueryWrapper<ProductCategory> wrapper2 = new QueryWrapper<>();
        wrapper2.eq("level",2);
        List<ProductCategory> productCategoriesLevelThree = productCategoryMapper.selectList(wrapper2);

        //遍历一级分类
        int sizeLevelOne = productCategoriesLevelOne.size();
        for (int i = 0; i < sizeLevelOne; i++) {
            ProductCategory productCategoryLevelOne = productCategoriesLevelOne.get(i);
            LevelOneCategoryVO levelOneCategoryVO = new LevelOneCategoryVO();
            BeanUtils.copyProperties(productCategoryLevelOne,levelOneCategoryVO);
            list.add(levelOneCategoryVO);

        //遍历二级分类
            ArrayList<LevelTwoCategoryVO> levelTwoList = new ArrayList<>();
            int sizeLevelTwo = productCategoriesLevelTwo.size();
        for (int j = 0; j < sizeLevelTwo; j++) {
            ProductCategory productCategoryLevelTwo = productCategoriesLevelTwo.get(j);
                LevelTwoCategoryVO levelTwoCategoryVO = new LevelTwoCategoryVO();
            if(productCategoryLevelTwo.getParentId()==productCategoryLevelOne.getId()){
                BeanUtils.copyProperties(productCategoryLevelTwo,levelTwoCategoryVO);
                levelTwoList.add(levelTwoCategoryVO);
                levelOneCategoryVO.setChildren(levelTwoList);
            }

            //遍历三级分类
            ArrayList<LevelThreeCategoryVO> levelThreeList = new ArrayList<>();
            int sizeLevelThree = productCategoriesLevelThree.size();
            for (int k = 0; k < sizeLevelThree; k++) {
                ProductCategory productCategoryLevelThree = productCategoriesLevelThree.get(k);
                if(productCategoryLevelThree.getParentId()==productCategoryLevelTwo.getId()){
                    LevelThreeCategoryVO levelThreeCategoryVO = new LevelThreeCategoryVO();
                    BeanUtils.copyProperties(productCategoryLevelThree,levelThreeCategoryVO);
                    levelThreeList.add(levelThreeCategoryVO);
                    levelTwoCategoryVO.setChildren(levelThreeList);
                }
            }
        }
        }
        return list;
    }

    @Override
    public ProductCategory getProductById(Long id) {
        ProductCategoryMapper productCategoryMapper = getBaseMapper();
        ProductCategory productCategory = productCategoryMapper.selectById(id);
        return productCategory;
    }

    @Override
    public Boolean deleteById(Long id) {
        //判断此分类下面是否有二级分类
        if(this.hasANDNotHasCategory(id)){
            throw new RuntimeException("请先删除下面的分类信息再删除此分类");
        }
        Integer i = getBaseMapper().deleteById(id);
        return i!=null && i>0;
    }

    @Override
    public Map<String, Object> getPageList(Long parentId, Integer pageNum, Integer pageSize) {
        HashMap<String, Object> map = new HashMap<>();
        QueryWrapper<ProductCategory> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id",parentId);
        Page<ProductCategory> page = new Page<>(pageNum, pageSize);
        IPage<ProductCategory> productIPage = getBaseMapper().selectPage(page, wrapper);
        map.put("pageSize", pageSize);
        map.put("totalPage", productIPage.getPages());
        map.put("total", productIPage.getTotal());
        map.put("pageNum", pageNum);
        map.put("list", productIPage.getRecords());

        return map;
    }

    //判断此分类下面是否有二级分类
    public Boolean hasANDNotHasCategory(Long id){
        QueryWrapper<ProductCategory> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id",id);
        ProductCategoryMapper baseMapper = getBaseMapper();
        Integer i = baseMapper.selectCount(wrapper);
        return i!=null && i>0;
    }

}
