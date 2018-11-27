# 什么是Redis

REmote DIctionary Server(Redis)是一个完全开源免费的key-value存储系统。他是一个使用ANSI C语言编写、遵守BSD协议，支持网络、可基于内存亦可持久化的日志型、Key-Value数据库，并提供多种语言的API。

它通常被称为数据结构服务器，因为值（value）可以是 字符串(String), 哈希(Map), 列表(list), 集合(sets) 和 有序集合(sorted sets)等类型。



# 特点

- redis具有持久化机制，可以定期将内存中的数据持久化到硬盘上。
- Redis不仅仅支持简单的key-value类型的数据，同时还提供list，set，zset，hash等数据结构的存储。
- redis具备binlog功能，可以将所有操作写入日志，当redis出现故障，可依照binlog进行数据恢复。
- redis支持virtual memory，可以限定内存使用大小，当数据超过阈值，则通过类似LRU的算法把内存中的最不常用数据保存到硬盘的页面文件中。
- redis原生支持的数据类型更多，使用的想象空间更大。

# 优势

- 性能极高 – Redis能读的速度是110000次/s,写的速度是81000次/s 。
- 丰富的数据类型 – Redis支持二进制案例的 Strings, Lists, Hashes, Sets 及 Ordered Sets 数据类型操作。
- 原子 – Redis的所有操作都是原子性的，同时Redis还支持对几个操作全并后的原子性执行。
- 丰富的特性 – Redis还支持 publish/subscribe, 通知, key 过期等等特性。

> 原子性：
>
> 事务，同时成功同时失败的特性称作原子性。



# BSD协议

BSD开源协议是一个给于使用者很大自由的协议。可以自由的使用，修改源代码，也可以将修改后的代码作为开源或者专有软件再发布。当你发布使用了BSD协议的代码，或者以BSD协议代码为基础做二次开发自己的产品时，需要满足三个条件：

- 如果再发布的产品中包含源代码，则在源代码中必须带有原来代码中的BSD协议。
- 如果再发布的只是二进制类库/软件，则需要在类库/软件的文档和版权声明中包含原来代码中的BSD协议。
- 不可以用开源代码的作者/机构名字和原来产品的名字做市场推广。

BSD代码鼓励代码共享，但需要尊重代码作者的著作权。BSD由于允许使用者修改和重新发布代码，也允许使用或在BSD代码上开发商业软件发布和销 售，因此是对商业集成很友好的协议。

很多的公司企业在选用开源产品的时候都首选BSD协议，因为可以完全控制这些第三方的代码，在必要的时候可以修改或者 二次开发。



# 安装/运行

