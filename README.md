#基于bboss es spring boot starter的maven工程
本实例是一个基于bboss es spring boot starter的demo maven工程，可供spring boot项目集成bboss elasticsearch rest client参考

展示了通过spring boot管理单集群功能和管理多集群功能

单集群测试用例：eshelloword-spring-boot-starter\src\test\java\org\bboss\elasticsearchtest\springboot\BBossESStarterTestCase.java
 * 单集群演示功能测试用例，spring boot配置项以spring.elasticsearch.bboss开头
 * 对应的配置文件为application.properties文件
 
多集群测试用例：eshelloword-spring-boot-starter\src\test\java\org\bboss\elasticsearchtest\springboot\MultiBBossESStartersTestCase.java
 * 多集群演示功能测试用例，spring boot配置项以spring.elasticsearch.bboss.集群名称开头，例如：
 * spring.elasticsearch.bboss.default 默认es集群
 * spring.elasticsearch.bboss.logs  logs es集群
 * 两个集群通过 org.bboss.elasticsearchtest.springboot.MultiESSTartConfigurer加载
 * 对应的配置文件为application-multi-datasource.properties文件

# spring boot工程集成说明
## 快速集成和应用 
非spring boot项目：
https://my.oschina.net/bboss/blog/1801273 

spring boot项目：
https://my.oschina.net/bboss/blog/1835601


## 在spring booter项目中配置es参数
单es集群配置，修改spring booter项目的配置文件：eshelloword-spring-boot-starter\src\main\resources\application.properties

多es集群配置，修改spring booter项目的配置文件：eshelloword-spring-boot-starter\src\main\resources\application-multi-datasource.properties
   
## 2.3 参考文档
https://my.oschina.net/bboss/blog/1556866

## 开发交流群
166471282
  