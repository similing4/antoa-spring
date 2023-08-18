## AntOAController
AntOAController定义于com.whuying.antoa.controller.AntOAController中，你需要的后台页面控制器均应继承自AntOAController。一个简单的后台控制器结构如下：
```
@RestController
@RequestMapping("/api/admin/user")
public class UserAntOAController extends AntOAController {
    @Override
    public void grid(Grid grid) {
        ;
    }

    @Override
    public String statistic() {
        return "";
    }

    @Override
    protected boolean checkPower(String uid) {
        return true;
    }
}
```
该类一共提供了九个接口和三个页面：

> 接口

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

> 页面

| 页面路由地址（URL#后面部分） | 说明 | 
| ------ | ----- |
| /xxx/list |列表页 |
| /xxx/create | 创建页 |
| /xxx/edit | 编辑页 |

为了方便开发，AntOAController中已经注册了这些路径的访问接口，您只需要在您编写的继承自AntOAController的控制器中加上@RequestMapping("/api/xxx")即可。其中xxx即页面路径，由您自己定义。

只要定义了继承自AntOAController的控制器并标有@RequestMapping标记且按规范命名，那么你就有了如下这些接口：

| 接口地址 | 请求方式 | 对应你定义的控制器方法 | 说明 | 
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

和如下这些页面（vue页面）

| 页面路由地址（URL#后面部分） | 说明 | 
| ------ | ----- |
| /xxx/list |列表页 |
| /xxx/create | 创建页 |
| /xxx/edit | 编辑页 |

这些接口是否可用受Grid对象控制。当然，你可以自己实现这些接口来实现功能，具体接口内容规范请参考《五、API文档》。

## 页面配置与Grid
一个AntOA的List页面由如下部分组成：
![/admin/work/list](grid_1.jpg)

Create与Edit页面均由纯表单组成。其中控制器的statistic方法返回的值会被展示在“统计信息”位置，其余内容均受Grid配置影响。具体配置方法详见《后台列表页》、《后台创建页》及《后台编辑页》三章。

## Grid的钩子
Grid实例共计5个钩子方法，分别对应如下：

### public Grid hookList(ListHook func);
设置一个列表页数据结果的钩子，该方法可以拦截列表查询结果，你可以通过这个钩子对查询结果进行修改后返回。

| 参数 | 参数类型 | 说明 |
| ------ | ----- | ------ |
| func | ListHook | 实现了 ListHook 接口的对象，你可以将钩子的回调内容定义于其抽象方法的实现中 |

#### 返回值：
返回this供链式调用

### public Grid hookCreate(CreateHook func);
设置一个创建页进行创建时的钩子，该方法可以拦截用户提交的表单内容，你可以通过这个钩子自行处理插入逻辑、拦截创建或返回修改后的数据让AntOA继续创建逻辑。


| 参数 | 参数类型 | 说明 |
| ------ | ----- | ------ |
| func | CreateHook | 实现了 CreateHook 接口的对象，你可以将钩子的回调内容定义于其抽象方法的实现中 |

#### 返回值：
返回this供链式调用

### public Grid hookDetail(DetailHook func);
设置一个编辑页查询当前编辑的项时的钩子，该方法可以拦截编辑页根据主键内容查询的结果，你可以通过这个钩子自行修改查询结果。

| 参数 | 参数类型 | 说明 |
| ------ | ----- | ------ |
| func | DetailHook | 实现了 DetailHook 接口的对象，你可以将钩子的回调内容定义于其抽象方法的实现中 |

#### 返回值：
返回this供链式调用

### public Grid hookSave(SaveHook func);
设置一个编辑页进行修改时的钩子，该方法可以拦截编辑页用户提交的表单内容，你可以通过这个钩子自行处理修改逻辑、拦截修改或返回修改后的数据让AntOA继续创建逻辑。

| 参数 | 参数类型 | 说明 |
| ------ | ----- | ------ |
| func | SaveHook | 实现了 SaveHook 接口的对象，你可以将钩子的回调内容定义于其抽象方法的实现中 |

#### 返回值：
返回this供链式调用

### public Grid hookDelete(DeleteHook func);
设置一个列表页进行删除时的钩子，该方法可以拦截列表页用户删除的主键信息，你可以通过这个钩子自行处理删除逻辑、拦截删除或返回修改后的数据让AntOA继续创建逻辑。

| 参数 | 参数类型 | 说明 |
| ------ | ----- | ------ |
| func | DeleteHook | 实现了 DeleteHook 接口的对象，你可以将钩子的回调内容定义于其抽象方法的实现中 |

#### 返回值：
返回this供链式调用