[https://github.com/MSOpenTech/redis/releases](https://github.com/MSOpenTech/redis/releases)

在命令行执行redis-server [配置文件]即可开启redis服务器

再打开一个窗口，使用`redis-cli.exe -h 127.0.0.1 -p 6379`即可连接redis数据库

# 配置

```
# 获取指定配置
127.0.0.1:6379> CONFIG GET CONFIG_SETTING_NAME
```

```
# 获取所有配置
127.0.0.1:6379> CONFIG GET * 
  1) "dbfilename"
  2) "dump.rdb"
  3) "requirepass"
  4) ""
  5) "masterauth"
  6) ""
  7) "unixsocket"
  8) ""
  9) "logfile"
 10) ""
 11) "pidfile"
 12) ""
 13) "maxmemory"
 14) "0"
 15) "maxmemory-samples"
 16) "5"
 17) "timeout"
 18) "0"
 19) "auto-aof-rewrite-percentage"
 20) "100"
 21) "auto-aof-rewrite-min-size"
 22) "67108864"
 23) "hash-max-ziplist-entries"
 24) "512"
 25) "hash-max-ziplist-value"
 26) "64"
 27) "list-max-ziplist-size"
 28) "-2"
 29) "list-compress-depth"
 30) "0"
 31) "set-max-intset-entries"
 32) "512"
 33) "zset-max-ziplist-entries"
 34) "128"
 35) "zset-max-ziplist-value"
 36) "64"
 37) "hll-sparse-max-bytes"
 38) "3000"
 39) "lua-time-limit"
 40) "5000"
 41) "slowlog-log-slower-than"
 42) "10000"
 43) "latency-monitor-threshold"
 44) "0"
 45) "slowlog-max-len"
 46) "128"
 47) "port"
 48) "6379"
 49) "tcp-backlog"
 50) "511"
 51) "databases"
 52) "16"
 53) "repl-ping-slave-period"
 54) "10"
 55) "repl-timeout"
 56) "60"
 57) "repl-backlog-size"
 58) "1048576"
 59) "repl-backlog-ttl"
 60) "3600"
 61) "maxclients"
 62) "10000"
 63) "watchdog-period"
 64) "0"
 65) "slave-priority"
 66) "100"
 67) "min-slaves-to-write"
 68) "0"
 69) "min-slaves-max-lag"
 70) "10"
 71) "hz"
 72) "10"
 73) "cluster-node-timeout"
 74) "15000"
 75) "cluster-migration-barrier"
 76) "1"
 77) "cluster-slave-validity-factor"
 78) "10"
 79) "repl-diskless-sync-delay"
 80) "5"
 81) "tcp-keepalive"
 82) "0"
 83) "cluster-require-full-coverage"
 84) "yes"
 85) "no-appendfsync-on-rewrite"
 86) "no"
 87) "slave-serve-stale-data"
 88) "yes"
 89) "slave-read-only"
 90) "yes"
 91) "stop-writes-on-bgsave-error"
 92) "yes"
 93) "daemonize"
 94) "no"
 95) "rdbcompression"
 96) "yes"
 97) "rdbchecksum"
 98) "yes"
 99) "activerehashing"
100) "yes"
101) "protected-mode"
102) "yes"
103) "repl-disable-tcp-nodelay"
104) "no"
105) "repl-diskless-sync"
106) "no"
107) "aof-rewrite-incremental-fsync"
108) "yes"
109) "aof-load-truncated"
110) "yes"
111) "maxmemory-policy"
112) "noeviction"
113) "loglevel"
114) "notice"
115) "supervised"
116) "no"
117) "appendfsync"
118) "everysec"
119) "appendonly"
120) "no"
121) "dir"
122) "O:\\Development\\Redis-x64-3.2.100"
123) "save"
124) "jd 900 jd 300 jd 60"
125) "client-output-buffer-limit"
126) "normal 0 0 0 slave 268435456 67108864 60 pubsub 33554432 8388608 60"
127) "unixsocketperm"
128) "0"
129) "slaveof"
130) ""
131) "notify-keyspace-events"
132) ""
133) "bind"
134) "127.0.0.1"
```

```
# 修改指定配置
127.0.0.1:6379> CONFIG SET CONFIG_SETTING_NAME NEW_CONFIG_VALUE
```

```
# 实例
127.0.0.1:6379> CONFIG SET loglevel "notice"
OK
 CONFIG GET loglevel
1) "loglevel"
2) "notice"
```

```
redis.conf 配置项说明如下：
1. Redis默认不是以守护进程的方式运行，可以通过该配置项修改，使用yes启用守护进程
    daemonize no
2. 当Redis以守护进程方式运行时，Redis默认会把pid写入/var/run/redis.pid文件，可以通过pidfile指定
    pidfile /var/run/redis.pid
3. 指定Redis监听端口，默认端口为6379，作者在自己的一篇博文中解释了为什么选用6379作为默认端口，因为6379在手机按键上MERZ对应的号码，而MERZ取自意大利歌女Alessia Merz的名字
    port 6379
4. 绑定的主机地址
    bind 127.0.0.1
5.当 客户端闲置多长时间后关闭连接，如果指定为0，表示关闭该功能
    timeout 300
6. 指定日志记录级别，Redis总共支持四个级别：debug、verbose、notice、warning，默认为verbose
    loglevel verbose
7. 日志记录方式，默认为标准输出，如果配置Redis为守护进程方式运行，而这里又配置为日志记录方式为标准输出，则日志将会发送给/dev/null
    logfile stdout
8. 设置数据库的数量，默认数据库为0，可以使用SELECT <dbid>命令在连接上指定数据库id
    databases 16
9. 指定在多长时间内，有多少次更新操作，就将数据同步到数据文件，可以多个条件配合
    save <seconds> <changes>
    Redis默认配置文件中提供了三个条件：
    save 900 1
    save 300 10
    save 60 10000
    分别表示900秒（15分钟）内有1个更改，300秒（5分钟）内有10个更改以及60秒内有10000个更改。
 
10. 指定存储至本地数据库时是否压缩数据，默认为yes，Redis采用LZF压缩，如果为了节省CPU时间，可以关闭该选项，但会导致数据库文件变的巨大
    rdbcompression yes
11. 指定本地数据库文件名，默认值为dump.rdb
    dbfilename dump.rdb
12. 指定本地数据库存放目录
    dir ./
13. 设置当本机为slav服务时，设置master服务的IP地址及端口，在Redis启动时，它会自动从master进行数据同步
    slaveof <masterip> <masterport>
14. 当master服务设置了密码保护时，slav服务连接master的密码
    masterauth <master-password>
15. 设置Redis连接密码，如果配置了连接密码，客户端在连接Redis时需要通过AUTH <password>命令提供密码，默认关闭
    requirepass foobared
16. 设置同一时间最大客户端连接数，默认无限制，Redis可以同时打开的客户端连接数为Redis进程可以打开的最大文件描述符数，如果设置 maxclients 0，表示不作限制。当客户端连接数到达限制时，Redis会关闭新的连接并向客户端返回max number of clients reached错误信息
    maxclients 128
17. 指定Redis最大内存限制，Redis在启动时会把数据加载到内存中，达到最大内存后，Redis会先尝试清除已到期或即将到期的Key，当此方法处理 后，仍然到达最大内存设置，将无法再进行写入操作，但仍然可以进行读取操作。Redis新的vm机制，会把Key存放内存，Value会存放在swap区
    maxmemory <bytes>
18. 指定是否在每次更新操作后进行日志记录，Redis在默认情况下是异步的把数据写入磁盘，如果不开启，可能会在断电时导致一段时间内的数据丢失。因为 redis本身同步数据文件是按上面save条件来同步的，所以有的数据会在一段时间内只存在于内存中。默认为no
    appendonly no
19. 指定更新日志文件名，默认为appendonly.aof
     appendfilename appendonly.aof
20. 指定更新日志条件，共有3个可选值： 
    no：表示等操作系统进行数据缓存同步到磁盘（快） 
    always：表示每次更新操作后手动调用fsync()将数据写到磁盘（慢，安全） 
    everysec：表示每秒同步一次（折衷，默认值）
    appendfsync everysec
 
21. 指定是否启用虚拟内存机制，默认值为no，简单的介绍一下，VM机制将数据分页存放，由Redis将访问量较少的页即冷数据swap到磁盘上，访问多的页面由磁盘自动换出到内存中（在后面的文章我会仔细分析Redis的VM机制）
     vm-enabled no
22. 虚拟内存文件路径，默认值为/tmp/redis.swap，不可多个Redis实例共享
     vm-swap-file /tmp/redis.swap
23. 将所有大于vm-max-memory的数据存入虚拟内存,无论vm-max-memory设置多小,所有索引数据都是内存存储的(Redis的索引数据 就是keys),也就是说,当vm-max-memory设置为0的时候,其实是所有value都存在于磁盘。默认值为0
     vm-max-memory 0
24. Redis swap文件分成了很多的page，一个对象可以保存在多个page上面，但一个page上不能被多个对象共享，vm-page-size是要根据存储的 数据大小来设定的，作者建议如果存储很多小对象，page大小最好设置为32或者64bytes；如果存储很大大对象，则可以使用更大的page，如果不 确定，就使用默认值
     vm-page-size 32
25. 设置swap文件中的page数量，由于页表（一种表示页面空闲或使用的bitmap）是在放在内存中的，，在磁盘上每8个pages将消耗1byte的内存。
     vm-pages 134217728
26. 设置访问swap文件的线程数,最好不要超过机器的核数,如果设置为0,那么所有对swap文件的操作都是串行的，可能会造成比较长时间的延迟。默认值为4
     vm-max-threads 4
27. 设置在向客户端应答时，是否把较小的包合并为一个包发送，默认为开启
    glueoutputbuf yes
28. 指定在超过一定的数量或者最大的元素超过某一临界值时，采用一种特殊的哈希算法
    hash-max-zipmap-entries 64
    hash-max-zipmap-value 512
29. 指定是否激活重置哈希，默认为开启（后面在介绍Redis的哈希算法时具体介绍）
    activerehashing yes
30. 指定包含其它的配置文件，可以在同一主机上多个Redis实例之间使用同一份配置文件，而同时各个实例又拥有自己的特定配置文件
    include /path/to/local.conf
```

# 数据类型

Redis五大数据类型：

- String
- Hash
- List
- Set
- zset

## String

string是redis最基本的类型，一个key对应一个value。

string类型是二进制安全的。意思是redis的string可以包含任何数据。比如jpg图片或者序列化的对象 。

string类型是Redis最基本的数据类型，一个键最大能存储512MB。

```
127.0.0.1:6379> set name "feathers"
OK
127.0.0.1:6379> get name
"feathers"
```

## Hash

Redis hash 是一个键名对集合。

Redis hash是一个string类型的field和value的映射表，hash特别**适合用于存储对象**。

```
hmset key field value [field value ...]
```

```
# 存入一个key为user:1的user对象
127.0.0.1:6379> hmset user:1 username feathers password feathers points 200
OK
127.0.0.1:6379> hgetall user:1
1) "username"
2) "feathers"
3) "password"
4) "feathers"
5) "points"
6) "200"
```

## List

列表，Redis 列表是简单的字符串列表，按照插入顺序排序。你可以添加一个元素到列表的头部（左边）或者尾部（右边）。

```
# 定义一个key为a的list列表，向里面push元素，并输出
127.0.0.1:6379> lpush a 1
(integer) 1
127.0.0.1:6379> lpush a 5
(integer) 2
127.0.0.1:6379> lpush a 2
(integer) 3
127.0.0.1:6379> lrange a
(error) ERR wrong number of arguments for 'lrange' command
127.0.0.1:6379> lrange a 0 10
1) "2"
2) "5"
3) "1"
```

## Set

集合，Redis的Set是string类型的无序集合。集合是通过哈希表实现的，所以添加，删除，查找的复杂度都是O(1)。

```
sadd key member [member ...]
```

```
127.0.0.1:6379> sadd c redis mongdb memecached
(integer) 3
127.0.0.1:6379> smembers c
1) "redis"
2) "memecached"
3) "mongdb"
127.0.0.1:6379>
```

## zset

有序集合，sorted set，注意，zset 和 set 一样也是string类型元素的集合,且不允许重复的成员。

```
zadd key [NX|XX] [CH] [INCR] score member [score member ...]
```

```
# 实例
127.0.0.1:6379> zadd d 0 redis
(integer) 1
127.0.0.1:6379> zadd d 0 mongodb
(integer) 1
127.0.0.1:6379> zadd d 0 memcached
(integer) 1
127.0.0.1:6379> zrangebyscore d 0 1000
1) "memcached"
2) "mongodb"
3) "redis"
```

# Redis常用操作

## 连接

```
redis-cli -h host -p port -a "password"
```

## key相关

存储一个key为name值value为Feathers的值

```r
redis 127.0.0.1:6379> set name Feathers
OK
```

获取一个值

```
redis 127.0.0.1:6379> get name
"Feathers"
```

序列化某一个值

```
127.0.0.1:6379> dump name
"\x00\bFeathers\a\x00r\xbd\xf0\xd9|\xb6/\x86"
```

删除一个值

```
redis 127.0.0.1:6379> del name
(integer) 1
```

判断是否存在该key

```
redis 127.0.0.1:6379> exists name
(integer) 0
```

设置key的有效期为100秒

```
127.0.0.1:6379> expire name 100
(integer) 1
```

设置key的有效期截止到Unix时间戳

```
redis 127.0.0.1:6379> EXPIREAT w3ckey 1293840000
(integer) 1
```

设置key的有效期为1000毫秒

```
127.0.0.1:6379> pexpire name 1000
(integer) 1
```

查看key的有效期

```
127.0.0.1:6379[1]> ttl name   /  pttl  name
(integer) -1  // key存在，没有设置剩余时间
(integer) -2  // key不存在，或者已经过期
```

移除key的过期时间

```
127.0.0.1:6379[1]> set name feathers   // 设置一个key为name的值
OK
127.0.0.1:6379[1]> expire name 100 // 设置过期时间 100 s
(integer) 1
127.0.0.1:6379[1]> ttl name  // 查看name的剩余时间 84s
(integer) 84
127.0.0.1:6379[1]> persist name  // 移除key的过期时间
(integer) 1
127.0.0.1:6379[1]> ttl name  // 查看key的剩余时间 -1代表永久有效
(integer) -1
127.0.0.1:6379[1]> ttl nannnn  // 查看一个不存在的key  -2 代表已过期
(integer) -2
```

查找所有给定给定模式pattern的key

```
127.0.0.1:6379> set book_name APPDevolp
OK
127.0.0.1:6379> set book_price 12.5
OK
127.0.0.1:6379> set book_category study
OK
127.0.0.1:6379> keys book_*
1) "book_category"
2) "book_name"
3) "book_price"
```

将当前数据库的 key 移动到给定的数据库 db 当中

```
127.0.0.1:6379> select 0   // 默认使用数据库0
OK
127.0.0.1:6379> set name feathers // 添加一个key为name的数据
OK
127.0.0.1:6379> move name 1  // 将数据移动到数据库1
(integer) 1
127.0.0.1:6379> exists name  // 数据库0种无name了
(integer) 0
127.0.0.1:6379> select 1  // 切换到数据库1
OK
127.0.0.1:6379[1]> exists name  // name被移动到数据库1中了
(integer) 1
```

从当前数据库中随机返回一个key

```
127.0.0.1:6379[1]> randomkey
"name"
```

修改key的名称

```
127.0.0.1:6379[1]> rename name lastname
OK
127.0.0.1:6379[1]> get name
(nil)
127.0.0.1:6379[1]> get lastname
"feathers"
```

修改key的名称，新的名称如果存在，则失败

```
127.0.0.1:6379[1]> set name feathers
OK
127.0.0.1:6379[1]> set lastname sx
OK
127.0.0.1:6379[1]> renamenx name lastname   // 修改失败
(integer) 0
127.0.0.1:6379[1]> get name
"feathers"
127.0.0.1:6379[1]> get lastname
"sx"
```

查看key对应的value的类型

```
127.0.0.1:6379[1]> set testType 120.8
OK
127.0.0.1:6379[1]> type testType
string
```



## String相关

添加一个值为字符串类型的记录

```
127.0.0.1:6379[1]> set name feathers
OK
127.0.0.1:6379[1]> type name
string
127.0.0.1:6379[1]> set age "23"
OK
127.0.0.1:6379[1]> type age
string
```

获取字符串,字符串会使用双引号包裹

```
127.0.0.1:6379[1]> get name
"feathers"
127.0.0.1:6379[1]> get age
"23"
```

返回字符串的子串

```
127.0.0.1:6379[1]> getrange name 0 2
"fea"
```

重新设置value的值，并返回旧值

```
127.0.0.1:6379[1]> getset name lisi
"feathers"
```

