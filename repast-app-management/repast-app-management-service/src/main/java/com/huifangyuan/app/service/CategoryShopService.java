package com.huifangyuan.app.service;


import com.huifangyuan.app.base.BaseService;
import com.huifangyuan.app.mapper.CategoryShopMapper;
import com.huifangyuan.app.vo.CategoryShop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Service
public class CategoryShopService extends BaseService<CategoryShop> {
    @Autowired
    private CategoryShopMapper categoryShopMapper;
    @Override
    public Mapper<CategoryShop> getMapper() {
        return categoryShopMapper;
    }

    /**
     * 根据类目名称 查询所有商铺列表
     * @param parentId
     * @return
     */
    public List<CategoryShop> selectCategoryShop(Long parentId){
        List<CategoryShop> shops = categoryShopMapper.getCategoryShop(parentId);
        if (shops.size()>0){
            return shops;
        }
        return null;

    }
}
