为了给小白学习 Java 提供一些帮助，我们创建了 LearnJava 在线学习网站。与此同时，我们把网站的源码也开放了——名字就叫，为的就是让大家在学习 Java 的时候，能多一个练手项目。

大家都知道，阅读优秀的源代码能够快速地提升编程功底，所以我们希望借此为大家提供一个优质的源码阅读项目。当然了，如果你想搭建学习网站的话，完全可以 fork 这份源码，自己做一些增删改查。

以后写在简历上，也是加分项。

# 项目结构介绍


| 项目名 | 作用 | 
| --- | --- |
| learn-mbg | MyBatis-Plus 根据数据库表直接生成的实体类模块 | 
| learn-common | 异常定义、JSON 格式、错误码定义等等公共模块 | 
| learn-web | 针对用户的 Web 的前后端模块 |  
| learn-admin | 针对网站管理员的 Web 后端模块 | 
| learn-admin-web | 针对网站管理员的 Web 前端模块 | 


# 项目部署说明

1）MySQL 数据库

- 新建数据库 learn；
- 下载 doc 目录下的 learn.sql 文件，将数据表导入到该数据库下。

2）导入项目

- 将 learn 目录导入到 Intellij IDEA 中；
- 等待项目构建完成；
- 打开 learn-admin/resources 目录下的 application-dev.yml，配置你自己的 datasource 的 username 和 password
- 打开 learn-web/resources 目录下的 application-dev.yml，配置你自己的 datasource 的 username 和 password

3）启动项目

- 运行 learn-admin 模块中的 LearnAdminBootstrap.java，测试地址：http://localhost:8081/swagger-ui/index.html
- 运行 learn-web 模块中的 LearnWebBootstrap.java，测试地址：http://localhost:8080/swagger-ui/index.html

4）针对 learn-admin-web 模块

该模块需要你本地环境安装 yarn、node 和 git，我们的技术栈基于 ES2015+、Vue、Antd，提前学习这些知识会非常有帮助。

- 进入该模块的目录下
- 执行 `yarn install`(可能你会遇到网络问题，需要科学上网，另外多把安装时候的错误信息进行搜索，寻找解决方案；如果实在不行，请微信联系我们（qing_geee），备注 lean)
 - 大致解决思路
 - 针对 macOS 系统
 - 重新安装 homebrew，使用国内镜像 `/bin/zsh -c "$(curl -fsSL https://gitee.com/cunkai/HomebrewCN/raw/master/Homebrew.sh)"`
 - 安装成功后，重新安装 yarn，使用命令 `brew install yarn`，使用 `yarn --version` 查看安装后的版本
 - 重新执行 `yarn install` 命令
- 这个过程需要保持耐心（嗯，来一包华子压压惊） 




# 后端技术

| 技术 | 说明 | 官网 |
| --- | --- | --- |
| Spring Boot | 容器+MVC框架 | [https://spring.io/projects/spring-boot](https://spring.io/projects/spring-boot) |
| Spring Security Oauth2 | 认证和授权框架 | [https://spring.io/projects/spring-security-oauth](https://spring.io/projects/spring-security-oauth) |
| MyBatis | ORM框架 | [http://www.mybatis.org/mybatis-3/zh/index.html](http://www.mybatis.org/mybatis-3/zh/index.html) |
| [MyBatis-Plus](https://baomidou.com/) | mybatis增强，低代码 | [https://baomidou.com/](https://baomidou.com/) |
| lucene | 搜索 | [https://lucene.apache.org/](https://lucene.apache.org/) |
| Redis | 分布式缓存 | [https://redis.io/](https://redis.io/) |
| Druid | 数据库连接池 | [https://github.com/alibaba/druid](https://github.com/alibaba/druid) |
| OSS | 对象存储 | [https://github.com/aliyun/aliyun-oss-java-sdk](https://github.com/aliyun/aliyun-oss-java-sdk) |
| JWT | JWT登录支持 | [https://github.com/jwtk/jjwt](https://github.com/jwtk/jjwt) |
| thymeleaf | 模板引擎方案 | [https://www.thymeleaf.org/](https://www.thymeleaf.org/) |
| arthas |性能监控 | [https://arthas.aliyun.com/doc/](https://arthas.aliyun.com/doc/) |

# 前端技术
| 技术 | 说明 | 官网 |
| --- | --- | --- |
| Vue | 前端框架 | [https://vuejs.org/](https://vuejs.org/) |
| Vue-router | 路由框架 | [https://router.vuejs.org/](https://router.vuejs.org/) |
| Vuex | 全局状态管理框架 | [https://vuex.vuejs.org/](https://vuex.vuejs.org/) |
| ant design vue | 前端UI框架 | [https://www.antdv.com/](https://www.antdv.com/) |
| Axios | 前端HTTP框架 | [https://github.com/axios/axios](https://github.com/axios/axios) |
| vue-vben-admin | 开箱即用的中后台前端/设计解决方案 | [https://github.com/anncwb/vue-vben-admin](https://github.com/anncwb/vue-vben-admin) |



# 开发环境
| 工具 | 下载 |
| --- | --- |
| JDK |  |
| Mysql | [https://www.mysql.com/](https://www.mysql.com/) |
| Redis | [https://redis.io/download](https://redis.io/download) |
| nginx | [http://nginx.org/en/download.html](http://nginx.org/en/download.html) |


# 参考资料
[https://halo.run](https://halo.run)
[https://www.learnjavaonline.org/](https://www.learnjavaonline.org/)
[https://www.jianshu.com/p/9317a927e844](https://www.jianshu.com/p/9317a927e844) git配置
![image.png](https://cdn.nlark.com/yuque/0/2021/png/354158/1618128832822-2b27668a-a983-4dc8-8c1a-1001eb0a84e9.png#align=left&display=inline&height=23&margin=%5Bobject%20Object%5D&name=image.png&originHeight=46&originWidth=1046&size=42299&status=done&style=none&width=523)
# 计划
![](https://cdn.nlark.com/yuque/0/2021/jpeg/354158/1619537457787-191595e4-138e-4bad-901b-e2c57e76d5fc.jpeg)

# 数据库设计
![在这里插入图片描述](https://github.com/itwanger/JavaLearnSource/blob/main/doc/%E6%95%B0%E6%8D%AE%E5%BA%93.png)

# 美景
https://user-images.githubusercontent.com/11729877/119362794-aa8c9180-bcdf-11eb-9488-ed7c9b50e150.mp4

