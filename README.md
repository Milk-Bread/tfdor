#com.crrn.tfdor.main 后台管理系统
****
**目录结构**
>[com.crrn.tfdor.main][main_url]
>
> * [com.crrn.tfdor.common][common_url]-核心控制器
> * [com.crrn.tfdor.service.manage][manage_url]-管理端服务
> * [com.crrn.tfdor.service.wechat][wechat_url]-微信服务
> * [com.crrn.tfdor.dao][dao_url]-dao层，数据库操作
> * [com.crrn.tfdor.domain][domain_url]-实体bean
> * [com.crrn.tfdor.utils][utils_url]-工具类


[main_url]: https://https://github.com/Milk-Bread/tfdor/tree/master/com.crrn.tfdor.main
[common_url]: https://https://github.com/Milk-Bread/tfdor/tree/master/com.crrn.tfdor.main/com.crrn.tfdor.common
[manage_url]: https://github.com/Milk-Bread/tfdor/tree/master/com.crrn.tfdor.main/com.crrn.tfdor.service.manage
[wechat_url]: https://github.com/Milk-Bread/tfdor/tree/master/com.crrn.tfdor.main/com.crrn.tfdor.service.wechat
[dao_url]: https://github.com/Milk-Bread/tfdor/tree/master/com.crrn.tfdor.main/com.crrn.tfdor.dao
[domain_url]: https://github.com/Milk-Bread/tfdor/tree/master/com.crrn.tfdor.main/com.crrn.tfdor.domain
[utils_url]: https://github.com/Milk-Bread/tfdor/tree/master/com.crrn.tfdor.main/com.crrn.tfdor.utils




##开发说明

**一、添加菜单方法**

```
1、向菜单表插入菜单
	* insert into menu (`MenuId`, `MenuName`, `ParentId`, `OrderId`, `TransId`, `CreateTime`) 
	         	values ('MenuId','MenuName','ParentId',4,'MenuId.do',now());
	* 字段说明：
	  menuId:菜单ID（唯一）
	  menuName：菜单名称
	  parentId：父菜单ID，一级菜单默认【00000000】，二级菜单的父级菜单为【一级菜单ID】
	  orderId：菜单展示顺序，数字越小展示越前
	  transId：前台页面跳转路径（路由ID）
2、给系统内置管理员角色添加权限
	* insert into rolemenurelate (`RoleSeq`, `MenuId`) values(1,'MenuId');
	* 字段说明：
	  RoleSeq：角色序号，内置管理员角色序号为 1
	  MenuId：添加的菜单ID
```

**二、交易开发步骤**

```
1、在com.crrn.tfdor.common中找到相应的Controller，添加方法
	* 方法如下
	  @RequestMapping(value = "audiResultList.do", method = RequestMethod.POST)
	  @ResponseBody
	  public Object audiResultList(HttpServletRequest request, HttpServletResponse response) {
        //具体方法实现
        return '';
      }
	* 方法说明
	  @RequestMapping注解中value 为接口名称，method为请求方式POST或GET
2、在com.crrn.tfdor.service.manage中添加服务提供给Controller调用
	* 定义接口方法
	  public PageInfo<Map<String,Object>> audiResultList(Map<String, Object> param);
    
	* 实现接口方法
	  @Override
	  public PageInfo<Map<String, Object>> audiResultList(Map<String, Object> param) {
        //具体方法实现
        return page;
	  }
3、在com.crrn.tfdor.dao中定义数据库操作方法提供给service层调用
	* 方法如下
	  public List<Map<String, Object>> audiResultList(Map<String, Object> param);
	* 在相应的mapper中编写sql语句
	  <select id="audiResultList" parameterType="java.util.Map" resultType="java.util.Map">
        select auditingSeq,auditingTrans,auditingName,auditingData,promoterSeq,promoter,
        auditPersonSeq,auditPerson,DATE_FORMAT(createTime,'%Y-%m-%d %H:%i:%s') createTime,
        state,remarks FROM Auditing WHERE  auditPersonSeq = #{auditPersonSeq} and state in ('F','S')
        ORDER BY createTime DESC
      </select>
```