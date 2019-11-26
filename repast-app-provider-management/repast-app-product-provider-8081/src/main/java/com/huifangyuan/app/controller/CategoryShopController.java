package com.huifangyuan.app.controller;

import com.huifangyuan.app.service.CategoryShopService;
import com.huifangyuan.app.vo.CategoryShop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2019/11/21 9:36
 * @Description
 **/
@RestController
public class CategoryShopController {

    @Autowired
    private CategoryShopService categoryShopService;


    /**
     * 根据类目名称 查询店铺信息
     * @param parentId
     * @return
     */
    @GetMapping("/getCategoryShop")
    public List<CategoryShop> getCategoryShop(@RequestParam("parentId") Long parentId) {
        return categoryShopService.selectCategoryShop(parentId);
    }

}
