Bboss is a good elasticsearch Java rest client. It operates and accesses elasticsearch in a way similar to mybatis.

# BBoss Environmental requirements

JDK requirement: JDK 1.7+

Elasticsearch version requirements: 1.X,2.X,5.X,6.X,+

Spring booter 1.x,2.x,+
# 基于bboss es spring boot starter的maven工程
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

# 运行demo
src/test/java目录下面提供提供两个可以直接运行的的junit测试用例
## 单集群测试用例运行
org.bboss.elasticsearchtest.springboot.BBossESStarterTestCase
## 多集群测试用例运行
org.bboss.elasticsearchtest.springboot.MultiBBossESStartersTestCase

## 在spring booter项目中配置es参数
yml配置(默认配置)
单es集群配置，修改spring booter项目的配置文件：eshelloword-spring-boot-starter\src\main\resources\application.yml

多es集群配置，修改spring booter项目的配置文件：eshelloword-spring-boot-starter\src\main\resources\application-multi-datasource.yml

properties配置：如果需要采用properties配置，则将下面的两个文件拷贝到resources目录下面，把后缀.example去掉即可，记住yml和properties只能保留一个

单es集群配置，修改spring booter项目的配置文件：eshelloword-spring-boot-starter\src\main\resources\config\application.properties.example

多es集群配置，修改spring booter项目的配置文件：eshelloword-spring-boot-starter\src\main\resources\config\application-multi-datasource.properties.example

## 快速集成和应用 

非spring boot项目：

https://esdoc.bbossgroups.com/#/common-project-with-bboss

spring boot项目：

https://esdoc.bbossgroups.com/#/spring-booter-with-bboss

详细配置说明参考文档：

https://esdoc.bbossgroups.com/#/development

## 开发交流群
166471282
  