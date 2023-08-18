GridEditForm与GridCreateForm内容大体一致，只不过GridEditForm必须要提供主键ID的column。
## GridEditForm对象用法
GridEditForm配置需要在AntOAController的子类中的grid方法中配置。依然以UserAntOAController举例：
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

## GridEditForm对象的实例化
GridEditForm对象需要由AntOAController的grid方法的grid参数的editForm方法创建。该参数接收一个DBEditOperator对象。你需要按照你的需求自行构造DBEditOperator对象。例：
```
    @Override
    public void grid(Grid grid) {
    	grid.editForm(new DBEditOperator(DB.table("user")) {}); //这里参数是一个继承自DBEditOperator的匿名对象，返回值是GridEditForm对象。
    }
```

其中DBEditOperator类属于com.whuying.antoa.db.DB的扩展类，com.whuying.antoa.db.DB类基于JOOQ（生成SQL）与JdbcTemplate（执行SQL）实现。DBEditOperator的定义如下：

```
public abstract class DBEditOperator {
    public DB builder;

    public DBEditOperator(DB builder) {
        this.builder = builder;
    }

    public Map<String, Object> find(String id) {
        return this.builder.where("id", id).first();
    }

    public int onUpdate(String primaryKey, Map<String, Object> param) {
        return this.builder.where(primaryKey, param.get(primaryKey)).update(param);
    }
}
```

你可以在你的实体类中重写这个父类方法来实现你的各种功能。

## GridEditForm对象的column系列实例方法

column系列方法用于表单项的配置。该方法可以将配置的信息以表单的形式展示到页面上并实现功能。

### public GridEditForm column(EditColumnBase columnItem);
column的通用方法

