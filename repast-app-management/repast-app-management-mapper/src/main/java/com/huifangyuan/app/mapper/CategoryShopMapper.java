package com.huifangyuan.app.mapper;

import com.huifangyuan.app.vo.CategoryShop;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface CategoryShopMapper extends Mapper<CategoryShop> {
    /**
     * 根据类目名称 查询所有商铺列表
     * @param parentId
     * @return
     */
    List<CategoryShop> getCategoryShop(Long parentId);
}
