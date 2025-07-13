package com.tiv.card.audit.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("representative")
public class Representative {

    @TableId("rep_number")
    private String repNumber;

    @TableField("rep_password")
    private String repPassword;

    @TableField("rep_sim_number")
    private String repSimNumber;

    @TableField("rep_name")
    private String repName;

    @TableField("rep_level")
    private Byte repLevel;

    @TableField("rep_phone")
    private String repPhone;

    @TableField("rep_create_time")
    private Date repCreateTime;

    @TableField("rep_state")
    private Byte repState;

}
