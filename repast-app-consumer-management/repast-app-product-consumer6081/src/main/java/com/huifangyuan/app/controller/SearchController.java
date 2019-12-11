package com.huifangyuan.app.controller;

import com.huifangyuan.app.base.BaseController;
import com.huifangyuan.app.base.ResultData;
import com.huifangyuan.app.domain.Product;
import com.huifangyuan.app.service.IRepastService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2019/11/21 10:55
 * @Description
 *      商家controller
 **/
@RestController
@Api(value = "搜索信息", tags = "搜索信息接口")
public class SearchController extends BaseController {

    @Autowired
    private IRepastService repastService;

    /**
     * 根据商品name或keywords或product_category_name进行对全部商品的模糊搜索
     * @param keywords
     * @return
     */
    @GetMapping("/homeSeekProduct")
    @ApiOperation(value = "首页搜索" , notes = "首页搜索商品列表")
    public ResultData homeSeekProduct(String keywords){

                List<Product> products = repastService.homeSeekProduct(keywords);
                if (products.size() > 0){
                    return success(products);


        }
        return failed();

    }



}
