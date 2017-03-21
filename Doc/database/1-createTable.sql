/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     16/8/1 下午3:23:56                             */
/*==============================================================*/
CREATE DATABASE `tfdor` CHARACTER SET 'utf8' COLLATE 'utf8_general_ci';



drop table if exists UserInfo;
drop table if exists RoleMenuRelate;
drop table if exists Menu;
drop table if exists Role;
drop table if exists AccessToken;
drop table if exists Auditing;
drop table if exists QrcodeImg;
drop table if exists CreateQrcodeImg;
drop table if exists RedPack;
drop table if exists CustomerInfo;
drop table if exists Advertisement;
drop table if exists Notice;
drop table if exists MerchantMenuAssociation;


drop table if exists WebMenu;
drop table if exists Merchant;
drop table if exists Channel;








create table Auditing(
   auditingSeq integer not null auto_increment comment '审核表序列',
   auditingTrans varchar(50)  comment '审核交易',
   auditingName  varchar(100) comment '审核交易名称',
   auditingData  varchar(1024) not null comment '审核交易请求数据',
   promoterSeq integer not null comment '审核发起人Seq',
   promoter varchar(20) not null comment '审核发起人',
   auditPersonSeq integer not null comment '指定审核人SEQ',
   auditPerson varchar(20) not null comment '审核人名称',
   createTime timestamp default '0000-00-00 00:00:00' comment '创建时间',
   updateTime timestamp default now() on update now() comment '修改时间（审核时间）',
   state char(1) comment '审核状态，I－待审核，S－审核通过，F－审核拒绝',
   remarks varchar(100) comment '备注',
   primary key (auditingSeq)
)DEFAULT CHARSET= UTF8 ENGINE = InnoDB;
alter table Auditing comment '审核表';



create table Menu(
   menuId               varchar(20) not null comment '菜单ID',
   menuName             varchar(50) not null comment '菜单名称',
   parentId             varchar(20) not null comment '父级菜单ID',
   orderId              integer comment '排序ID 从小到大排序',
   transId              varchar(20) comment '交易ID',
   createTime           TIMESTAMP not null comment '创建时间',
   primary key (MenuId)
)
DEFAULT CHARSET= UTF8 ENGINE = InnoDB;

alter table Menu comment '菜单表';


create table Role(
   roleSeq              INTEGER not null auto_increment,
   channelId            VARCHAR(20) not null comment '渠道ID',
   roleName             VARCHAR(30) not null comment '角色名称',
   createTime           TIMESTAMP not null comment '创建时间',
   primary key (RoleSeq)
)
auto_increment = 1000 DEFAULT CHARSET= UTF8 ENGINE = InnoDB;

alter table Role comment '角色表';



create table RoleMenuRelate(
   roleSeq              INTEGER not null comment '角色SEQ',
   menuId               varchar(20) not null comment '菜单ID',
   primary key (RoleSeq, MenuId)
)
auto_increment = 1000 DEFAULT CHARSET= UTF8 ENGINE = InnoDB;

alter table RoleMenuRelate comment '角色菜单关联表';


create table UserInfo(
   userSeq              INTEGER not null auto_increment,
   roleSeq              INTEGER  comment '角色ID',
   userId               VARCHAR(18)  comment '用户ID',
   userName             VARCHAR(18) comment '用户名称',
   password             VARCHAR(200)  comment '密码',
   customerType         char(1) comment '用户类型，A-管理员，O-操作员',
   sex                  char(1)  comment '性别 W-女，M-男 - -未知',
   age                  INTEGER(3) comment '年龄',
   idType               char(2) comment '证件类型',
   idNo                 char(18) comment '证件号码',
   mobilePhone          char(11) comment '手机号码',
   phone                VARCHAR(24) comment '电话-座机',
   channelId            char(20) comment '渠道ID',
   pasdErrorCount       integer default 0 comment '密码错误次数',
   loginCount           integer default 0 comment '登陆次数',
   lastLoginTime        timestamp default now() on update now() comment '最后登陆时间',
   isReSetPwd           varchar(5) default 'true' comment '是否需要重置密码',
   createTime           timestamp default '0000-00-00 00:00:00',
   addr                 VARCHAR(500),
   primary key (UserSeq)
)
auto_increment = 800000 DEFAULT CHARSET= UTF8 ENGINE = InnoDB;

alter table UserInfo comment '用户表';



create table Channel(
   channelId            varchar(20) not null comment '渠道ID',
   channelName          varchar(50) not null comment '渠道名称',
   createTime           timestamp default '0000-00-00 00:00:00',
   updateTime           timestamp default now() on update now(),
   state                char(1) comment '渠道状态  N-正常，C-销户，S-停用',
   primary key (channelId)
)
auto_increment = 100 DEFAULT CHARSET= UTF8 ENGINE = InnoDB;
alter table channel comment '渠道表';



