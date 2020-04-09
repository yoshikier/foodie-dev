package com.yoshiker.controller;

import com.yoshiker.enums.YesOrNo;
import com.yoshiker.pojo.Carousel;
import com.yoshiker.pojo.Category;
import com.yoshiker.pojo.vo.CategoryVo;
import com.yoshiker.pojo.vo.RecommendItemsVo;
import com.yoshiker.service.CarouselService;
import com.yoshiker.service.CategoryService;
import com.yoshiker.utils.ToolJSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "首页相关接口", tags = {"用于首页的相关接口"})
@RestController
@RequestMapping("/index")
public class IndexController {

    @Autowired
    private CarouselService carouselService;
    @Autowired
    private CategoryService categoryService;

    @ApiOperation(value = "获取轮播图列表", notes = "获取轮播图列表" )
    @GetMapping("/queryCarouselList")
    public ToolJSONResult queryCarouselList() {

        List<Carousel> carousels = carouselService.getAll(YesOrNo.YES.type);

        return ToolJSONResult.ok(carousels);
    }

    @ApiOperation(value = "获取所有商品一级分类", notes = "获取所有商品一级分类" )
    @GetMapping("/queryAllRootCatList")
    public ToolJSONResult queryAllRootCatList() {

        List<Category> categories = categoryService.queryAllRootCat();

        return ToolJSONResult.ok(categories);
    }

    @ApiOperation(value = "获取商品子分类", notes = "获取商品子分类", httpMethod = "GET")
    @GetMapping("/getSubCatList/{rootCatId}")
    public ToolJSONResult getSubCatList(
            @ApiParam(name = "rootCatId", value = "一级分类id", required = true)
            @PathVariable Integer rootCatId) {

        if (rootCatId == null) {
            return ToolJSONResult.errorMsg("分类不存在");
        }

        List<CategoryVo> list = categoryService.getSubCatList(rootCatId);
        return ToolJSONResult.ok(list);
    }

    @ApiOperation(value = "获取首页商品推荐", notes = "获取首页商品推荐", httpMethod = "GET")
    @GetMapping("/getRecommendItems/{rootCatId}")
    public ToolJSONResult getRecommendItems(
            @ApiParam(name = "rootCatId", value = "一级分类id", required = true)
            @PathVariable Integer rootCatId) {

        if (rootCatId == null) {
            return ToolJSONResult.errorMsg("分类不存在");
        }

        List<RecommendItemsVo> list = categoryService.getRecommendItems(rootCatId);
        RecommendItemsVo item = list.get(0);
        return ToolJSONResult.ok(item);
    }


}
