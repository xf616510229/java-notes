# 前言

@author: [Feathers](http://feathers.me)

@email: 616510229@qq.com

整理了memcached的相关笔记，以及常见的几种java client的使用方式

对应代码：https://github.com/xf616510229/memcached



# Memcahce简介

memcache是一套分布式的高速缓存系统，由LiveJournal的Brad Fitzpatrick开发，但目前被许多网站使用以提升网站的访问速度，尤其对于一些大型的、需要频繁访问数据库的网站访问速度提升效果十分显著。这是一套开放源代码软件，以BSD license授权发布。

MemCache的工作流程如下：先检查客户端的请求数据是否在memcached中，如有，直接把请求数据返回，不再对数据库进行任何操作；如果请求的数据不在memcached中，就去查数据库，把从数据库中获取的数据返回给客户端，同时把数据缓存一份到memcached中（memcached客户端不负责，需要程序明确实现）；每次更新数据库的同时更新memcached中的数据，保证一致性；当分配给memcached内存空间用完之后，会使用LRU（Least Recently Used，最近最少使用）策略加上到期失效策略，失效数据首先被替换，然后再替换掉最近未使用的数据。

Memcache是一个高性能的分布式的内存对象缓存系统，通过在内存里维护一个统一的巨大的hash表，它能够用来存储各种格式的数据，包括图像、视频、文件以及数据库检索的结果等。简单的说就是将数据调用到内存中，然后从内存中读取，从而大大提高读取速度。

Memcached是以守护程序(监听)方式运行于一个或多个服务器中，随时会接收客户端的连接和操作。



# 配置环境

centos下安装memcached：

> 安装：` yum install memcached`
>
> 启动： `/usr/bin/memcached -d -l 127.0.0.1 -p 11211 -m 150 -u root`
>
> 启动服务：`service start memcached`
>
> telnet连接：telnet 127.0.0.1 11211
>
> telnet安装：
>
> `yum install telnet`
>
> `yum install telnet-server`

windows下安装memcached：http://blog.csdn.net/l1028386804/article/details/61417166

windows下安装telnet：

> 打开或关闭windows功能，勾选telnet相关选项



# 使用memcached

**修改命令语法**:

```
command <key> <flags> <expiration time> <bytes> // 空一行
<value>
```



表 1 定义了 memcached 修改命令的参数和用法。

**表 1. memcached 修改命令参数**

| 参数              | 用法                              |
| --------------- | ------------------------------- |
| key             | key 用于查找缓存值                     |
| flags           | 可以包括键值对的整型参数，客户机使用它存储关于键值对的额外信息 |
| expiration time | 在缓存中保存键值对的时间长度（以秒为单位，0 表示永远）    |
| bytes           | 在缓存中存储的字节点                      |
| value           | 存储的值（始终位于第二行）                   |

现在，我们来看看这些命令的实际使用。

## set

`set` 命令用于向缓存添加新的键值对。如果键已经存在，则之前的值将被替换。

注意以下交互，它使用了 `set` 命令：

```
set userId 0 0 5
12345
STORED
```

如果使用 `set` 命令正确设定了键值对，服务器将使用单词 **STORED** 进行响应。本示例向缓存中添加了一个键值对，其键为 `userId`，其值为 `12345`。并将过期时间设置为 0，这将向 memcached 通知您希望将此值存储在缓存中直到删除它为止。

## add 

仅当缓存中不存在键时，`add` 命令才会向缓存中添加一个键值对。如果缓存中已经存在键，则之前的值将仍然保持相同，并且您将获得响应 **NOT_STORED**。

下面是使用 `add` 命令的标准交互：

```
set userId 0 0 5
12345
STORED

add userId 0 0 5
55555
NOT_STORED

add companyId 0 0 3
564
STORED
```

## replace

仅当键已经存在时，`replace` 命令才会替换缓存中的键。如果缓存中不存在键，那么您将从 memcached 服务器接受到一条 **NOT_STORED** 响应。

下面是使用 `replace` 命令的标准交互：

 ```
replace accountId 0 0 5
67890
NOT_STORED

set accountId 0 0 5
67890
STORED

replace accountId 0 0 5
55555
STORED
 ```

最后两个基本命令是 `get` 和 `delete`。这些命令相当容易理解，并且使用了类似的语法，如下所示：

 `command <key>`

接下来看这些命令的应用。

## get 

`get `命令用于检索与之前添加的键值对相关的值。您将使用 `get `执行大多数检索操作。

下面是使用 get 命令的典型交互：

```
set userId 0 0 5
12345
STORED

get userId
VALUE userId 0 5
12345
END

get bob
END
```



如您所见，`get` 命令相当简单。您使用一个键来调用 `get`，如果这个键存在于缓存中，则返回相应的值。如果不存在，则不返回任何内容。

## delete

最后一个基本命令是 `delete`。`delete` 命令用于删除 memcached 中的任何现有值。您将使用一个键调用 `delete`，如果该键存在于缓存中，则删除该值。如果不存在，则返回一条 **NOT_FOUND** 消息。

下面是使用 `delete` 命令的客户机服务器交互：

```
set userId 0 0 5
98765
STORED

delete bob
NOT_FOUND

delete userId
DELETED

get userId
END
```



**高级 memcached 客户机命令**

可以在 memcached 中使用的两个高级命令是 `gets` 和 `cas`。`gets` 和 `cas` 命令需要结合使用。您将使用这两个命令来确保不会将现有的名称/值对设置为新值（如果该值已经更新过）。我们来分别看看这些命令。

## gets

`gets` 命令的功能类似于基本的 `get` 命令。两个命令之间的差异在于，`gets` 返回的信息稍微多一些：64 位的整型值非常像名称/值对的 “版本” 标识符。

下面是使用 `gets` 命令的客户机服务器交互：

```
set userId 0 0 5
12345
STORED

get userId
VALUE userId 0 5
12345
END

gets userId
VALUE userId 0 5 4
12345
END
```



考虑 `get` 和 `gets` 命令之间的差异。`gets` 命令将返回一个额外的值 — 在本例中是整型值 4，用于标识名称/值对。如果对此名称/值对执行另一个 `set` 命令，则 `gets`返回的额外值将会发生更改，以表明名称/值对已经被更新。清单 6 显示了一个例子：

##  cas (set 更新版本指示符 )

```
set userId 0 0 5
33333
STORED

gets userId
VALUE userId 0 5 5
33333
END
```



您看到 `gets` 返回的值了吗？它已经更新为 5。您每次修改名称/值对时，该值都会发生更改。

**cas** 
`cas`（check 和 set）是一个非常便捷的 memcached 命令，用于设置名称/值对的值（如果该名称/值对在您上次执行 `gets` 后没有更新过）。它使用与 `set` 命令相类似的语法，但包括一个额外的值：`gets` 返回的额外值。

注意以下使用 `cas` 命令的交互：

```
set userId 0 0 5
55555
STORED

gets userId
VALUE userId 0 5 6
55555
END

cas userId 0 0 5 6
33333
STORED
```



如您所见，我使用额外的整型值 6 来调用 `gets` 命令，并且操作运行非常顺序。现在，我们来看看清单 7 中的一系列命令：

**使用旧版本指示符的 cas 命令**

```
set userId 0 0 5
55555
STORED

gets userId
VALUE userId 0 5 8
55555
END

cas userId 0 0 5 6
33333
EXISTS
```



注意，我并未使用 `gets` 最近返回的整型值，并且 `cas` 命令返回 EXISTS 值以示失败。从本质上说，同时使用 `gets` 和 `cas` 命令可以防止您使用自上次读取后经过更新的名称/值对。

**缓存管理命令**

最后两个 memcached 命令用于监控和清理 memcached 实例。它们是 `stats` 和 `flush_all` 命令。

## stats

`stats` 命令的功能正如其名：转储所连接的 memcached 实例的当前统计数据。在下例中，执行 `stats` 命令显示了关于当前 memcached 实例的信息：

```
stats
STAT pid 63
STAT uptime 101758
STAT time 1248643186
STAT version 1.4.11
STAT pointer_size 32
STAT rusage_user 1.177192
STAT rusage_system 2.365370
STAT curr_items 2
STAT total_items 8
STAT bytes 119
STAT curr_connections 6
STAT total_connections 7
STAT connection_structures 7
STAT cmd_get 12
STAT cmd_set 12
STAT get_hits 12
STAT get_misses 0
STAT evictions 0
STAT bytes_read 471
STAT bytes_written 535
STAT limit_maxbytes 67108864
STAT threads 4
END
```



此处的大多数输出都非常容易理解。稍后在讨论缓存性能时，我还将详细解释这些值的含义。至于目前，我们先来看看输出，然后再使用新的键来运行一些 `set` 命令，并再次运行 `stats` 命令，注意发生了哪些变化。

## flush_all

`flush_all` 是最后一个要介绍的命令。这个最简单的命令仅用于清理缓存中的所有名称/值对。如果您需要将缓存重置到干净的状态，则 `flush_all` 能提供很大的用处。下面是一个使用 `flush_all` 的例子：

```
set userId 0 0 5
55555
STORED

get userId
VALUE userId 0 5
55555
END

flush_all
OK

get userId
END
```



## 缓存性能

在本文的最后，我将讨论如何使用高级 memcached 命令来确定缓存的性能。`stats` 命令用于调优缓存的使用。需要注意的两个最重要的统计数据是 et_hits 和 get_misses。这两个值分别指示找到名称/值对的次数（get_hits）和未找到名称/值对的次数（get_misses）。

结合这些值，我们可以确定缓存的利用率如何。初次启动缓存时，可以看到 get_misses 会自然地增加，但在经过一定的使用量之后，这些 get_misses 值应该会逐渐趋于平稳 — 这表示缓存主要用于常见的读取操作。如果您看到 get_misses 继续快速增加，而 get_hits 逐渐趋于平稳，则需要确定一下所缓存的内容是什么。您可能缓存了错误的内容。

确定缓存效率的另一种方法是查看缓存的命中率（hit ratio）。缓存命中率表示执行 `get` 的次数与错过 `get` 的次数的百分比。要确定这个百分比，需要再次运行 `stats` 命令，如清单 8 所示：

## 计算缓存命中率

```
stats
STAT pid 6825
STAT uptime 540692
STAT time 1249252262
STAT version 1.2.6
STAT pointer_size 32
STAT rusage_user 0.056003
STAT rusage_system 0.180011
STAT curr_items 595
STAT total_items 961
STAT bytes 4587415
STAT curr_connections 3
STAT total_connections 22
STAT connection_structures 4
STAT cmd_get 2688
STAT cmd_set 961
STAT get_hits 1908
STAT get_misses 780
STAT evictions 0
STAT bytes_read 5770762
STAT bytes_written 7421373
STAT limit_maxbytes 536870912
STAT threads 1
END
```



现在，用 get_hits 的数值除以 cmd_gets。在本例中，您的命中率大约是 71%。在理想情况下，您可能希望得到更高的百分比 — 比率越高越好。查看统计数据并不时测量它们可以很好地判定缓存策略的效率。

## 常用命令

启动/结束
`memcached -d -m 10 -u root -l 192.168.0.122 -p 11200 -c 256 -P /tmp/memcached.pid`
-d 选项是启动一个守护进程， 
-m 是分配给Memcache使用的内存数量，单位是MB，这里是10MB
-u 是运行Memcache的用户，这里是root
-l 是监听的服务器IP地址，如果有多个地址的话，这里指定了服务器的IP地址192.168.0.122 
-p 是设置Memcache监听的端口，这里设置了12000，最好是1024以上的端口
-c 选项是最大运行的并发连接数，默认是1024，这里设置了256，按照你服务器的负载量来设定
-P 是设置保存Memcache的pid文件
kill 'cat /tmp/memcached.pid'

获取运行状态
`echo stats | nc 192.168.1.123 11200`
`watch "echo stats | nc 192.168.1.123 11200" `(实时状态)



# spymemcached

## 添加依赖：

mvnrepository keywords: spymemcached

```xml
<!-- https://mvnrepository.com/artifact/net.spy/spymemcached -->
<dependency>
  <groupId>net.spy</groupId>
  <artifactId>spymemcached</artifactId>
  <version>2.12.0</version>
</dependency>
```

## 集成spring配置：

applicationContext-spymemcached.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd">

    <!--<bean id="propertyConfigurer1"-->
          <!--class="org.springframework.beans.factory.config.PropertyPlaceholderConfigurer">-->
        <!--<property name="locations">-->
            <!--<list>-->
                <!--<value>classpath:config/spymemcached.properties</value>-->
            <!--</list>-->
        <!--</property>-->
        <!--<property name="ignoreUnresolvablePlaceholders" value="true"/>-->
    <!--</bean>-->
  	<!-- 引入properties文件 -->
    <context:property-placeholder location="classpath:config/spymemcached.properties" ignore-unresolvable="true"/>

    <!-- SpyMemcached客户端-->
    <bean name="spyMemcachedClient" class="net.spy.memcached.spring.MemcachedClientFactoryBean">
        <property name="servers" value="${memcached.url}"/>
        <property name="protocol" value="TEXT"/><!-- BINARY -->
        <property name="locatorType" value="CONSISTENT"/>
        <property name="opTimeout" value="1000"/>

        <property name="transcoder">
            <bean class="net.spy.memcached.transcoders.SerializingTranscoder">
                <property name="compressionThreshold" value="1024"/>
            </bean>
        </property>

        <property name="timeoutExceptionThreshold" value="1998"/>
        <property name="hashAlg">
            <value type="net.spy.memcached.DefaultHashAlgorithm">KETAMA_HASH</value>
        </property>
        <property name="failureMode" value="Redistribute"/>
        <property name="useNagleAlgorithm" value="false"/>
    </bean>
</beans>
```

config/spymemcached.properties:

```properties
# server地址，这是我是用的centos虚拟机
memcached.url=192.168.128.130:11211 
```



## 属性详解：

```
Servers
一个字符串，包括由空格或逗号分隔的主机或IP地址与端口号
Daemon
设置IO线程的守护进程(默认为true)状态
FailureMode
设置故障模式(取消，重新分配，重试)，默认是重新分配
HashAlg
设置哈希算法(见net.spy.memcached.HashAlgorithm的值)
InitialObservers
设置初始连接的观察者(观察初始连接)
LocatorType
设置定位器类型(ARRAY_MOD,CONSISTENT),默认是ARRAY_MOD
MaxReconnectDelay
设置最大的连接延迟
OpFact
设置操作工厂
OpQueueFactory
设置操作队列工厂
OpTimeout
以毫秒为单位设置默认的操作超时时间
Protocol
指定要使用的协议(BINARY,TEXT),默认是TEXT
ReadBufferSize
设置读取的缓冲区大小
ReadOpQueueFactory
设置读队列工厂
ShouldOptimize
如果默认操作优化是不可取的，设置为false(默认为true)
Transcoder
设置默认的转码器(默认以net.spy.memcached.transcoders.SerializingTranscoder)
UseNagleAlgorithm
如果你想使用Nagle算法，设置为true
WriteOpQueueFactory
设置写队列工厂
AuthDescriptor
设置authDescriptor,在新的连接上使用身份验证
```



## 使用示例：

```java
public class App {

    private static ApplicationContext app ;
    private static MemcachedClient spyMemcachedClient ;

    public static void main(String[] args) {

        app = new ClassPathXmlApplicationContext("applicationContext-spymemcahed.xml" );
        spyMemcachedClient = (MemcachedClient) app.getBean( "spyMemcachedClient");

        spyMemcachedClient.set("aaa" , 36000, "hhh/www");
    }
}
```



## 方法参照：

// todo



# xmemcached

## 添加依赖：

mvnrepository keywords: xmemcached

```xml
<!-- https://mvnrepository.com/artifact/com.googlecode.xmemcached/xmemcached -->
<dependency>
    <groupId>com.googlecode.xmemcached</groupId>
    <artifactId>xmemcached</artifactId>
    <version>2.0.0</version>
</dependency>
```



## 集成spring配置：

这里不再使用properties了，可自行添加

```xml
<bean name="memcachedClient"
      class="net.rubyeye.xmemcached.utils.XMemcachedClientFactoryBean"
      destroy-method="shutdown" lazy-init="false">
  <property name="servers" value="127.0.0.1:11211" />
  <property name="commandFactory">
    <bean class="net.rubyeye.xmemcached.command.BinaryCommandFactory" />
  </property>
</bean>
```



  ## 使用示例：

```java
public class App2 {

    private static ApplicationContext app ;
    private static MemcachedClient memcachedClient;

    public static void main(String[] args) throws IOException, InterruptedException, MemcachedException, TimeoutException {
        app = new ClassPathXmlApplicationContext("applicationContext-xmemcached" );
      	// 直接强转为memcachedClient
        memcachedClient = (MemcachedClient) app.getBean("memcachedClient");
        memcachedClient.set("xmemcached", 0, "haha");
    }
}
```



## 不与spring集成：

```java
MemcachedClientBuilder builder = new XMemcachedClientBuilder(AddrUtil.getAddresses (“localhost:11211”));
MemcachedClient memcachedClient = builder.build();
```



## 方法参照：

// todo



