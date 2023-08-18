# AntOA

### 一个基于 Vue AntD Admin 二次开发与 SpringBoot 结合的后台 OA 管理系统

Vue AntD Admin 项目地址：https://github.com/iczer/vue-antd-admin

## 使用

### 预配置：

在安装运行之前需要你去设置域名及模块相关配置：

#### 1.设置该文件夹下的.env 文件

VUE_APP_API_BASE_URL=你的域名（如http://www.baidu.com，注意末尾没有斜杠/，正式打包时记得留空）

#### 2.设置模块的 application.yml 文件

详见根目录的 Readme.md

### 开发模式：

cd进入本目录，运行如下代码（推荐使用yarn，速度更快）：

```
$ yarn
$ yarn serve
```

运行成功后浏览器访问http://localhost:8080/antoa/webpack/ 即可进行开发调试。

### 部署模式：

```
$ yarn
$ yarn build
```

运行成功后浏览器访问 你的域名/antoa/webpack/ 即可查看部署效果。

详细开发方式详见根目录开发文档。