#### 参数：
* column 任意EditColumnBase子类的实例。所有继承自该类的实例均以EditColumn开头，且均定义于AntOA/Http/Utils/Model下。可用的实例如下：
    - EditColumnCascader 级联选择对象（参考[AntDesignVue-Cascader组件](https://www.antdv.com/components/cascader-cn)）
    - EditColumnChildrenChoose 表单项为一个按钮，点击该按钮后弹出列表页进行单选
    - EditColumnDisplay 仅用于展示，需要通过hook设置其值
    - EditColumnDivideNumber 用于展示数据与数据库字段为除以指定数值关系的需求。如输入的是元数据库中存储是分的情况。
    - EditColumnEnum 下拉单选（Select标签样式）
    - EditColumnEnumCheckBox 多选框
    - EditColumnEnumRadio 圆形Radio单选
    - EditColumnEnumTreeCheckBox 下拉多选（Select标签样式）
    - EditColumnFile 选择文件并上传到七牛云，后台接到七牛云目标文件链接
    - EditColumnFileLocal 选择文件并上传到服务端本地，后台接到上传文件的绝对地址
    - EditColumnFiles 选择多个文件并上传到七牛云，后台接到七牛云目标文件链接的数组json
    - EditColumnFilesLocal 选择多个文件并上传到服务端本地，后台接到上传文件的绝对地址的数组json
    - EditColumnHidden 隐藏字段，需要通过ChangeHook配置其值
    - EditColumnPassword 密码输入，插入时需要通过SaveHook对其加密。
    - EditColumnPicture 选择图片并上传到七牛云，后台接到七牛云目标图片链接
    - EditColumnPictureLocal 选择图片并上传到服务端本地，后台接到上传图片的绝对地址
    - EditColumnPictures 选择多个图片并上传到七牛云，后台接到七牛云目标图片链接的数组json
    - EditColumnPicturesLocal 选择多个图片并上传到服务端本地，后台接到上传图片的绝对地址的数组json
    - EditColumnRichText 富文本输入（WangEditor）
    - EditColumnText 普通文本输入
    - EditColumnTextarea 多行文本域输入
    - EditColumnTimestamp 时间输入

#### 返回值：
返回this供链式调用

### public GridEditForm columnText(String col, String colTip, String defaultVal);
设置一个普通文本输入的表单项

| 参数 | 参数类型 | 说明 |
| ------ | ----- | ------ |
| col | String | 表单name |
| colTip | String | 表单项目左侧提示内容 |
| defaultVal | String | 默认值（可不传） |

#### 返回值：
返回this供链式调用

### public GridEditForm columnNumberDivide(String col, String colTip, double divide, String unit, String defaultVal);
设置一个用于展示数据与数据库字段为除以指定数值关系的表单项。如输入的是元数据库中存储是分的情况。

| 参数 | 参数类型 | 说明 |
| ------ | ----- | ------ |
| col | String | 表单name |
| colTip | String | 表单项目左侧提示内容 |
| divide | Double | 除数 |
| unit | String | 展现给用户的单位名，如元（可不传） |
| defaultVal | String | 默认值（可不传） |

#### 返回值：
返回this供链式调用

### public GridEditForm columnTextarea(String col, String colTip, String defaultVal);
设置一个多行文本域输入的表单项。

| 参数 | 参数类型 | 说明 |
| ------ | ----- | ------ |
| col | String | 表单name |
| colTip | String | 表单项目左侧提示内容 |
| defaultVal | String | 默认值（可不传） |

#### 返回值：
返回this供链式调用

### public GridEditForm columnPassword(String col, String colTip, String defaultVal);
设置一个密码输入的表单项。

| 参数 | 参数类型 | 说明 |
| ------ | ----- | ------ |
| col | String | 表单name |
| colTip | String | 表单项目左侧提示内容 |
| defaultVal | String | 默认值（可不传） |

#### 返回值：
返回this供链式调用

### public GridEditForm columnSelect(String col, String colTip, List<EnumOption> options, String defaultVal);
设置一个下拉单选的表单项。

| 参数 | 参数类型 | 说明 |
| ------ | ----- | ------ |
| col | String | 表单name |
| colTip | String | 表单项目左侧提示内容 |
| options | List<EnumOption> | 选项数组 |
| defaultVal | String | 默认值（可不传） |

#### 返回值：
返回this供链式调用

### public GridEditForm columnRadio(String col, String colTip, List<EnumOption> options, String defaultVal);
设置一个Radio圆圈式的单选的表单项。

| 参数 | 参数类型 | 说明 |
| ------ | ----- | ------ |
| col | String | 表单name |
| colTip | String | 表单项目左侧提示内容 |
| options | List<EnumOption> | 选项数组 |
| defaultVal | String | 默认值（可不传） |

#### 返回值：
返回this供链式调用

### public GridEditForm columnCheckbox(String col, String colTip, List<EnumOption> options, String defaultVal);
设置一个Checkbox方块打勾式的多选的表单项。

| 参数 | 参数类型 | 说明 |
| ------ | ----- | ------ |
| col | String | 表单name |
| colTip | String | 表单项目左侧提示内容 |
| options | List<EnumOption> | 选项数组 |
| defaultVal | String | 默认值（可不传） |

#### 返回值：
返回this供链式调用

### public GridEditForm columnTreeCheckbox(String col, String colTip, List<TreeNode> options, String defaultVal);
设置一个下拉多选的表单项。

| 参数 | 参数类型 | 说明 |
| ------ | ----- | ------ |
| col | String | 表单name |
| colTip | String | 表单项目左侧提示内容 |
| options | List<TreeNode> | 选项数组 |
| defaultVal | String | 默认值（可不传） |

#### 返回值：
返回this供链式调用

### public GridEditForm columnTimestamp(String col, String colTip, String defaultVal);
设置一个时间输入的表单项。

| 参数 | 参数类型 | 说明 |
| ------ | ----- | ------ |
| col | String | 表单name |
| colTip | String | 表单项目左侧提示内容 |
| defaultVal | String | 默认值（可不传） |

#### 返回值：
返回this供链式调用

### public GridEditForm columnRichText(String col, String colTip, String defaultVal);
设置一个富文本输入的表单项。

| 参数 | 参数类型 | 说明 |
| ------ | ----- | ------ |
| col | String | 表单name |
| colTip | String | 表单项目左侧提示内容 |
| defaultVal | String | 默认值（可不传） |

#### 返回值：
返回this供链式调用

### public GridEditForm columnPicture(String col, String colTip, String defaultVal);
设置一个选择图片（上传到七牛云）的表单项。

| 参数 | 参数类型 | 说明 |
| ------ | ----- | ------ |
| col | String | 表单name |
| colTip | String | 表单项目左侧提示内容 |
| defaultVal | String | 默认值（可不传） |

#### 返回值：
返回this供链式调用

### public GridEditForm columnFile(String col, String colTip, String defaultVal);
设置一个选择文件（上传到七牛云）的表单项。

| 参数 | 参数类型 | 说明 |
| ------ | ----- | ------ |
| col | String | 表单name |
| colTip | String | 表单项目左侧提示内容 |
| defaultVal | String | 默认值（可不传） |

#### 返回值：
返回this供链式调用

### public GridEditForm columnPictures(String col, String colTip, String defaultVal);
设置一个选择多个图片（上传到七牛云）的表单项。

| 参数 | 参数类型 | 说明 |
| ------ | ----- | ------ |
| col | String | 表单name |
| colTip | String | 表单项目左侧提示内容 |
| defaultVal | String | 默认值（可不传） |

#### 返回值：
返回this供链式调用

### public GridEditForm columnFiles(String col, String colTip, String defaultVal);
设置一个选择多个文件（上传到七牛云）的表单项。

| 参数 | 参数类型 | 说明 |
| ------ | ----- | ------ |
| col | String | 表单name |
| colTip | String | 表单项目左侧提示内容 |
| defaultVal | String | 默认值（可不传） |

#### 返回值：
返回this供链式调用

### public GridEditForm columnCascader(String col, String colTip, List<CascaderNode> options, String defaultVal);
设置一个级联选择的表单项。

| 参数 | 参数类型 | 说明 |
| ------ | ----- | ------ |
| col | String | 表单name |
| colTip | String | 表单项目左侧提示内容 |
| options | List<CascaderNode> | 选项数组 |
| defaultVal | String | 默认值（可不传） |


#### 返回值：
返回this供链式调用

### public GridEditForm columnDisplay(String col, String colTip, String defaultVal);
设置一个只用于展示的表单项（不会提交）。

| 参数 | 参数类型 | 说明 |
| ------ | ----- | ------ |
| col | String | 表单name |
| colTip | String | 表单项目左侧提示内容 |
| defaultVal | String | 默认值（可不传） |

#### 返回值：
返回this供链式调用

### public GridEditForm columnHidden(String col, String defaultVal);
设置一个隐藏的表单项（会提交但不会展示）。

| 参数 | 参数类型 | 说明 |
| ------ | ----- | ------ |
| col | String | 表单name |
| defaultVal | String | 默认值（可不传） |

#### 返回值：
返回this供链式调用

### public GridEditForm columnChildrenChoose(String col, String colTip, GridListEasy gridListEasy, String gridListVModelCol, String gridListDisplayCol, String defaultVal);
设置一个表单项为一个按钮，点击该按钮后弹出列表页进行单选的表单项。

| 参数 | 参数类型 | 说明 |
| ------ | ----- | ------ |
| col | String | 表单name |
| colTip | String | 表单项目左侧提示内容 |
| gridListEasy | GridListEasy | GridList的简化版对象 |
| gridListVModelCol | String | 需要作为表单值的GridListEasy查询结果列 |
| gridListDisplayCol | String | 需要作为表单选项展示的GridListEasy查询结果列 |
| defaultVal | String | 默认值（可不传） |

#### 返回值：
返回this供链式调用

### public GridEditForm columnPictureLocal(String col, String colTip, String defaultVal);
设置一个选择图片（上传到服务器本地）的表单项。

| 参数 | 参数类型 | 说明 |
| ------ | ----- | ------ |
| col | String | 表单name |
| colTip | String | 表单项目左侧提示内容 |
| defaultVal | String | 默认值（可不传） |

#### 返回值：
返回this供链式调用

### public GridEditForm columnFileLocal(String col, String colTip, String defaultVal);
设置一个选择文件（上传到服务器本地）的表单项。

| 参数 | 参数类型 | 说明 |
| ------ | ----- | ------ |
| col | String | 表单name |
| colTip | String | 表单项目左侧提示内容 |
| defaultVal | String | 默认值（可不传） |

#### 返回值：
返回this供链式调用

### public GridEditForm columnPicturesLocal(String col, String colTip, String defaultVal);
设置一个选择多个图片（上传到服务器本地）的表单项。

| 参数 | 参数类型 | 说明 |
| ------ | ----- | ------ |
| col | String | 表单name |
| colTip | String | 表单项目左侧提示内容 |
| defaultVal | String | 默认值（可不传） |

#### 返回值：
返回this供链式调用

### public GridEditForm columnFilesLocal(String col, String colTip, String defaultVal);
设置一个选择多个文件（上传到服务器本地）的表单项。

| 参数 | 参数类型 | 说明 |
| ------ | ----- | ------ |
| col | String | 表单name |
| colTip | String | 表单项目左侧提示内容 |
| defaultVal | String | 默认值（可不传） |

#### 返回值：
返回this供链式调用

## GridEditForm对象的按钮系列方法
按钮系列方法用于表单项的对应的按钮配置。该方法可以将一个按钮添加到指定选项的右侧，点击这个按钮的时候调用自定义的接口实现功能。

由于按钮系列方法的实体类均继承自EditRowButtonBase，因此在实例化类时你需要手动实现实例的抽象方法：
```
abstract public boolean judgeIsShow(UrlParamCalculator calculator);
```

### public GridEditForm rowButton(EditRowButtonBase buttonItem);
在指定表单项后方添加一个请求数据的按钮。按钮返回值可以赋值到当前已有表单项中（页面跳转的除外）。

#### 参数：
* buttonItem 任意EditRowButtonBase子类的实例。所有继承自该类的实例均以EditRowButton开头，且均定义于AntOA/Http/Utils/Model下。可用的实例如下：
    - EditRowButtonApi 点击后直接调用API的按钮
    - EditRowButtonApiWithConfirm 点击后弹出确认框，用户确认后调用API接口的按钮
    - EditRowButtonBlob 点击后下载API接口返回的文件流的按钮
    - EditRowButtonNavigate 点击后跳转页面的按钮
    - EditRowButtonRichText 点击后弹出文本框展示API调用结果的按钮


#### 返回值：
返回this供链式调用

### public GridEditForm rowNavigateButton(EditRowButtonNavigate editRowButtonItem);
在指定表单项后方添加一个用于跳转页面的按钮。

| 参数 | 参数类型 | 说明 |
| ------ | ----- | ------ |
| editRowButtonItem | EditRowButtonNavigate | EditRowButtonNavigate类的实例 |

#### 返回值：
返回this供链式调用

### public GridEditForm rowApiButton(EditRowButtonApi editRowButtonItem);
在指定表单项后方添加一个调用API并将返回值填充到表单的按钮。

| 参数 | 参数类型 | 说明 |
| ------ | ----- | ------ |
| editRowButtonItem | EditRowButtonApi | EditRowButtonApi类的实例 |

#### 返回值：
返回this供链式调用

### public GridEditForm rowBlobButton(EditRowButtonBlob editRowButtonItem);
在指定表单项后方添加一个调用API并将返回值作为文件流下载的按钮。

| 参数 | 参数类型 | 说明 |
| ------ | ----- | ------ |
| editRowButtonItem | EditRowButtonBlob | EditRowButtonBlob类的实例 |

#### 返回值：
返回this供链式调用

### public GridEditForm rowApiButtonWithConfirm(EditRowButtonApiWithConfirm editRowButtonItem);
在指定表单项后方添加一个需要弹窗确认之后调用API并将返回值填充到表单的按钮。

| 参数 | 参数类型 | 说明 |
| ------ | ----- | ------ |
| editRowButtonItem | EditRowButtonApiWithConfirm | EditRowButtonApiWithConfirm类的实例 |

#### 返回值：
返回this供链式调用

### public GridEditForm rowRichTextButton(EditRowButtonRichText editRowButtonItem);
在指定表单项后方添加一个调用API并将返回值作为富文本弹窗展示的按钮。

| 参数 | 参数类型 | 说明 |
| ------ | ----- | ------ |
| editRowButtonItem | EditRowButtonRichText | EditRowButtonRichText类的实例 |

#### 返回值：
返回this供链式调用

## GridEditForm对象的钩子方法
如果你想监听用户表单的内容的变化，你可以通过本方法来实现。比如你可以使用本功能实现点击按钮后切换展示的表单项。

### public GridEditForm setChangeHook(CreateOrEditColumnChangeHook hook);
为指定表单项添加一个变化监听钩子，这个值发生变化时会触发参数对应的回调方法。

| 参数 | 参数类型 | 说明 |
| ------ | ----- | ------ |
| hook | CreateOrEditColumnChangeHook | CreateOrEditColumnChangeHook类的实例，内含用于客户端回调的抽象方法 |

#### 返回值：
返回this供链式调用