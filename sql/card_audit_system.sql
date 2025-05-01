DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`
(
    `ad_id`          int         NOT NULL AUTO_INCREMENT comment '管理员的主键id',
    `ad_number`      char(7)     NOT NULL COMMENT '管理员的工号',
    `ad_password`    char(32)    NOT NULL COMMENT '管理员的密码',
    `ad_name`        varchar(30) NOT NULL COMMENT '管理员的姓名',
    `ad_phone`       char(11)    NOT NULL COMMENT '管理员的手机号',
    `ad_create_time` datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `ad_state`       tinyint     NOT NULL DEFAULT '1' COMMENT '状态',
    PRIMARY KEY (`ad_id`)
) COMMENT ='管理员表';

DROP TABLE IF EXISTS `representative`;
CREATE TABLE `representative`
(
    `rep_number`      char(7)     NOT NULL COMMENT '业代的工号',
    `rep_password`    char(32)    NOT NULL COMMENT '业代的密码',
    `rep_sim_number`  char(20)    NOT NULL COMMENT '业代的sim卡号',
    `rep_name`        varchar(30) NOT NULL COMMENT '业代的姓名',
    `rep_level`       tinyint     NOT NULL DEFAULT '1' COMMENT '业代的级别',
    `rep_phone`       char(11)    NOT NULL COMMENT '业代的手机号',
    `rep_create_time` datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `rep_state`       tinyint     NOT NULL DEFAULT '1' COMMENT '状态',
    PRIMARY KEY (`rep_number`)
) COMMENT ='业代表';

DROP TABLE IF EXISTS `document`;
CREATE TABLE `document`
(
    `doc_number`        char(31)     NOT NULL COMMENT '申请件的编号',
    `doc_title`         varchar(60)  NOT NULL COMMENT '申请件的标题',
    `doc_content`       text         NOT NULL COMMENT '申请件的内容',
    `doc_reject_reason` varchar(500) DEFAULT NULL COMMENT '拒绝原因',
    `doc_type`          tinyint      NOT NULL DEFAULT '1' COMMENT '类型',
    `doc_create_time`   datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `doc_state`         tinyint      NOT NULL DEFAULT '1' COMMENT '状态',
    `rep_number`        char(7)      NOT NULL COMMENT '业代的工号',
    PRIMARY KEY (`doc_number`),
    KEY `document_fk` (`rep_number`),
    CONSTRAINT `document_fk` FOREIGN KEY (`rep_number`) REFERENCES `representative` (`rep_number`)
) COMMENT ='申请件表';

DROP TABLE IF EXISTS `card`;
CREATE TABLE `card`
(
    `cd_id`          int           NOT NULL AUTO_INCREMENT COMMENT '信用卡的主键id',
    `cd_name`        varchar(60)   NOT NULL COMMENT '信用卡名',
    `cd_content`     varchar(2000) NOT NULL COMMENT '信用卡的内容',
    `cd_service`     varchar(5000) DEFAULT NULL COMMENT '信用卡的服务信息',
    `cd_version`     int           NOT NULL COMMENT '信用卡的版本号',
    `cd_create_time` datetime      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `cd_state`       tinyint       NOT NULL COMMENT '状态',
    PRIMARY KEY (`cd_id`)
) COMMENT ='信用卡表';
