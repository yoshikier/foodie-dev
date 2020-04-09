package com.yoshiker.pojo.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "评论条数对象", description = "从客户端，由用户传入的数据封装在此entity中")
public class CommentsBo {

    @ApiModelProperty(value = "商品id", name = "itemId", example = "cake-1001", required = true)
    public String itemId;
    @ApiModelProperty(value = "评价等级", name = "level", example = "（1/2/3/null）", required = false)
    public Integer level;
    @ApiModelProperty(value = "当前页码", name = "page", example = "1", required = true)
    public Integer page;
    @ApiModelProperty(value = "查询页数", name = "pageSize", example = "10", required = true)
    public Integer pageSize;

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
