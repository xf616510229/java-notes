# Message Queue 环境搭建


[docker-activemq](https://hub.docker.com/r/rmohr/activemq)


```
docker run -p 61616:61616 -p 8161:8161 \
           -v /your/persistent/dir/conf:/opt/activemq/conf \
           -v /your/persistent/dir/data:/opt/activemq/data \
           rmohr/activemq        

# 默认控制台用户名密码皆是admin
```

