package com.yoshiker.pojo.vo;

import com.yoshiker.pojo.Items;
import com.yoshiker.pojo.ItemsImg;
import com.yoshiker.pojo.ItemsParam;
import com.yoshiker.pojo.ItemsSpec;

import java.util.List;

/**
 * 商品详情Vo
 */
public class ItemsVo {
    private Items items;
    private List<ItemsImg> itemsImgs;
    private List<ItemsSpec> itemsSpecs;
    private ItemsParam itemsParam;

    public Items getItems() {
        return items;
    }

    public void setItems(Items items) {
        this.items = items;
    }

    public List<ItemsImg> getItemsImgs() {
        return itemsImgs;
    }

    public void setItemsImgs(List<ItemsImg> itemsImgs) {
        this.itemsImgs = itemsImgs;
    }

    public List<ItemsSpec> getItemsSpecs() {
        return itemsSpecs;
    }

    public void setItemsSpecs(List<ItemsSpec> itemsSpecs) {
        this.itemsSpecs = itemsSpecs;
    }

    public ItemsParam getItemsParam() {
        return itemsParam;
    }

    public void setItemsParam(ItemsParam itemsParam) {
        this.itemsParam = itemsParam;
    }
}
