package com.huifangyuan.app.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ProductVo implements Serializable {
    private Long shopId;
    private String name;
    private String keywords;

    private String price;
    @Column(name = "promotion_price")
    private String promotionPrice;
    private String sale;
    private String description;
}
