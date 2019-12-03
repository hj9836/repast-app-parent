package com.huifangyuan.app.service;

import com.huifangyuan.app.base.BaseService;
import com.huifangyuan.app.mapper.CommentMapper;
import com.huifangyuan.app.vo.CommentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


/**
 * @作者：吴文博
 * @项目：repast-app-parent
 * @包：com.aaa.lee.app.service
 * @日期：2019-11-22 11:07
 */
@Service
public class ShopCommentService extends BaseService<CommentVo> {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public Mapper<CommentVo> getMapper() {
        return commentMapper;
    }

    /**
     *根据产品id获取产品评价
     */
    public List<CommentVo> getProductCommentById(Long productId){
        List<CommentVo> productCommentById = commentMapper.getProductCommentById(productId);
        if (productCommentById.size() > 0){
            return productCommentById;
        }
        return null;
    }

    /**
     *根据店铺id获取店铺评价
     */
    public List<CommentVo> getShopCommentById(Long shopId){
        List<CommentVo> shopCommentById = commentMapper.getShopCommentById(shopId);
        if (shopCommentById.size() > 0){
            return shopCommentById;
        }
        return null;
    }
}

