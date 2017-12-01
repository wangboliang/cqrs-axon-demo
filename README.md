[原文地址](http://edisonxu.org/archives/)
### 什么是CQRS
CQRS 架构全称是``Command Query Responsibility Segregation``，即命令查询职责分离。
### AxonFramework
AxonFramework是一个基于事件驱动的轻量级CQRS框架，既支持直接持久化Aggreaget状态，也支持采用EventSourcing，使用AxonFramework的应用架构如下
<br>
引入Axon非常简单，加入Maven依赖即可
```xml
<dependency>
  <groupId>org.axonframework</groupId>
  <artifactId>axon-core</artifactId>
  <version>${axon.version}</version>
</dependency>
```
AxonFramework的源码地址：https://github.com/AxonFramework/AxonFramework
<br>
包含了如下组件：
- ``core axon``的核心代码
- ``amqp`` 使用AMQP协议的MQ，如rabbit等，实现Event跨JVM的分发
- ``distributed-commandbus-jgroups`` 使用Jgroup实现跨JVM的Command分发
- ``distributed-commandbus-springcloud`` 与SpringCloud集成，使用DiscoveryClient和RESTemplate实现跨JVM的Command分发
- ``metrics`` 提供监控相关信息
- ``mongo`` 实现axon与mongoDB的集成
- ``spring-boot-autoconfigure`` 实现spring的autoconfigure支持，只需要提供相关Property就可以自动配置Axon
- ``spring-boot-starter-jgroups`` 用distributed-commandbus-jgroups加上spring autoconfigure，提供jgroup“一键”集成
- ``spring-boot-starter`` 与springboot集成
- ``spring`` 提供各种annotation，与spring集成