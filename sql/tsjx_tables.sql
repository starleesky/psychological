drop table if exists tsjx_user;

/*==============================================================*/
/* Table: tsjx_user                                             */
/*==============================================================*/
create table tsjx_user
(
   id                   bigint not null auto_increment comment '用户ID|',
   user_name            varchar(40) comment '用户名|',
   mobile               varchar(16) comment '手机号|',
   telephone            varchar(16) comment '座机号|',
   email                varchar(40) comment '邮箱|',
   password             varchar(32) comment '密码|',
   qq                   varchar(16) comment 'QQ|',
   province_name        varchar(16) comment '省份|',
   province_id          bigint comment '省份ID|',
   city_name            varchar(16) comment '城市|',
   city_id              bigint comment '城市ID',
   business_scope       varchar(16) comment '经营范围|',
   business_nature      varchar(16) comment '经营性质|',
   user_type            varchar(8) comment '用户类型 0系统管理员 1普通管理员，2普通用户 3企业管理员 |',
   company_id           varchar(32) comment '企业ID|',
   src                  varchar(32) comment '用户来源 0 默认, 1 微信|',
   is_activate          varchar(8) comment '是否激活(T激活，F未激活)',
   is_new_message       varchar(8) comment '0没有消息，1有消息|',
   head_icon            varchar(60) comment '图片地址|',
   last_login_time      datetime COMMENT '最近登录时间|',
   create_time          datetime comment '创建时间|',
   create_by            varchar(40) comment '创建者|',
   modify_time          datetime comment '更新时间|',
   modify_by            varchar(40) comment '修改者|',
   deleted              varchar(8) comment '是否删除(T删除，F未删除)|',
   primary key (id)
);

alter table tsjx_user comment '用户表';

drop table if exists tsjx_company;

/*==============================================================*/
/* Table: tsjx_company                                          */
/*==============================================================*/
create table tsjx_company
(
   id                   bigint not null AUTO_INCREMENT comment '企业ID|',
   company_name                 varchar(64) comment '企业名称|',
   telephone            varchar(16) comment '联系电话|',
   fax                  varchar(16) comment '传真|',
   province_name             varchar(16) comment '省份|',
   province_id          bigint comment '省份ID|',
   city_name                 varchar(16) comment '城市|',
   city_id              bigint comment '城市ID',
   address              varchar(128) comment '详细地址|',
   introduction         varchar(2000) comment '公司介绍|',
   business_license_image_url varchar(128) comment '营业执照图片路径|',
   organization_code_image_url varchar(128) comment '组织机构代码证图片路径|',
   status               varchar(2) comment '企业状态 0未认证 1认证成功 2认证失败|',
   create_time          datetime comment '创建时间|',
   create_by            varchar(40) comment '创建者|',
   modify_time          datetime comment '更新时间|',
   modify_by            varchar(40) comment '修改者|',
   deleted              varchar(8) comment '是否删除(T删除，F未删除)|',
   primary key (id)
);

alter table tsjx_company comment '企业表';

drop table if exists tsjx_brand;

/*==============================================================*/
/* Table: tsjx_brand                                            */
/*==============================================================*/
create table tsjx_brand
(
   id                   bigint AUTO_INCREMENT comment '品牌ID|',
   brand_name           varchar(64) comment '品牌名称|',
   first_letter         varchar(2) comment '品牌首字母|',
   catagory_id          bigint comment '产品类别（第三级）ID|',
   create_time          datetime comment '创建时间|',
   create_by            varchar(40) comment '创建者|',
   modify_time          datetime comment '更新时间|',
   modify_by            varchar(40) comment '修改者|',
   deleted              varchar(8) comment '是否删除(T删除，F未删除)|',
   primary key (id)
);

alter table tsjx_brand comment '品牌表';

drop table if exists tsjx_models;

/*==============================================================*/
/* Table: tsjx_model                                            */
/*==============================================================*/
create table tsjx_models
(
   id                   bigint not null AUTO_INCREMENT comment '型号ID|',
   models_name                 varchar(64) comment '型号名称|',
   brand_id             bigint comment '品牌ID|',
   create_time          datetime comment '创建时间|',
   create_by            varchar(40) comment '创建者|',
   modify_time          datetime comment '更新时间|',
   modify_by            varchar(40) comment '修改者|',
   deleted              varchar(8) comment '是否删除(T删除，F未删除)|',
   primary key (id)
);