create table Merchant(
   mchSeq               integer not null auto_increment comment '商户Seq',
   mchId                varchar(20) comment '商户ID',
   channelId            varchar(20) not null comment '渠道ID 外键',
   mchName              varchar(50) comment '商户名称',
   appId                varchar(20) not null comment '微信APPID',
   wxToken              varchar(32) not null comment '微信Token',
   appSecret            varchar(100) not null comment '微信appSecret',
   encodingAesKey       varchar(100) not null comment '微信加解密秘钥',
   signatureKey         varchar(100) comment '微信签名秘钥',
   createTime           timestamp default '0000-00-00 00:00:00',
   updateTime           timestamp default now() on update now(),
   state                char(1) comment '商户状态  N-正常，C-销户，S-停用',
   primary key (mchSeq)
)
auto_increment = 100 DEFAULT CHARSET= UTF8 ENGINE = InnoDB;

alter table Merchant comment '商户表';



create table RedPack(
   redPackSeq           integer not null auto_increment comment 'Seq',
   redPackType          varchar(4) not null comment '红包种类 OYRK-普通红包，FNRK-裂变红包',
   amountType           varchar(4) not null comment '金额类型 FDAT-固定金额、RMAT-随机金额',
   totalAmount          varchar(20) not null comment '红包金额  1-200，当type是随机金额时 totalAmount为一个区间  例如：1-3（元）',
   wishing              varchar(50) not null comment '红包祝福语',
   actName              varchar(50) not null comment '活动名称',
   mchId                varchar(20) not null comment '商户ID',
   remark               varchar(100) not null comment '备注',
   totalNum             varchar(10) comment '裂变红包发放总人数',
   state                char(1) DEFAULT '1' not null comment '状态 N-正常，H-关闭',
   createTime           timestamp default '0000-00-00 00:00:00',
   updateTime           timestamp default now() on update now(),
   primary key (redPackSeq)
)DEFAULT CHARSET= UTF8 ENGINE = InnoDB;
alter table RedPack comment '红包配置表';


create table CreateQrcodeImg(
   CreateQISeq        integer not null auto_increment comment '序列',
   mchId              varchar(20) not null comment '商户ID',
   beginDate          varchar(20) comment '二维码生效时间',
   actionName         varchar(20) not null comment '二维码类型',
   endDate            varchar(20) comment '二维码失效时间',
   expireSeconds      varchar(20) comment '临时二维码有效时间（天 1-30）',
   number             integer not null comment '二维码数量',
   preservation       varchar(150)  comment '二维码保存路径',
   redPackSeq         integer not null comment '活动名称',
   state              char(1) not null comment '二维码状态 N-正常，H-关闭',
   createTime         timestamp default '0000-00-00 00:00:00',
   updateTime         timestamp default now() on update now(),
   primary key (CreateQISeq)
)DEFAULT CHARSET= UTF8 ENGINE = InnoDB;
alter table CreateQrcodeImg comment '二维码生成配置表';




create table QrcodeImg(
   qrcodeSeq            integer not null auto_increment comment '二维码序列',
   CreateQISeq          integer not null comment '二维码批次Seq',
   mchId                varchar(20) not null comment '商户ID',
   sceneStr             varchar(50) not null comment '二维码ID',
   ticket               varchar(100) not null comment '微信兑换二维码凭证',
   url                  varchar(100) not null comment '二维码URL',
   qrcodeName           varchar(50) not null comment '二维码图片名称',
   state                char(1) not null comment '二维码状态：I-初始状态，S-使用成功，F-使用失败',
   createTime           timestamp default '0000-00-00 00:00:00'  comment '创建时间',
   openId               varchar(100) comment '使用用户微信openId',
   transjnl             varchar(100) comment '交易流水号',
   orderId              varchar(100) comment '商户订单号',
   updateTime           timestamp default now() on update now() comment '修改时间',
   primary key (qrcodeSeq)
)DEFAULT CHARSET= UTF8 ENGINE = InnoDB;

alter table QrcodeImg comment '二维码表';



create table AccessToken (
   tokenSeq             integer not null auto_increment,
   mchId                varchar(20) not null comment '商户ID',
   accessToken          varchar(512) not null  comment 'accessToken',
   invalidTime          varchar(5) not null comment '有效时间',
   createTime           timestamp default '0000-00-00 00:00:00' comment '创建时间',
   updateTime           timestamp default now() on update now() comment '修改时间',
   primary key (tokenSeq)
)
DEFAULT CHARSET= UTF8 ENGINE = InnoDB;
alter table AccessToken comment 'AccessToken表';






