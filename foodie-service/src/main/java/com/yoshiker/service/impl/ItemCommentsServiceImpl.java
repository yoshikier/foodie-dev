package com.yoshiker.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yoshiker.enums.CommentLevel;
import com.yoshiker.mapper.ItemsCommentsMapper;
import com.yoshiker.pojo.ItemsComments;
import com.yoshiker.pojo.vo.ItemCommentCountVo;
import com.yoshiker.pojo.vo.ItemCommentVo;
import com.yoshiker.service.ItemCommentsService;
import com.yoshiker.utils.DesensitizationUtil;
import com.yoshiker.utils.PagedGridResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class ItemCommentsServiceImpl implements ItemCommentsService {

    @Autowired
    ItemsCommentsMapper itemsCommentsMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public ItemCommentCountVo queryCommentCount(String itemId) {

        Integer goodCounts = getItemsCommentCount(itemId, CommentLevel.GOOD.type);
        Integer normalCounts = getItemsCommentCount(itemId, CommentLevel.NORMAL.type);
        Integer badCounts = getItemsCommentCount(itemId, CommentLevel.BAD.type);
        Integer totalCounts = goodCounts + normalCounts + badCounts;

        ItemCommentCountVo itemCommentCountVo = new ItemCommentCountVo();
        itemCommentCountVo.setTotalCounts(totalCounts);
        itemCommentCountVo.setGoodCounts(goodCounts);
        itemCommentCountVo.setNormalCounts(normalCounts);
        itemCommentCountVo.setBadCounts(badCounts);

        return itemCommentCountVo;
    }

    private Integer getItemsCommentCount(String itemId, Integer level) {

        ItemsComments itemsComments = new ItemsComments();
        itemsComments.setItemId(itemId);
        itemsComments.setCommentLevel(level);

        return itemsCommentsMapper.selectCount(itemsComments);
    }

    @Override
    public PagedGridResult queryItemCommentList(Map<String, Object> map, Integer page, Integer pageSize) {

        PageHelper.startPage(page, pageSize);

        List<ItemCommentVo> list = itemsCommentsMapper.queryItemCommentsList(map);

        // 为用户昵称设置脱敏（y****ki）
        for(ItemCommentVo item : list) {
            item.setNickName(DesensitizationUtil.commonDisplay(item.getNickName()));
        }

        PageInfo<?> pageList = new PageInfo<>(list);

        PagedGridResult pagedGridResult = new PagedGridResult();
        pagedGridResult.setPage(page);
        pagedGridResult.setRows(list);
        pagedGridResult.setPageTotal(pageList.getPages());
        pagedGridResult.setCounts(pageList.getTotal());

        return pagedGridResult;
    }
}
