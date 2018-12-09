# Webservice

Webservice，也成为 Xml Webservice，是一个轻量级的web通信框架。

特点：
1. 基于web服务，服务端提供的接口，客户端可以访问（rmi基于socket）
2. 跨平台、跨语言的整合方案（rmi是一个纯java的解决方案，无法跨语言）

## 概念

WSDL(Webservice Definition Language) Webservice 定义语言：
Webservice 通过 WSDL 文件 声明对外提供的服务，并且定义了可用的方法、方法参数
1. WSDL基于XML语言定义
2. 定义了一个.wsdl文件类型
3. 定义了webservice服务端和客户端进行交互的请求和响应数据的格式和方式
4. 一个webservice定义一个唯一的WSDL文档

SOAP(simple object access protocol 简单对象访问协议)：
webservice 通过http协议发送的接收请求时，请求和响应报文都是使用xml格式进行封装，这些特定的Http消息头和xml内容格式就是SOAP协议
1. 一种简单，基于HTTP和XML(报文)的协议
2. soap消息：请求和响应消息

SEI(Webservice endpoint interface) webservice的终端接口：
webservice服务端用来处理请求的接口，也就是发不出去的接口

> Webservice 是一个协议，是一个标准，拥有标准可以让不同的系统达到完美融合的效果