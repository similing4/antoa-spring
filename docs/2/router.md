## API接口及访问路径

#### 框架自带接口
框架自带接口定义于两个控制器之上：

```
com.whuying.antoa.controller.AuthController
com.whuying.antoa.controller.AntOAUserController
```

共计四个接口：

| 接口地址 | 请求方式 | 对应控制器 | 对应控制器方法 | 说明 |
| ------ | ----- | ------ | ----- | ------ |
| /auth/login | POST | AuthController | login | 登录页面用于授权登录的接口 |
| /auth/auth | POST | AuthController | auth | 登录页面用于判断是否已经授权登录的接口 |
| /auth/config | GET | AuthController | api_config | 获取路由配置、七牛云配置等信息的接口 |
| /user/change_password | POST | AntOAUserController | changePassword | 修改当前登录用户的密码的接口 |

接口详细信息请参考API文档

#### 控制器相关接口

这里的控制器相关接口特指继承自com.whuying.antoa.controller.AntOAController类的控制器。
如果定义了这种控制器，你需要在你对应类的定义处注册接口前缀，定义方式：
```java
	//假设你定义的路由路径是home，控制器名为HomeController，那么你应该这么定义：
    @RestController
    @RequestMapping("/api/home/user")
    public class HomeController extends AntOAController {
        //...
    }
	//此时你访问 /antoa/webpack/#/home/list 即可访问该控制器对应路由
```

这里只是做了简单介绍，详见 三、Grid的使用->控制器与Grid。

#### 自定义接口路由
定义在模块内自己定义的路由内容。对应路由指向方法只要是定义在继承自com.whuying.antoa.controller.AntOAController类的控制器中，调用this.getUserInfo()方法即可进行后台授权鉴权。默认自定义接口不包含鉴权功能！详情请参考API文档。

### 前端接口路由
前端接口路由主要由全局的配置文件application.yml配置。antoa配置文件如下：
```

```yml
server:
  port: 8080
spring:
  application:
    name: maven-springboot
  datasource:
    url: jdbc:mysql://127.0.0.1/test?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai
    username: test
    password: test
    driver-class-name: com.mysql.cj.jdbc.Driver
antoa:
  config:
    home_page: 
    login_diy: false
    menu_routes: #菜单路由配置，path为完整URL链接，name为在菜单上显示的内容，breadcrumbname为面包屑上显示的标题，不设置默认为name
       - name: 首页 #页面名称，将显示在侧边栏及标签页上，如果不设置breadcrumbname也将设置为面包屑。
         home: true #使用ant-design-vue-admin时此指定此页面组为首页/首页，设置的children将被无视，如需设置首页内容需要自定义vue项目的home.vue文件，只能设置在第一层且只能设置一个。
       - name: 用户管理
         role_limit_id: 1
         children: 
             - path: /admin/user/list
               name: 用户列表
               role_limit_id: 1 #权限限制，不设置为不限制
             - visible: false #设置左侧导航栏中不显示该页面
               path: /admin/user/create
               name: 用户创建
               role_limit_id: 1
             - visible: false
               path: /admin/user/edit
               name: 用户编辑
               role_limit_id: 1
    qiniu: #七牛云的配置，如果你使用了七牛云文件上传那么该项必填
      access_key: QiniuAccessKey #七牛云的AccessKey，可以在用户头像下的秘钥管理处获取
      secret_key: QiniuSecretKey #七牛云的SecretKey，同上
      bucket: Bucket #Bucket名称，七牛云的对象存储-空间管理中的空间名称即为Bucket名称
      url: http://xxx.xxx.com/ #访问域名，如https://qn.github.com/，注意要带末尾的斜杠。可以在七牛云的对象存空间管理中的域名中绑定，http或https由空间配置决定，请尽量不要用测试域名避免因过期而造成困扰。
    template_file_path: template
```

上述配置文件会被AntOA解析并通过/auth/config接口传给前端用于渲染页面、侧边栏及组件等。

## 页面

页面分为控制器配置页面和自定义页面两种。

### 自定义页面

详见 四、功能扩展->扩展页面

### 控制器配置页面

继承自com.whuying.antoa.controller.AntOAController的且在application.yml中配置的页面即可访问。控制器配置的页面主要包括：

1.list页面

list页面为后台管理的列表页，可通过Grid对应的方法来配置页面展示信息与交互。

2.create页面

create页面为后台管理的创建页，可通过Grid对应的方法来配置页面展示信息与交互。

3.edit页面

edit页面为后台管理的编辑页，可通过Grid对应的方法来配置页面展示信息与交互。

它们对应的接口有（假设你定义的路由路径是xxx）：

| 接口地址 | 请求方式 | 对应AntOA控制器方法 | 说明 | 
| ------ | ----- | ------ | ----- |
| /api/xxx/list | POST | apiListResponse | 获取列表页的列表数据 | 
| /api/xxx/create | POST | apiCreateResponse | 创建页进行创建操作的接口 | 
| /api/xxx/detail | POST | apiDetailResponse | 获取编辑页待编辑行数据的接口 | 
| /api/xxx/detail_column_list | POST | apiDetailColumnListResponse | 获取ColumnChildrenChoose功能的对应列表数据信息 | 
| /api/xxx/save | POST | apiSaveResponse | 编辑页进行保存修改操作的接口 | 
| /api/xxx/delete | GET | apiDeleteResponse | 列表页进行删除操作的接口 | 
| /api/xxx/column_change | POST | apiColumnChangeResponse | 待监听的字段值发生改变时调用的钩子接口 | 
| /api/xxx/grid_config | POST | apiGridConfigResponse | 获取后台配置的列表页创建页编辑页结构信息 | 
| /api/xxx/upload | POST | apiUploadFileResponse | 上传文件到服务端接口 | 

对应的接口文档详见API文档。