package com.huifangyuan.app.controller;

import com.huifangyuan.app.base.BaseController;
import com.huifangyuan.app.base.ResultData;
import com.huifangyuan.app.domain.IntegralMall;
import com.huifangyuan.app.service.IRepastService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Regina
 * @data 2019/11/27 9:46
 * @project repast-pro-parent
 * @declaration:
 */
@Api(value = "积分商城",tags = "积分商城接口")
@RestController
public class IntegralMallController extends BaseController {

    @Autowired
    private IRepastService iRepastService;

    /**
     * 查询支持用积分兑换的商品信息
     * @return
     */
    @ApiOperation(value = "积分商城",notes = "查询支持用积分兑换的商品信息")
    @GetMapping("/getProductInfoByIntegral")
    public ResultData getProductInfoByIntegral(){
        List<IntegralMall> proInfo = iRepastService.getProductInfoByIntegral();
        if (null != proInfo){
            return success(proInfo);
        }else{
            return failed();
        }
    }
}
