package com.huifangyuan.app.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @作者：吴文博
 * @项目：repast-app-parent
 * @包：com.huifangyuan.app.vo
 * @日期：2019-11-28 21:41
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ProductVo implements Serializable {

    private Long shopId;
    private String name;

}
