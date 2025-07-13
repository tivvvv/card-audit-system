package com.tiv.card.audit.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("document")
public class Document {

    @TableId(value = "doc_number")
    private String docNumber;

    @TableField(value = "doc_title")
    private String docTitle;

    @TableField(value = "doc_content")
    private String docContent;

    @TableField(value = "doc_reject_reason")
    private String docRejectReason;

    @TableField(value = "doc_type")
    private Byte docType;

    @TableField(value = "doc_create_time")
    private Date docCreateTime;

    @TableField(value = "doc_state")
    private Byte docState;

    @TableField(value = "rep_number")
    private String repNumber;

}
