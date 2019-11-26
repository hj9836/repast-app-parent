package com.huifangyuan.app.controller;

import com.huifangyuan.app.service.ShopInfoService;
import com.huifangyuan.app.vo.ShopInfoVo;
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
public class ShopInfoController {

    @Autowired
    ShopInfoService shopInfoService;
    @GetMapping("/getShopInfoByPrimaryKey")
    public ShopInfoVo getShopInfoByPrimaryKey(@RequestParam(value = "ShopId") Long ShopId){

        System.out.println("进入8081");
        System.out.println(ShopId);
        return shopInfoService.getShopInfoByPrimaryKey(ShopId);
    }


}
