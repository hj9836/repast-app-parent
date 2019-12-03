package com.huifangyuan.app.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class CommentVo implements Serializable {
    @Id
    private Long id;
    private Long shopId;
    /**
     * 订单ID
     */
    private Long orderId;
    /**
     * 订单为单一商品时，该字段有值
     */
    private Long productId;
    private String memberNickName;
    private String productName;
    /**
     * 评价星数：0->5
     */
    private Integer star;
    /**
     * 评价的ip
     */
    private String memberIp;
    private Date createTime;
    private Integer showStatus;
    /**
     * 购买时的商品属性
     */
    private String productAttribute;
    private Integer collectCouont;
    private Integer readCount;
    /**
     * 上传图片地址，以逗号隔开
     */
    private String pics;
    /**
     * 评论用户头像
     */
    private String memberIcon;
    private Integer replayCount;
    private String content;

    private Long comment_id;
    private String replayContent;
    private Date userCreateTime;
    private Integer type;

}