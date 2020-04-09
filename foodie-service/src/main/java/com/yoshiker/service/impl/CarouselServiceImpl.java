package com.yoshiker.service.impl;

import com.yoshiker.mapper.CarouselMapper;
import com.yoshiker.pojo.Carousel;
import com.yoshiker.service.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class CarouselServiceImpl implements CarouselService {

    @Autowired
    private CarouselMapper carouselMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Carousel> getAll(Integer isShow) {

        Example carouselExample = new Example(Carousel.class);
        carouselExample.orderBy("sort").desc();
        Example.Criteria carouselCriteria = carouselExample.createCriteria();
        carouselCriteria.andEqualTo("isShow", isShow);

        List<Carousel> carousels = carouselMapper.selectByExample(carouselExample);

        return carousels;
    }
}
