package com.huifangyuan.app.mapper;

import com.huifangyuan.app.vo.CommentVo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface CommentMapper extends Mapper<CommentVo> {

    /**根据产品id获取所有产品评价*/
    List<CommentVo> getProductCommentById(Long productId);

    /**根据店铺id获取所有店铺评价*/
    List<CommentVo> getShopCommentById(Long shopId);

}