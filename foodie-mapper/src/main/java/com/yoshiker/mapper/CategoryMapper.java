package com.yoshiker.mapper;

import com.yoshiker.my.mapper.MyMapper;
import com.yoshiker.pojo.Category;
import com.yoshiker.pojo.vo.CategoryVo;
import com.yoshiker.pojo.vo.RecommendItemsVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CategoryMapper extends MyMapper<Category> {

    public List<CategoryVo> getSubList(Integer rootCatId);

    public List<RecommendItemsVo> getRecommendItems(@Param("itemsMap") Map<String, Object> map);
}
