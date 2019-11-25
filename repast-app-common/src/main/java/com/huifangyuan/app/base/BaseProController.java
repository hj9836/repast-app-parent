package com.huifangyuan.app.base;

import com.huifangyuan.app.status.GoodsStatus;
import org.springframework.stereotype.Controller;

@Controller
public class BaseProController {
    /**
     * 获取商品成功，使用系统消息返回
     * @return
     */
    protected ResultData success(){
        ResultData resultData = new ResultData();
        resultData.setCode(GoodsStatus.GET_SUCCESS.getCode());
        resultData.setMsg(GoodsStatus.GET_SUCCESS.getMsg());
        return resultData;
    }

    /**
     * 获取商品成功，使用自定义消息返回
     * @param msg
     * @return
     */
    protected ResultData success(String msg){
        ResultData resultData = new ResultData();
        resultData.setCode(GoodsStatus.GET_SUCCESS.getCode());
        resultData.setMsg(msg);
        return resultData;
    }

    /**
     * 获取商品成功，使用系统消息,返回自定义数据
     * @param data
     * @return
     */
    protected ResultData success(Object data){
        ResultData resultData = new ResultData();
        resultData.setCode(GoodsStatus.GET_SUCCESS.getCode());
        resultData.setMsg(GoodsStatus.GET_SUCCESS.getMsg());
        resultData.setData(data);
        return resultData;
    }
    /**
     * 获取商品成功，使用自定义消息,自定义数据返回
     * @param msg
     * @return
     */
    protected ResultData success(String msg,Object data){
        ResultData resultData = new ResultData();
        resultData.setCode(GoodsStatus.GET_SUCCESS.getCode());
        resultData.setMsg(msg);
        resultData.setData(data);
        return resultData;
    }

    /**
     * 获取商品失败，使用系统消息返回
     * @return
     */
    protected ResultData failed(){
        ResultData resultData = new ResultData();
        resultData.setCode(GoodsStatus.GET_FAILED.getCode());
        resultData.setMsg(GoodsStatus.GET_FAILED.getMsg());
        return resultData;
    }
    /**
     * 获取商品失败，使用自定义消息返回
     * @return
     */
    protected ResultData failed(String msg){
        ResultData resultData = new ResultData();
        resultData.setCode(GoodsStatus.GET_FAILED.getCode());
        resultData.setMsg(msg);
        return resultData;
    }

}
