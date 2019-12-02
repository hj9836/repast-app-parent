package com.huifangyuan.app.controller;

import com.huifangyuan.app.domain.Product;
import com.huifangyuan.app.service.ProductInfoService;
import com.huifangyuan.app.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2019/11/21 11:00
 * @Description
 **/
@RestController
public class SearchController {

    @Autowired
    SearchService searchService;

    /**
     * 根据商品name或keywords或product_category_name进行对全部商品的模糊搜索
     * @param keywords
     * @return
     */
    @GetMapping("/homeSeekProduct")
    public List<Product> homeSeekProduct(@RequestParam("keywords") String keywords){
        List<Product> products = searchService.HomeSeekProduct(keywords);
        return products;
    }









}
