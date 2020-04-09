package com.yoshiker.service.impl;

import com.yoshiker.mapper.ItemsImgMapper;
import com.yoshiker.mapper.ItemsMapper;
import com.yoshiker.mapper.ItemsParamMapper;
import com.yoshiker.mapper.ItemsSpecMapper;
import com.yoshiker.pojo.Items;
import com.yoshiker.pojo.ItemsImg;
import com.yoshiker.pojo.ItemsParam;
import com.yoshiker.pojo.ItemsSpec;
import com.yoshiker.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class ItemsServiceImpl implements ItemsService {

    @Autowired
    private ItemsMapper itemsMapper;
    @Autowired
    private ItemsImgMapper itemsImgMapper;
    @Autowired
    private ItemsSpecMapper itemsSpecMapper;
    @Autowired
    private ItemsParamMapper itemsParamMapper;



    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Items queryItemsInfoById(String itemId) {

        Items items = itemsMapper.selectByPrimaryKey(itemId);

        return items;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<ItemsImg> queryItemsImgsById(String itemId) {

        Example itemsImgExample = new Example(ItemsImg.class);
        Example.Criteria criteria = itemsImgExample.createCriteria();
        criteria.andEqualTo("itemId", itemId);

        return itemsImgMapper.selectByExample(itemsImgExample);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<ItemsSpec> queryItemsSpecById(String itemId) {

        Example itemsSpecExample = new Example(ItemsSpec.class);
        Example.Criteria criteria = itemsSpecExample.createCriteria();
        criteria.andEqualTo("itemId", itemId);

        return itemsSpecMapper.selectByExample(itemsSpecExample);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public ItemsParam queryItemsParamById(String itemId) {

        Example paramExample = new Example(ItemsParam.class);
        Example.Criteria paramExampleCriteria = paramExample.createCriteria();

        paramExampleCriteria.andEqualTo("itemId", itemId);

        return itemsParamMapper.selectOneByExample(paramExample);

    }
}
