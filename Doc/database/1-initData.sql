--创建内置渠道
INSERT INTO channel (channelId, channelName, createTime, updateTime, state, appId, wxToken, appSecret) VALUES ('tfdor', '内置渠道', now(), now(), 'N', 'wx67c2134f4935acfb', 'wx66ba812ba25ec00a', 'b9d81b13ac888108be2c96723d1a9169');


--创建角色--内置最高权限角色
insert into role (`RoleSeq`,`channelId`, `RoleName`, `CreateTime`) values (1,'tfdor','Admin',now());
--初始化用户 密码 88888888
insert into userinfo (userId,userName,password,sex,age,addr,mobilePhone,phone,idType,idNo,RoleSeq,CreateTime,channelId)
values('admin1','admin1','6+exnvC5D+ydvlsiSizl4g==','M',25,'湖南岳阳','15150667366','0730-7165261','00','430621199111258112',1,now(),'tfdor');

insert into userinfo (userId,userName,password,sex,age,addr,mobilePhone,phone,idType,idNo,RoleSeq,CreateTime,channelId)
values('admin2','admin2','R/Iyn2+kzn8jniEyiXorEQ==','M',25,'湖南岳阳','15150667366','0730-7165261','00','430621199111258112',1,now(),'tfdor');


-----------------------------
--*********系统管理*********--
-----------------------------
--创建菜单
--一级菜单
insert into menu (`MenuId`, `MenuName`, `ParentId`, `OrderId`, `TransId`, `CreateTime`) values ('SystemSettings','系统设置','00000000',1,'',now());
--二级菜单
insert into menu (`MenuId`, `MenuName`, `ParentId`, `OrderId`, `TransId`, `CreateTime`) values ('RoleManager','角色管理','SystemSettings',1,'RoleManager',now());
insert into menu (`MenuId`, `MenuName`, `ParentId`, `OrderId`, `TransId`, `CreateTime`) values ('UserManager','用户管理','SystemSettings',2,'UserManager',now());
insert into menu (`MenuId`, `MenuName`, `ParentId`, `OrderId`, `TransId`, `CreateTime`) values ('PasswordManager','密码管理','SystemSettings',3,'PasswordManager',now());


--授权 --Admin
insert into rolemenurelate (`RoleSeq`, `MenuId`) values(1,'SystemSettings');
insert into rolemenurelate (`RoleSeq`, `MenuId`) values(1,'RoleManager');
insert into rolemenurelate (`RoleSeq`, `MenuId`) values(1,'UserManager');
insert into rolemenurelate (`RoleSeq`, `MenuId`) values(1,'PasswordManager');

-----------------------------
--*********任务中心*********--
-----------------------------
--一级菜单
insert into menu (`MenuId`, `MenuName`, `ParentId`, `OrderId`, `TransId`, `CreateTime`) values ('MissionCenter','任务中心','00000000',2,'',now());
--二级菜单
insert into menu (`MenuId`, `MenuName`, `ParentId`, `OrderId`, `TransId`, `CreateTime`) values ('CheckQuery','待复合查询','MissionCenter',1,'CheckQuery',now());
insert into menu (`MenuId`, `MenuName`, `ParentId`, `OrderId`, `TransId`, `CreateTime`) values ('CheckResultQuery','复合结果查询','MissionCenter',2,'CheckResultQuery',now());

--授权 Admin
insert into rolemenurelate (`RoleSeq`, `MenuId`) values(1,'MissionCenter');
insert into rolemenurelate (`RoleSeq`, `MenuId`) values(1,'CheckQuery');
insert into rolemenurelate (`RoleSeq`, `MenuId`) values(1,'CheckResultQuery');

-----------------------------
--*********营销推广*********--
-----------------------------

-- 一级菜单
insert into menu (`MenuId`, `MenuName`, `ParentId`, `OrderId`, `TransId`, `CreateTime`) values ('MarketingManager','营销推广','00000000',3,'',now());
-- 二级菜单
insert into menu (`MenuId`, `MenuName`, `ParentId`, `OrderId`, `TransId`, `CreateTime`) values ('QrCodeManager','二维码管理','MarketingManager',1,'QrCodeManager',now());
--授权 Admin
insert into rolemenurelate (`RoleSeq`, `MenuId`) values(1,'MarketingManager');
insert into rolemenurelate (`RoleSeq`, `MenuId`) values(1,'QrCodeManager');




