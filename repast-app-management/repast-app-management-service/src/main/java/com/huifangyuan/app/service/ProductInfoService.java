package com.huifangyuan.app.service;

import com.huifangyuan.app.base.BaseService;
import com.huifangyuan.app.domain.Product;
import com.huifangyuan.app.mapper.ProductInfoMapper;
import com.huifangyuan.app.mapper.ShopInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.ArrayList;

@Service
public class ProductInfoService extends BaseService<Product> {

    @Autowired
    private ProductInfoMapper productInfoMapper;

    /**
        通用mapper方法
     **/
    public Mapper<Product> getMapper() {

        return null;

    }


















}
