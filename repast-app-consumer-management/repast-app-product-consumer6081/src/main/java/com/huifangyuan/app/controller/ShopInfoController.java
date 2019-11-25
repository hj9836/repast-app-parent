package com.huifangyuan.app.controller;

import com.huifangyuan.app.base.BaseController;
import com.huifangyuan.app.base.ResultData;
import com.huifangyuan.app.domain.ProductCat;
import com.huifangyuan.app.service.IRepastService;
import com.huifangyuan.app.vo.ShopInfoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2019/11/21 10:55
 * @Description
 *      商家controller
 **/
@RestController
@Api(value = "商家信息", tags = "商家信息接口")
public class ShopInfoController extends BaseController {

    @Autowired
    private IRepastService repastService;

    @GetMapping("/getShopInfoByPrimaryKey")
    @ApiOperation(value = "商家信息",notes = "根据主键获取商家信息")
    public ResultData getShopInfoByPrimaryKey(@RequestParam("shopId") Long ShopId){
        HashMap<String, Object> dataMap = new HashMap<String, Object>();
        if (null!=repastService.getShopInfoByPrimaryKey(ShopId)){
            System.out.println(repastService.getShopInfoByPrimaryKey(ShopId).getName());
            dataMap.put("Shop",repastService.getShopInfoByPrimaryKey(ShopId));
            return success(dataMap);
        }
        return failed();
    }



}
