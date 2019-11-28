package com.huifangyuan.app.controller;

import com.huifangyuan.app.domain.IntegralMall;
import com.huifangyuan.app.service.IntegralMallService;
import com.huifangyuan.app.service.MyRedisService;
import com.huifangyuan.app.service.RedisService;
import com.huifangyuan.app.vo.MemberProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Regina
 * @data 2019/11/27 10:32
 * @project repast-pro-parent
 * @declaration:
 */
@RestController
public class IntegralMallController {

    @Autowired
    private IntegralMallService integralMallService;

    @Autowired
    private RedisService redisService;
    @Autowired
    private MyRedisService myRedisService;

    /**
     * 查询支持用积分兑换的商品信息
     * @return
     */
    @GetMapping("/getProductInfoByIntegral")
    public List<MemberProduct> getProductInfoByIntegral(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize){
        return integralMallService.getProductInfoByIntegral(pageNum,pageSize,redisService,myRedisService);
    }
}
