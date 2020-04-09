package com.yoshiker.service;


import com.yoshiker.pojo.Items;
import com.yoshiker.pojo.ItemsImg;
import com.yoshiker.pojo.ItemsParam;
import com.yoshiker.pojo.ItemsSpec;

import java.util.List;

public interface ItemsService {

    /**
     * 根据商品id查询商品基本信息
     * @param itemId
     * @return
     */
    public Items queryItemsInfoById(String itemId);

    /**
     * 根据商品id查询商品图片
     * @param itemId
     * @return
     */
    public List<ItemsImg> queryItemsImgsById(String itemId);

    /**
     * 根据商品id查询商品规格
     * @param itemId
     * @return
     */
    public List<ItemsSpec> queryItemsSpecById(String itemId);


    /**
     * 根据商品id查询商品参数
     * @param itemId
     * @return
     */
    public ItemsParam queryItemsParamById(String itemId);

}
