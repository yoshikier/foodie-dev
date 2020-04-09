package com.yoshiker.pojo.vo;

import java.util.List;

/**
 * 首页顶部商品分类Vo
 */
public class CategoryVo {
    private Integer id;
    private String name;
    private String type;
    private Integer fatherId;
    private List<CategorySubVo> subList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getFatherId() {
        return fatherId;
    }

    public void setFatherId(Integer fatherId) {
        this.fatherId = fatherId;
    }

    public List<CategorySubVo> getSubList() {
        return subList;
    }

    public void setSubList(List<CategorySubVo> subList) {
        this.subList = subList;
    }
}
