package com.yoshiker.service;


import com.yoshiker.pojo.Carousel;

import java.util.List;

public interface CarouselService {
    /**
     * 查询轮播图
     */
    public List<Carousel> getAll(Integer isShow);

}
