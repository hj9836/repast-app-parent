package com.huifangyuan.app.controller;

import com.huifangyuan.app.base.BaseController;
import com.huifangyuan.app.base.ResultData;
import com.huifangyuan.app.service.IRepastService;
import com.huifangyuan.app.vo.CategoryShop;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(value = "类目名称", tags = "类目名称查询店铺接口")
public class CategoryShopController extends BaseController {

    @Autowired
    private IRepastService repastService;


    /**
     * @Author 闫增健
     * 根据类目名称 查询店铺信息
     * @param parentId
     * @return
     */
    @GetMapping("/getCategoryShop")
    @ApiOperation(value = "商品类目", notes = "通过类目名称查询店铺列表")
    public ResultData getCategoryShop(@RequestParam("parentId") Long parentId) {
        List<CategoryShop> shops = repastService.getCategoryShop(parentId);
        if (null != shops){
            return success("查询成功",shops);
        }
        return failed();
    }

}
