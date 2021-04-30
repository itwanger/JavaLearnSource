# learn-admin-ui




## 项目介绍

learn-admin-ui 是网站项目管理后台ui


### 技术选型

| 技术 | 说明 | 官网 |
| --- | --- | --- |
| Vue | 前端框架 | [https://vuejs.org/](https://vuejs.org/) |
| Vue-router | 路由框架 | [https://router.vuejs.org/](https://router.vuejs.org/) |
| Vuex | 全局状态管理框架 | [https://vuex.vuejs.org/](https://vuex.vuejs.org/) |
| ant design vue | 前端UI框架 | [https://www.antdv.com/](https://www.antdv.com/) |
| Axios | 前端HTTP框架 | [https://github.com/axios/axios](https://github.com/axios/axios) |
| vue-antd-admin | 开箱即用的中后台前端/设计解决方案 | [https://gitee.com/iczer/vue-antd-admin?_from=gitee_search](https://gitee.com/iczer/vue-antd-admin?_from=gitee_search) |


### 项目布局

``` lua
src -- 源码目录
├── api -- axios网络请求定义
├── assets -- 静态图片资源文件
├── components -- 通用组件封装
├── icons -- svg矢量图片文件
├── router -- vue-router路由配置
├── store -- vuex的状态管理
├── styles -- 全局css样式
├── utils -- 工具类
└── views -- 前端页面
    ├── home -- 首页
    ├── layout -- 通用页面加载框架
    ├── login -- 登录页

```

## 搭建步骤
- 下载node并安装：[https://nodejs.org/dist/v12.14.0/node-v12.14.0-x64.msi](https://nodejs.org/dist/v12.14.0/node-v12.14.0-x64.msi);
- 该项目为前后端分离项目，访问本地访问接口需搭建后台环境，搭建请参考后端项目[传送门](https://github.com/macrozheng/mall);
- 访问在线接口无需搭建后台环境，只需将config/dev.env.js文件中的BASE_API改为[http://120.27.63.9:8080](http://120.27.63.9:8080)即可;
- 克隆源代码到本地，使用IDEA打开，并完成编译;
- 在IDEA命令行中运行命令：npm install,下载相关依赖;
- 在IDEA命令行中运行命令：npm run dev,运行项目;
- 访问地址：[http://localhost:8090](http://localhost:8090) 即可打开后台管理系统页面;
- 具体部署过程请参考：[mall前端项目的安装与部署](http://www.macrozheng.com/#/deploy/mall_deploy_web)
- `注意`：如果遇到无法运行npm命令，需要配置npm的环境变量，如在path变量中添加：C:\Users\zhenghong\AppData\Roaming\npm;
- `注意`：如果遇到npm install无法成功下载依赖，请参考[使用Jenkins一键打包部署前端应用，就是这么6！](http://www.macrozheng.com/#/reference/jenkins_vue) 中`遇到的坑`部分。

