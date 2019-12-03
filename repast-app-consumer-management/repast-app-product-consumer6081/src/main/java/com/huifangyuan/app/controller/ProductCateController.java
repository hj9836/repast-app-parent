package com.huifangyuan.app.controller;

import com.huifangyuan.app.base.BaseController;
import com.huifangyuan.app.base.ResultData;
import com.huifangyuan.app.domain.ProductCat;
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
@Api(value = "分类信息", tags = "商品分类信息接口")
public class ProductCateController extends BaseController {

    @Autowired
    private IRepastService repastService;

    /**
     * 通过层级查询商品类型
     * @param level
     * @return
     */
    @GetMapping("/getByLevel")
    @ApiOperation(value = "商品类型", notes = "通过层级查询商品类型")
    public ResultData getCateByLevel(String token) {
        if(null != token) {
            String s = repastService.selectToken(token);
            if (null != s) {
                List<ProductCat> ProductCate = repastService.getCateByLevel();
                if (null != ProductCate) {
                    return success(ProductCate);
                }
            }
        }
        return failed();
    }
}
