package com.huifangyuan.app.service;

import com.huifangyuan.app.base.BaseService;
import com.huifangyuan.app.domain.Comment;
import com.huifangyuan.app.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;


/**
 * @作者：吴文博
 * @项目：repast-app-parent
 * @包：com.aaa.lee.app.service
 * @日期：2019-11-22 11:07
 */
@Service
public class ShopCommentService extends BaseService<Comment> {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public Mapper<Comment> getMapper() {
        return commentMapper;
    }
}
