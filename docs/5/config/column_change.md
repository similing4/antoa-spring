> 请求地址

```
/api{$配置路由}/column_change
```

> 调用方式：

```
POST JSON
```

> 接口描述：

当创建页/编辑页对应包含于createOrEditColumnChangeHookCollection的字段发生变化时请求的接口，以便于后端能处理表单内容数据变化。

> 请求参数:

* HEADER参数：

| 字段名称 | 字段说明 | 类型 | 必填 | 备注 |
| ---- | ---- | ---- | ---- | ---- |
| Authorization | Token请求头 | String | Y | 通过登录授权接口获取 | 

* GET参数:

无

* POST参数（JSON）:

| 字段名称 | 字段说明 | 类型 | 必填 | 备注 |
| ---- | ---- | ---- | ---- | ---- |
| type | 表单类型 | String | Y | 创建页传create，编辑页传edit |
| form | 表单项 | Object | Y | 根据配置的column系列方法变化而变化，具体应为《获取配置路由的配置信息》接口的grid属性的create/edit属性(type参数)的createColumnCollection响应字段每一项的col属性及其对应的值 |
| page | 页面url的Query参数 | Object | Y | 页面url参数，如/user/edit?id=1&s=a则传入{"id":"1","s":"a"} |
| col | 发生变化的字段 | String | Y | 数据发生变化的字段，如username数据发生了变化那么本字段传入"username" |

> 请求返回结果:

* 成功时

```json
{
	"status": 1,
	"data": {
		"username": ""
	},
	"displayColumns": [
		"username",
		"password"
	]
}
```

* 失败时

```json
{
	"status": 0,
	"msg": "登录失效"
}
```

> 请求返回结果参数说明:

| 字段名称 | 字段说明 | 类型 | 存在条件 | 备注 |
| ---- | ---- | ---- | ---- | ---- |
| status | 响应码 | int | 一定存在 | 值为1时请求成功，否则请求失败 |
| data | 覆盖于表单值的对象 | Object | status为1时 | 对象键为对应表单项，值为对应修改后的值 |
| displayColumns | 展示的字段 | Array或null | status为1时可能存在 | 当字段不存在或为null时不进行任何操作，对象字段存在时只显示数组内包含的字段其余隐藏 |
| msg | 请求失败原因 | String | status为0时 | - |

> CURL请求示例

* 假设配置路由为/admin/user，于创建页/admin/user/create?fid=3，表单项只有name字段，且createOrEditColumnChangeHookCollection不为null且其中包含col值为name的对象，当name字段发生改变（改变后的值为666）则curl请求示例如下：

```
curl --location --request POST 'https://similing.gitee.io/api/admin/user/column_change' \
--header 'Authorization: 11_f42332f725ee9101a8b0ce39e55acdd1' \
--header 'Content-Type: application/json' \
--data '{"type":"create","form":{"name":"666"},"page":{"fid":"3"},"col":"name"}'
```