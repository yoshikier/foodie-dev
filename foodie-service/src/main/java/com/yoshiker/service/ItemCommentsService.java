package com.yoshiker.service;

import com.yoshiker.pojo.vo.ItemCommentCountVo;
import com.yoshiker.utils.PagedGridResult;

import java.util.Map;

public interface ItemCommentsService {

    /**
     * 根据商品id查询商品评论数
     * @param itemId
     * @return
     */
    public ItemCommentCountVo queryCommentCount(String itemId);

    /**
     * 根据商品id查询商品评论条数
     * @param map
     * @return
     */
    public PagedGridResult queryItemCommentList(Map<String, Object> map, Integer page, Integer pageSize);


}
