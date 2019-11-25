package com.huifangyuan.app.controller;

import com.huifangyuan.app.service.ProductCatService;
import com.huifangyuan.app.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2019/11/21 11:00
 * @Description
 **/
@RestController
public class ProductCatController {

    @Autowired
    ProductCatService productCatService;



}
