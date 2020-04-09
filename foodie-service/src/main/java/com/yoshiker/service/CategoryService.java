package com.yoshiker.service;


import com.yoshiker.pojo.Category;
import com.yoshiker.pojo.vo.CategoryVo;
import com.yoshiker.pojo.vo.RecommendItemsVo;

import java.util.List;

public interface CategoryService {
    /**
     * 获取所有一级分类
     */
    public List<Category> queryAllRootCat();

    /**
     * 获取子分类
     */
    public List<CategoryVo> getSubCatList(Integer rootCatId);

    /**
     * 获取首页商品推荐
     */
    public List<RecommendItemsVo> getRecommendItems(Integer rootCatId);

}
