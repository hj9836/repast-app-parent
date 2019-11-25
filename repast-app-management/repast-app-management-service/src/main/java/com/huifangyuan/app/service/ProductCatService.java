package com.huifangyuan.app.service;

import com.huifangyuan.app.base.BaseService;
import com.huifangyuan.app.domain.Product;
import com.huifangyuan.app.domain.ProductCat;
import com.huifangyuan.app.mapper.ProductCatMapper;
import com.huifangyuan.app.mapper.ProductInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

@Service
public class ProductCatService extends BaseService<ProductCat> {

    @Autowired
    private ProductCatMapper productCatMapper;

    /**
        通用mapper方法
     **/
    public Mapper<ProductCat> getMapper() {
        return null;
    }


















}
