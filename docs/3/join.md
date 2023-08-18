多表联查主要涉及到MultiTableDBListOperator类。MultiTableDBListOperator类是一个DBListOperator的子类，它实现了将多个表通过join融合并查询出结果的功能。具体定义可参考com.whuying.antoa.utils.MultiTableDbListOperator。
## MultiTableDBListOperator类的实例化
这里我们先举个实际使用的例子，假设有两个表分别是create_log、user，create_log的uid字段是user的主键，那么查询create_log的id、phone与user的user_realname的例子如下：
```
List<JoinTable> joinTableList = new ArrayList<>();
List<TableColumns> tableColumnsList = new ArrayList<>();
joinTableList.add(new JoinTable("user", "b", "id", "a", "uid"));
tableColumnsList.add(new TableColumns("create_log", "a", null));
Map<String, Object> columns = new HashMap<String, Object>();
columns.put("fuser_realname", "user_realname");
tableColumnsList.add(new TableColumns("user", "b", columns)); //这个键值对数组的键是查询结果，值是数据库字段，二者可以一致，但不能与前面配置的冲突。
MultiTableDBListOperator operator = new MultiTableDBListOperator("create_log", "a", joinTableList, tableColumnsList);
grid.list(operator)
    .columnText("id", "ID")
    .columnText("fuser_realname", "姓名")
    .columnText("phone", "手机号");
```
MultiTableDBListOperator共计有4个参数分别如下：
* mainTable 主表，不容分说，就是要查询的主表
* mainTableAlias 给主表起个别名以避免同名表连接造成冲突。
* joinTables 左连接的表及其与主表连接方式的对象的数组（List<JoinTable>类型）
	这里的JoinTable对象构造方法有5个参数：
	- table 待左连接的表名
    - alias 待左连接的表别名
    - id 待左连接的表对应的条件键（比如select * from a left join b on a.uid = b.id，那么这个参数就应该是id）
    - originTable 左连接到的表别名（比如select * from a left join b on a.uid = b.id，那么这个参数就应该是a）
    - originId 左连接到的表对应的条件键（比如select * from a left join b on a.uid = b.id，那么这个参数就应该是uid）
* tableColumnsMap 要查哪个表的哪些字段的对象的数组（List<TableColumns>类型）
	这里的TableColumns对象构造方法有3个参数：
	- table 待查表原名（比如lw_user表别名为a就传入user）
	- alias 待查表别名（比如lw_user表别名为a就传入a）
	- columns 待查表的字段（比如要lw_user表里的username、password字段重命名成user_username、user_password就传["user_username"=>"username","user_password"=>"password"]）
	多个TableColumns对象的重命名的名称不能存在重复。比如第一个TableColumns对象columns参数是["user_username"=>"username","user_password"=>"password"]，那么就不能有columns参数以"user_username"、"user_password"为键的其他的TableColumns对象