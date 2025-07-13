package com.tiv.card.audit.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName(value = "card")
public class Card {

    @TableId(value = "cd_id", type = IdType.AUTO)
    private Integer cdId;

    @TableField(value = "cd_name")
    private String cdName;

    @TableField(value = "cd_content")
    private String cdContent;

    @TableField(value = "cd_service")
    private String cdService;

    @TableField(value = "cd_version")
    private Integer cdVersion;

    @TableField(value = "cd_create_time")
    private Date cdCreateTime;

    @TableField(value = "cd_state")
    private Byte cdState;

}
