package com.huifangyuan.app.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class AdvertiseVo implements Serializable {
    private Long id;
    private Long shopId;
    private String name;
    private Long positionId;
    private String pic;
    private Date start_time;
    private Date end_time;
    private int status;
    private int sort;
    private int click_count;
    private int order_count;
    private String url;
    private String note;


    private int type;
    private String positionName;
    private String descrition;
    private int max_count;


}
