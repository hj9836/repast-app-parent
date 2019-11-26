package com.huifangyuan.app.service;

import com.huifangyuan.app.base.BaseService;
import com.huifangyuan.app.domain.Product;
import com.huifangyuan.app.mapper.ProductInfoMapper;
import com.huifangyuan.app.mapper.ProductMapper;
import com.huifangyuan.app.mapper.SearchMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Service
public class SearchService {

    @Autowired
    private SearchMapper searchMapper;

    /**
     * 根据商品name或keywords或product_category_name进行对全部商品的模糊搜索
     * @param keywords
     * @return
     */
    public List<Product> HomeSeekProduct(String keywords){
        return searchMapper.HomeSeekProduct(keywords);
    }




















}
