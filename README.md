### 什么是CQRS
CQRS 架构全称是``Command Query Responsibility Segregation``，即命令查询职责分离，名词本身最早应该是Greg Young提出来的，但是概念却很早就有了。
本质上，CQRS也是一种读写分离的机制，架构图如下：

![CQRS](https://raw.githubusercontent.com/wangboliang/cqrs-axon-demo/master/images/cqrs.png)

### AxonFramework
AxonFramework是一个基于事件驱动的轻量级CQRS框架，既支持直接持久化Aggreaget状态，也支持采用EventSourcing，使用AxonFramework的应用架构如下：

![CQRS](https://raw.githubusercontent.com/wangboliang/cqrs-axon-demo/master/images/axon.png)

[axon官网](https://docs.axoniq.io/)

[来源](http://edisonxu.com/tags/CQRS/)