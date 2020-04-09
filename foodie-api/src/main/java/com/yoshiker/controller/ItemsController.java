package com.yoshiker.controller;

import com.yoshiker.pojo.Items;
import com.yoshiker.pojo.ItemsImg;
import com.yoshiker.pojo.ItemsParam;
import com.yoshiker.pojo.ItemsSpec;
import com.yoshiker.pojo.bo.CommentsBo;
import com.yoshiker.pojo.vo.ItemCommentCountVo;
import com.yoshiker.pojo.vo.ItemCommentVo;
import com.yoshiker.pojo.vo.ItemsVo;
import com.yoshiker.service.ItemCommentsService;
import com.yoshiker.service.ItemsService;
import com.yoshiker.utils.PagedGridResult;
import com.yoshiker.utils.ToolJSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(value = "商品详情相关接口", tags = {"用于商品详情的相关接口"})
@RestController
@RequestMapping("/items")
public class ItemsController {

    @Autowired
    private ItemsService itemsService;
    @Autowired
    private ItemCommentsService itemCommentsService;

    @ApiOperation(value = "获取商品详情通过商品id", notes = "获取商品详情通过商品id", httpMethod = "GET")
    @GetMapping("/getItemsInfoById/{itemId}")
    public ToolJSONResult getItemsInfoById(
            @ApiParam(name = "itemId", value = "商品id", required = true)
            @PathVariable String itemId) {

        if (itemId == null) {
            return ToolJSONResult.errorMsg("商品不存在！");
        }

        // 获取商品基本信息
        Items items = itemsService.queryItemsInfoById(itemId);

        // 获取商品图片list
        List<ItemsImg> itemsImgList = itemsService.queryItemsImgsById(itemId);

        // 获取商品规格
        List<ItemsSpec> itemsSpecList = itemsService.queryItemsSpecById(itemId);

        // 获取商品参数
        ItemsParam itemsParam = itemsService.queryItemsParamById(itemId);

        ItemsVo itemsVo = new ItemsVo();
        itemsVo.setItems(items);
        itemsVo.setItemsImgs(itemsImgList);
        itemsVo.setItemsSpecs(itemsSpecList);
        itemsVo.setItemsParam(itemsParam);

        return ToolJSONResult.ok(itemsVo);
    }

    @ApiOperation(value = "获取商品评论数", notes = "获取商品评论数", httpMethod = "GET")
    @GetMapping("/getItemCommentCounts/{itemId}")
    public ToolJSONResult getItemCommentCounts(
            @ApiParam(name = "itemId", value = "商品id", required = true)
            @PathVariable String itemId) {

        if (itemId == null) {
            return ToolJSONResult.errorMsg("商品id不能为空！");
        }

        ItemCommentCountVo itemCommentCountVo = itemCommentsService.queryCommentCount(itemId);

        return ToolJSONResult.ok(itemCommentCountVo);
    }

    @ApiOperation(value = "获取商品评论条数", notes = "获取商品评论条数", httpMethod = "POST")
    @PostMapping("/getItemCommentList")
    public ToolJSONResult getItemCommentList(@RequestBody CommentsBo commentsBo) {

        if (commentsBo.getItemId() == null) {
            return ToolJSONResult.errorMsg("商品id不能为空！");
        }

        Map<String, Object> map = new HashMap<>();
        map.put("itemId", commentsBo.getItemId());
        map.put("level", commentsBo.getLevel());

        Integer page = commentsBo.getPage();
        Integer pageSize = commentsBo.getPageSize();

        PagedGridResult pagedGridResult = itemCommentsService.queryItemCommentList(map, page, pageSize);

        return ToolJSONResult.ok(pagedGridResult);
    }


}
