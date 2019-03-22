package com.atguigu.gmall.pms.service;

import com.atguigu.gmall.pms.entity.ProductCategory;
import com.atguigu.gmall.pms.ivo.LevelOneCategoryVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 产品分类 服务类
 * </p>
 *
 * @author Lfy
 * @since 2019-03-19
 */
public interface ProductCategoryService extends IService<ProductCategory> {


    List<LevelOneCategoryVO> getList();

    ProductCategory getProductById(Long id);

    Boolean deleteById(Long id);

    Map<String,Object> getPageList(Long parentId, Integer pageNum, Integer pageSize);
}
