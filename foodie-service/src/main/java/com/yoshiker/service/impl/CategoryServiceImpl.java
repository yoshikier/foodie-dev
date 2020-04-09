package com.yoshiker.service.impl;

import com.yoshiker.mapper.CategoryMapper;
import com.yoshiker.pojo.Category;
import com.yoshiker.pojo.vo.CategoryVo;
import com.yoshiker.pojo.vo.RecommendItemsVo;
import com.yoshiker.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 查询所有一级分类
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Category> queryAllRootCat() {

        Example categoryExample = new Example(Category.class);
        Example.Criteria categoryCriteria = categoryExample.createCriteria();
        categoryCriteria.andEqualTo("fatherId", 0);

        List<Category> categories = categoryMapper.selectByExample(categoryExample);

        return categories;
    }

    /**
     * 获取子分类 根据一级分类id
     * @param rootCatId
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<CategoryVo> getSubCatList(Integer rootCatId) {
        return categoryMapper.getSubList(rootCatId);
    }

    /**
     * 获取首页商品推荐 根据一级分类id
     * @param rootCatId
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<RecommendItemsVo> getRecommendItems(Integer rootCatId) {

        Map<String, Object> map = new HashMap<>();
        map.put("rootCatId", rootCatId);

        return categoryMapper.getRecommendItems(map);
    }
}
