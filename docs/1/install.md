## 环境要求

Java1.8 + eclipse(Maven)

## 编译运行

克隆本项目，并使用eclipse打开本项目，右键点击本项目，选择Run As->Java Application。找到 com.whuying.MainApplication 运行。

## 打包运行

### 打包jar包

右键点击本项目，选择Run As->Maven clean，执行完成后再右键点击本项目，选择Run As->Maven install。执行完成后，项目中target目录下出现了一个jar文件即为打包的jar包。

### 部署运行

复制项目下的src/main/resources/public文件夹到jar包同目录下。

在存在java1.8环境的命令行下进入jar包所在目录后执行命令行（其中xxx.jar为你的jar包）：

```shell script
java -cp public -jar xxx.jar
```

前端代码请查看本项目前端部分（如果没有特殊需求不需要修改前端代码）：[AntOA Frontend](https://github.com/similing4/antoa-spring/main/src/main/resources/Modules/AntOA/frontend)

## 初始配置及修改配置
### application.yml

修改你的项目/src/main/resources/application.yml 文件以实现后台的菜单及页面配置。

如果你使用了包含文件上传的功能如上传文件、上传图片、富文本Wangeditor的上传图片等，请在配置文件中设置七牛云信息。

配置好后，运行并访问 http://你的域名/antoa/webpack/index.html 看看吧~ 默认账号密码是admin admin