alter table tsjx_models comment '型号表';

drop table if exists tsjx_catagory;

/*==============================================================*/
/* Table: tsjx_goods_catagory                                   */
/*==============================================================*/
create table tsjx_catagory
(
   id                   bigint not null  AUTO_INCREMENT comment 'ID|',
   code                 varchar(16) comment '类别代码|',
   catagory_name       varchar(64) comment '类别名称|',
   parent_id          varchar(16) comment '上级类别代码|',
   layer                varchar(2) comment '类别层级|',
   create_time          datetime comment '创建时间|',
   create_by            varchar(40) comment '创建者|',
   modify_time          datetime comment '更新时间|',
   modify_by            varchar(40) comment '修改者|',
   deleted              varchar(8) comment '是否删除(T删除，F未删除)|',
   primary key (id)
);

alter table tsjx_catagory comment '产品类别表';

drop table if exists tsjx_infomation;

/*==============================================================*/
/* Table: tsjx_infomation                                       */
/*==============================================================*/
create table tsjx_infomation
(
   id                   bigint not null AUTO_INCREMENT comment 'ID|',
   catagory_big_id      bigint comment '产品大类ID|',
   catagory_big_name    varchar(64) comment '产品大类名称|',
   catagory_mid_id      bigint comment '产品组ID|',
   catagory_mid_name    varchar(64) comment '产品组名称|',
   catagory_id          bigint comment '产品类ID|',
   catagory_name        varchar(64) comment '产品类名称|',
   brand_id             bigint comment '品牌ID|',
   brand_name           varchar(64) comment '品牌名称|',
   model_id             bigint comment '型号ID|',
   model_name           varchar(64) comment '型号名称|',
   is_new               varchar(2) comment '品牌型号自定义标示（是否新增的品牌型号）|',
   sell_type            varchar(16) comment '销售方式（类型）|',
   equipment_condition  varchar(128) comment '设备情况|',
   procedures           varchar(16) comment '手续资料|',
   src                  varchar(16) comment '设备来源（类型）|',
   equip_year                 varchar(8) comment '年份|',
   work_time                 varchar(16) comment '工时|',
   price                DECIMAL(10,2) comment '价格|',
   serial_num           varchar(32) comment '设备序列号|',
   in_stock_code        varchar(32) comment '内部库存编码|',
   remark               varchar(2000) comment '卖家附言|',
   equipment_location   varchar(64) comment '设备位置|',
   pub_time             datetime comment '发布日期|',
   valid_time           varchar(8) comment '有效期|',
   stock_count          varchar(8) comment '库存数量|',
   sell_count           varchar(8) comment '已售数量|',
   user_id              bigint comment '用户ID|',
   is_top               varchar(2) comment '是否推荐|',
   status               varchar(2) comment '信息状态（新建、审核中/待审核、发布、下架、已售）|',
   weight               varchar(4) comment '排序权重|',
   create_time          datetime comment '创建时间|',
   create_by            varchar(40) comment '创建者|',
   modify_time          datetime comment '更新时间|',
   modify_by            varchar(40) comment '修改者|',
   deleted              varchar(8) comment '是否删除(T删除，F未删除)|',
   primary key (id)
);

alter table tsjx_infomation comment ' 信息内容表';

drop table if exists tsjx_audit_record;

/*==============================================================*/
/* Table: tsjx_audit_his                                        */
/*==============================================================*/
create table tsjx_audit_record
(
   id                   bigint not null AUTO_INCREMENT comment '记录ID|',
   audit_type           varchar(2) comment '审核类型（信息发布、企业审核）|',
   user_id              bigint comment '记录创建人ID|',
   remark               varchar(1000) comment '备注|',
   audit_status         varchar(2) comment '审核结果 0成功 1失败|',
   create_time          datetime comment '创建时间|',
   create_by            varchar(40) comment '创建者|',
   modify_time          datetime comment '更新时间|',
   modify_by            varchar(40) comment '修改者|',
   deleted              varchar(8) comment '是否删除(T删除，F未删除)|',
   primary key (id)
);

alter table tsjx_audit_record comment '审核记录表';

drop table if exists tsjx_comment;

