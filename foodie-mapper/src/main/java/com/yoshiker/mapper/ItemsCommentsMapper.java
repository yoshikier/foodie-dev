package com.yoshiker.mapper;

import com.yoshiker.my.mapper.MyMapper;
import com.yoshiker.pojo.ItemsComments;
import com.yoshiker.pojo.vo.ItemCommentVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ItemsCommentsMapper extends MyMapper<ItemsComments> {
    public List<ItemCommentVo> queryItemCommentsList(@Param("paramsMap") Map<String, Object> map);

}