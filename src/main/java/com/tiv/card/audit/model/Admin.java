package com.tiv.card.audit.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("admin")
public class Admin {

    @TableId(value = "ad_id", type = IdType.AUTO)
    private Integer adId;

    @TableField("ad_number")
    private String adNumber;

    @TableField("ad_password")
    private String adPassword;

    @TableField("ad_name")
    private String adName;

    @TableField("ad_phone")
    private String adPhone;

    @TableField("ad_create_time")
    private Date adCreateTime;

    @TableField("ad_state")
    private Byte adState;

}