/*==============================================================*/
/* Table: tsjx_comment                                          */
/*==============================================================*/
create table tsjx_comment
(
   id                   bigint not null AUTO_INCREMENT,
   content              varchar(4000),
   infomation_id        bigint,
   user_id              bigint comment '用户id|',
   create_time          datetime comment '创建时间|',
   create_by            varchar(40) comment '创建者|',
   modify_time          datetime comment '更新时间|',
   modify_by            varchar(40) comment '修改者|',
   deleted              varchar(8) comment '是否删除(T删除，F未删除)|',
   primary key (id)
);

alter table tsjx_comment comment '信息评论表';

drop table if exists tsjx_collection;

/*==============================================================*/
/* Table: tsjx_collection                                       */
/*==============================================================*/
create table tsjx_collection
(
   id                   bigint not null AUTO_INCREMENT comment '主键|',
   user_id              bigint comment '用户ID|',
   information_id       bigint comment '信息ID|',
   create_time          datetime comment '创建时间|',
   create_by            varchar(40) comment '创建者|',
   modify_time          datetime comment '更新时间|',
   modify_by            varchar(40) comment '修改者|',
   deleted              varchar(8) comment '是否删除(T删除，F未删除)|',
   primary key (id)
);

alter table tsjx_collection comment '我的收藏';

drop table if exists tsjx_notice;

/*==============================================================*/
/* Table: tsjx_notice                                           */
/*==============================================================*/
create table tsjx_notice
(
   id                   bigint not null AUTO_INCREMENT comment '主键|',
   user_id              bigint comment '用户id|',
   title                varchar(256) comment '公告标题|',
   content              varchar(4000) comment '公告内容|',
   notice_type          varchar(40) comment '公告类型 0系统公告，1代表用户消息| ',
   create_time          datetime comment '创建时间|',
   create_by            varchar(40) comment '创建者|',
   modify_time          datetime comment '更新时间|',
   modify_by            varchar(40) comment '修改者|',
   deleted              varchar(8) comment '是否删除(T删除，F未删除)|',
   primary key (id)
);

alter table tsjx_notice comment '系统公告';

drop table if exists tsjx_region;

/*==============================================================*/
/* Table: tsjx_region                                           */
/*==============================================================*/
create table tsjx_region
(
   id                   bigint not null AUTO_INCREMENT comment '主键|',
   region_name                 varchar(64) comment '区划名|',
   region_code                 varchar(16) comment '区划代码|',
   parent_id          varchar(16) comment '上级区划代码|',
   layer                varchar(8) comment '层级|',
   create_time          datetime comment '创建时间|',
   create_by            varchar(40) comment '创建者|',
   modify_time          datetime comment '更新时间|',
   modify_by            varchar(40) comment '修改者|',
   deleted              varchar(8) comment '是否删除(T删除，F未删除)|',
   primary key (id)
);

alter table tsjx_region comment '行政区划';

drop table if exists tsjx_attch;

/*==============================================================*/
/* Table: tsjx_attch                                            */
/*==============================================================*/
create table tsjx_attch
(
   id                   bigint not null AUTO_INCREMENT comment 'ID|',
   information_id       bigint comment '信息ID|',
   attch_name           varchar(128) comment '附件名称|',
   attch_size           int comment '附件大小|',
   attch_type           varchar(64) comment '附件类型|',
   show_name            varchar(128) comment '附件显示名称|',
   attch_url                  varchar(192) comment '附件路径|',
   user_id       bigint comment '上传用户ID|',
   create_time          datetime comment '创建时间|',
   create_by            varchar(40) comment '创建者|',
   modify_time          datetime comment '更新时间|',
   modify_by            varchar(40) comment '修改者|',
   deleted              varchar(8) comment '是否删除(T删除，F未删除)|',
   primary key (id)
);

alter table tsjx_attch comment '附件表';
/*
 *2016-04-03 用户表添加微信号和真实姓名
 */
ALTER TABLE `tsjx`.`tsjx_user` 
ADD COLUMN `real_name` VARCHAR(45) NULL AFTER `deleted`,
ADD COLUMN `weixin_account` VARCHAR(45) NULL AFTER `real_name`;

/*
 * 2016-04-03 添加系统配置表
 */
CREATE TABLE `tsjx`.`tsjx_sysOption` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` VARCHAR(45) NULL COMMENT '描述',
  `code` VARCHAR(45) NULL COMMENT '编码',
  `set_val` VARCHAR(45) NULL COMMENT '设置值',
  `default_val` VARCHAR(45) NULL COMMENT '默认值',
  `deleted` VARCHAR(45) NULL COMMENT '是否删除',
  PRIMARY KEY (`id`));