create table CustomerInfo (
   openId               varchar(100) comment '使用用户微信openId',
   mchSeq               integer comment '商户Seq',
   nickName             varchar(100) comment '客户昵称',
   Sex                  char(1) comment '用户的性别，值为1时是男性，值为2时是女性，值为0时是未知',
   city                 varchar(50) comment '客户所在城市',
   country              varchar(50) comment '客户所在国家',
   province             varchar(50) comment '用户所在省份',
   language             varchar(20) comment '用户的语言，简体中文为zh_CN',
   headimgurl           varchar(200) comment '用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空',
   subscribeTime        varchar(20) comment '用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间',
   primary key (openId)
)
DEFAULT CHARSET= UTF8 ENGINE = InnoDB;
alter table CustomerInfo comment '客户信息表';



create table Advertisement (
   aSeq integer not null auto_increment comment '序列',
   mchSeq integer not null comment '商户Seq',
   advertisingURL varchar(200) not null comment '广告跳转URL',
   advertisingImg varchar(200) not null comment '广告图片',
   createTime timestamp default '0000-00-00 00:00:00' comment '创建时间',
   updateTime timestamp default now() on update now() comment '修改时间',
   primary key (aSeq)
)
DEFAULT CHARSET=  ENGINE = InnoDB;
alter table Advertisement comment '广告表';




create table Notice (
   nSeq integer not null auto_increment comment '序列',
   mchSeq integer not null comment '商户Seq',
   noticeName varchar(50) not null comment '',
   noticeContent varchar(2048) not null comment '',
   createTime timestamp default '0000-00-00 00:00:00' comment '创建时间',
   updateTime timestamp default now() on update now() comment '修改时间',
   primary key (nSeq)
)
DEFAULT CHARSET= UTF8 ENGINE = InnoDB;
alter table Notice comment '公告表';




create table WebMenu(
   wmSeq integer not null auto_increment comment '序列',
   wmName varchar(10) not null comment 'web 菜单名称',
   wmTransId varchar(30) not null comment '交易ID',
   createTime timestamp default '0000-00-00 00:00:00' comment '创建时间',
)
DEFAULT CHARSET= UTF8 ENGINE = InnoDB;
alter table WebMenu comment 'web菜单表';




create table MerchantMenuAssociation(
   wmSeq integer not null comment 'web菜单Seq',
   mchSeq integer not null comment '商户Seq',
   menuImg varchar(50) not null comment '菜单图标',
   primary key (wmSeq,mchSeq)
)
DEFAULT CHARSET= UTF8 ENGINE = InnoDB;
alter table MerchantMenuAssociation comment 'web菜单商户关联表';

--add script
create table JsapiTicket (
   ticketSeq             integer not null auto_increment,
   mchId                varchar(20) not null comment '商户ID',
   jsapiTicket          varchar(512) not null  comment 'jsapiTicket',
   invalidTime          varchar(5) not null comment '有效时间',
   createTime           timestamp default '0000-00-00 00:00:00' comment '创建时间',
   updateTime           timestamp default now() on update now() comment '修改时间',
   primary key (ticketSeq)
)
DEFAULT CHARSET= UTF8 ENGINE = InnoDB;
alter table AccessToken comment 'JsapiTicket表';







alter table UserInfo add constraint FK_Reference_1 foreign key (RoleSeq)
      references Role (RoleSeq) on delete restrict on update restrict;

alter table RoleMenuRelate add constraint FK_Reference_2 foreign key (RoleSeq)
      references Role (RoleSeq) on delete restrict on update restrict;

alter table RoleMenuRelate add constraint FK_Reference_3 foreign key (MenuId)
      references Menu (MenuId) on delete restrict on update restrict;

alter table Role add constraint FK_Reference_4 foreign key (channelId)
      references Channel (channelId) on delete restrict on update restrict;

alter table CreateQrcodeImg add constraint FK_Reference_5 foreign key (redPackSeq)
      references RedPack (redPackSeq) on delete restrict on update restrict;

alter table QrcodeImg add constraint FK_Reference_9 foreign key (CreateQISeq)
      references CreateQrcodeImg (CreateQISeq) on delete restrict on update restrict;

alter table Merchant add constraint FK_Reference_10 foreign key (channelId)
      references Channel (channelId) on delete restrict on update restrict;

alter table CustomerInfo add constraint FK_Reference_11 foreign key (mchSeq)
      references Merchant (mchSeq) on delete restrict on update restrict;

alter table Advertisement add constraint FK_Reference_12 foreign key (mchSeq)
      references Merchant (mchSeq) on delete restrict on update restrict;

alter table Notice add constraint FK_Reference_13 foreign key (mchSeq)
      references Merchant (mchSeq) on delete restrict on update restrict;

alter table MerchantMenuAssociation add constraint FK_Reference_14 foreign key (mchSeq)
      references Merchant (mchSeq) on delete restrict on update restrict;

alter table MerchantMenuAssociation add constraint FK_Reference_15 foreign key (wmSeq)
      references WebMenu (wmSeq) on delete restrict on update restrict;





