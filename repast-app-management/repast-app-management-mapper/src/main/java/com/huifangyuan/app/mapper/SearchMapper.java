package com.huifangyuan.app.mapper;

import com.huifangyuan.app.domain.Product;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


public interface SearchMapper {


    /**
     * 首页的搜索
     * @param keywords
     * @return
     */
    List<Product> HomeSeekProduct(String keywords);

}
