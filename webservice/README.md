---
typora-root-url: ./
typora-copy-images-to: etc/img
---

# Webservice

Webservice，也成为 Xml Webservice，是一个轻量级的web通信框架。

**特点**：

1. 基于web服务，服务端提供的接口，客户端可以访问（rmi基于socket）
2. 跨平台、跨语言的整合方案（rmi是一个纯java的解决方案，无法跨语言）

> Webservice 是一个协议，是一个标准，拥有标准可以让不同的系统达到完美融合的效果

## 概念

### WSDL

Webservice Definition Language, Webservice 定义语言，即用于描述一个Webservice的语言。

一个Webservice对应一个WSDL文档。

Webservice 通过 WSDL 文件 声明对外提供的服务，并且定义了可用的方法、方法参数

1. WSDL基于XML语言定义
2. 定义了一个.wsdl文件类型
3. 定义了webservice服务端和客户端进行交互的请求和响应数据的格式和方式
4. 一个webservice定义一个唯一的WSDL文档

### SOAP 

simple object access protocol，即简单对象访问协议。
webservice 通过http协议发送的接收请求时，请求和响应报文都是使用xml格式进行封装，这些特定的Http消息头和xml内容格式就是SOAP协议。

```
SOAP = HTTP + XML
```

1. 一种简单，基于HTTP和XML(报文)的协议
2. soap消息：请求和响应消息

### SEI

Webservice endpoint interface，webservice的终端接口。
webservice服务端用来处理请求的接口，也就是Java接口。

### Port type

一个Webservice并不直接包含一组 operation 方法，方法是被组成一个或者多个 Port Types。一个Port Types类似一个Java类，每个 operation 类似java 类中静态方法。


## WSDL文件解读

### 根元素：

```xml
<!--每个WSDL的跟标签都是 definitions，并且无论webservice的方法是一个还是多个，都包含type、message、portType、binding、server 五个部分 -->
<definitions
    xmlns:wsu="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd" 
    xmlns:wsp="http://www.w3.org/ns/ws-policy" 
    xmlns:wsp1_2="http://schemas.xmlsoap.org/ws/2004/09/policy" 
    xmlns:wsam="http://www.w3.org/2007/05/addressing/metadata" 
    xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" 
    xmlns:tns="http://ws/" 
    xmlns:xsd="http://www.w3.org/2001/XMLSchema" 
    xmlns="http://schemas.xmlsoap.org/wsdl/" 
    targetNamespace="http://ws/" 
    name="SayHelloImplService">
    
    <types>...</types>
    <message>...</message>
    <portType>...</portType>
    <binding>...</binding>
    <service>...</service>
    <!-- 共包含五个部分 -->
</definitions>
```

- type 用于定义webservice方法中**请求参数和响应参数的数据类型**
- message 用于定义通讯的**数据结构**
- portType 定义webservice的**SEI**
- binding  描述特定服务接口的协议、数据格式、安全性和其它属性
- service 定义一个webservice容器



关系如下：





### types标签

```xml
<types>
    <xsd:schema>
        <xsd:import namespace="http://ws/" schemaLocation="http://localhost:8888/ws/hello?xsd=1"/>
    </xsd:schema>
</types>
```

types定义此webservice中请求参数以及响应数据的数据类型，为了跨平台，所以使用schema来描述：
```xml
<xs:schema xmlns:tns="http://ws/" xmlns:xs="http://www.w3.org/2001/XMLSchema" version="1.0" targetNamespace="http://ws/">
    <xs:element name="sayHello" type="tns:sayHello"/>
    <xs:element name="sayHelloResponse" type="tns:sayHelloResponse"/>
    <xs:complexType name="sayHello">
        <xs:sequence>
            <xs:element name="arg0" type="xs:string" minOccurs="0"/>
        </xs:sequence>
    </xs:complexType>
    <xs:complexType name="sayHelloResponse">
        <xs:sequence>
            <xs:element name="return" type="xs:string" minOccurs="0"/>
        </xs:sequence>
    </xs:complexType>
</xs:schema>

<!-- 对于上面个的schema，可以得到的xml格式为：-->

<sayHello>
    <arg0>string</arg0> // 请求参数1的类型为string
</sayHello>
<sayHelloResponse>
    <return>string<return> // 响应的类型为string
</sayHelloResponse>
```

### message 标签
定义了通讯中消息的数据结构：
```xml
<message name="sayHello">
    <part name="parameters" element="tns:sayHello"/>
</message>
<message name="sayHelloResponse">
    <part name="parameters" element="tns:sayHelloResponse"/>
</message>
```
> 注意有可能只有请求或者只有响应

### portType 标签
定义服务器端的SEI（使用xml描述服务端的接口）：
```xml
<!--类名-->
<portType name="SayHelloImpl">
    <!--方法名-->
    <operation name="sayHello">
        <!--入参定义，指向message，由message标签定义-->
        <input wsam:Action="http://ws/SayHelloImpl/sayHelloRequest" message="tns:sayHello"/>
        <!--返回定义，指向message，由message标签定义-->
        <output wsam:Action="http://ws/SayHelloImpl/sayHelloResponse" message="tns:sayHelloResponse"/>
    </operation>
</portType>
```

### binding 标签



### service元素

```xml
<!-- 
	service 用于定义一个webservice容器 
	属性name：指定服务器处理请求的入口，即SEI的实现
	
-->
<service name="SayHelloImplService">
    <port name="SayHelloImplPort" binding="tns:SayHelloImplPortBinding">
    	<soap:address location="http://localhost:8888/ws/hello"/>
    </port>
</service>
```

