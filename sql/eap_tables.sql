drop table if exists EAP_USER;
create table EAP_USER
(
   id                   bigint not null auto_increment comment '用户ID|',
   user_name            varchar(40) comment '用户名|',
   user_phone           varchar(16) comment '手机号|',
   company              varchar(32) comment '企业单位|',
   sex                  varchar(32) comment '性别 0 未知, 1 男 2 女',
   age                  varchar(32) comment '年龄',
   wechat_id            varchar(32) comment '微信id',
   subscribe_count      INT (11) DEFAULT 0 comment '预约次数',
   evaluating_count     INT(11) DEFAULT 0  comment '评测次数',
   src                  varchar(32) comment '用户来源 0 预约, 1 评测|',
   create_time          datetime comment '创建时间|',
   create_by            varchar(40) comment '创建者|',
   modify_time          datetime comment '更新时间|',
   modify_by            varchar(40) comment '修改者|',
   deleted              varchar(8) comment '是否删除(T删除，F未删除)|',
   primary key (id)
);

alter table EAP_USER comment '用户表';

drop table if exists EAP_SUBSCRIBE;
create table EAP_SUBSCRIBE
(
   id                   bigint not null AUTO_INCREMENT comment '企业ID|',
   user_id              bigint comment '用户id',
   user_name            varchar(40) comment '用户名|',
   user_phone           varchar(16) comment '手机号|',
   company              varchar(32) comment '企业单位|',
   sex                  varchar(32) comment '性别 0 未知, 1 男 2 女',
   age                  varchar(32) comment '年龄',
   subscribe_time       datetime comment '预约申请时间|',
   remark               varchar(512) comment '预约描述',
   create_time          datetime comment '创建时间|',
   create_by            varchar(40) comment '创建者|',
   modify_time          datetime comment '更新时间|',
   modify_by            varchar(40) comment '修改者|',
   deleted              varchar(8) comment '是否删除(T删除，F未删除)|',
   primary key (id)
);

alter table EAP_SUBSCRIBE comment '预约记录';

drop table if exists EAP_EVALUATING;
create table EAP_EVALUATING
(
   id                   bigint AUTO_INCREMENT comment '主键ID|',
   user_id              bigint comment '用户id',
   user_name            varchar(40) comment '用户名|',
   user_phone           varchar(16) comment '手机号|',
   company              varchar(32) comment '企业单位|',
   sex                  varchar(32) comment '性别 0 未知, 1 男 2 女',
   age                  varchar(32) comment '年龄',
   eva_type             varchar(32) comment '评测类型：1 MBTI,2 OQ45,3 SCL90',
   eva_name             varchar(32) comment '评测名称',
   answer               varchar(512) comment '答案[1,3,4,2,3,2]',
   score                varchar(512) comment '分数',
   result               varchar(512) comment '结果描述',
   catagory_id          bigint comment '产品类别（第三级）ID|',
   create_time          datetime comment '创建时间|',
   create_by            varchar(40) comment '创建者|',
   modify_time          datetime comment '更新时间|',
   modify_by            varchar(40) comment '修改者|',
   deleted              varchar(8) comment '是否删除(T删除，F未删除)|',
   primary key (id)
);

alter table EAP_EVALUATING comment '评测记录';

drop table if exists EAP_MSG;
create table EAP_MSG
(
   id                   bigint AUTO_INCREMENT comment '主键ID|',
   phone                varchar(64) comment '手机号码',
   code                 varchar(2) comment '验证码',
   create_time          datetime comment '创建时间|',
   create_by            varchar(40) comment '创建者|',
   modify_time          datetime comment '更新时间|',
   modify_by            varchar(40) comment '修改者|',
   deleted              varchar(8) comment '是否删除(T删除，F未删除)|',
   primary key (id)
);

alter table EAP_MSG comment '短信验证码';

drop table if exists EAP_ANSWER;
create table EAP_ANSWER
(
   id                   bigint AUTO_INCREMENT comment '主键ID|',
   eva_type             varchar(32) comment '评测类型：1 MBTI,2 OQ45,3 SCL90',
   num                  varchar(32) comment '题号',
   dimension            varchar(2) comment '维度',
   options              varchar(2) comment '选项',
   score                varchar(2) comment '得分',
   sex                  varchar(32) comment '性别 0 未知, 1 男 2 女',
   create_time          datetime comment '创建时间|',
   create_by            varchar(40) comment '创建者|',
   modify_time          datetime comment '更新时间|',
   modify_by            varchar(40) comment '修改者|',
   deleted              varchar(8) comment '是否删除(T删除，F未删除)|',
   primary key (id)
);

alter table EAP_ANSWER comment '答案表';

CREATE TABLE `EAP_SYS_OPTION` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` VARCHAR(45) NULL COMMENT '描述',
  `code` VARCHAR(45) NULL COMMENT '编码',
  `set_val` VARCHAR(45) NULL COMMENT '设置值',
  `default_val` VARCHAR(45) NULL COMMENT '默认值',
  `deleted` VARCHAR(45) NULL COMMENT '是否删除',
  PRIMARY KEY (`id`));
alter table EAP_SYS_OPTION comment '系统配置表';
