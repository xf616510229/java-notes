# 前言

@author:[Feathers](http://feathers.me)

@email:616510229@qq.com

使用java生成Excel表，或者读取Excel表



# Excel表名词解释

- 工作簿 workbook

- 工作表 sheet

- 单元格 cell

  ​

# 开源框架

目前可以完成Excel相关操作的Java框架主要有韩国的**jExcelAPI**(即jxl)以及Apache的**POI**框架



JXL支持比较低版本的excel，比如Excel 95 ,97 ,2000，2003 

由于Excel版本比较低，导致最大行有限制，无法导出65535以上量级的数据 
对于内存，和时间的花费也比POI基于内存+磁盘的方式高。

JXL技术说明 

1. 读取Excel公式（可以读取Excel 97以后的公式） 
2. 生成Excel数据表（格式为Excel 97） 

3. 支持字体、数字、日期的格式化 
4. 支持单元格的阴影操作，以及颜色操作 


5. 修改已经存在的数据表 
6. 是最基础的excel api 


7. 小文件读取效率比较高 
8. 跨平台 



POI技术说明

1. 能保持Excel里原有的宏（但不能用它写新的宏）。
2. 不支持跨平台（主要就是Java语言）
3. 在一些业务场景中代码相对复杂，但是API丰富，支持多种模式的读写。
4. 支持比较新版本的excel.
5. 读写的时候比较占内存。
6. 读写的时候比较占内存。
7. 支持大数量大文件的读写操作。但是需要熟悉API。

总体来说，对于简单的单表excel导入导出的需求，建议使用JXL。数据量稍微小点，占用内存少，速度快。 
对于报表类的，涉及月份数据量，多表数据聚合在一起建议使用POI。





# 使用JXL

## 添加依赖

maven repository 搜索关键词 JXL

```xml
<dependency>
    <groupId>net.sourceforge.jexcelapi</groupId>
    <artifactId>jxl</artifactId>
    <version>2.6.12</version>
</dependency>
```



## 简述

一个excel就对应一个Workbook对象，（Wookbook中含有许多静态方法，所以他也是一个工具类）
一个Workbook可以有多个Sheet对象
一个Sheet对象可以有多个Cell对象



## 	读取Excel

通过Workbook，Sheet ，Cell这三个对象我们就可以实现Excel文件的读取工作。我们先想想一下读取步骤，不管是什么样的Excel操作框架必定都要经历

1.  选取Excel文件得到工作薄
2.  选择工作表
3. 选择Cell
4. 读取信息

### 读取工作簿

```java
// 1. 使用工具类Workbook工具类，传入目标文件，获取工作簿
Workbook workbook = Workbook.getWorkbook(new File("D:\\exceldemo\\", "test.xls"));
// 2. 使用文件流读取，然后交给Workbook工具类处理
InputStream in = new BufferedInputStream(new FileInputStream(file));
Workbook workbook1 = Workbook.getWorkbook(in);
// 3. 还有两种构造可以传入WorkbookSettings, 比如setEncode可以设置工作簿编码
Workbook.getWorkbook(file, ws);
Workbook.getWorkbook(in, ws);
```

### 读取工作表

```java
// 获取工作簿下的所有工作表
Sheet[] sheets = workbook.getSheets();
// 获取第一个工作表
Sheet sheet = workbook.getSheet(0);
// 获取名称为工作表一的工作表
Sheet 工作表1 = workbook.getSheet("工作表1");
```

### 读取单元格

```java
// 获取3行2列单元格
Cell cell = sheet.getCell(2, 1);

// 获取单元格的值
String contents = cell.getContents();
// 获取单元格的类型 
CellType type = cell.getType();
```

**单元格类型：**

```
EMPTY 空
LABEL 字符
NUMBER 数字
BOOLEAN 布尔
ERROR 错误
NUMBER_FORMULA 数值公式
DATE_FORMULA 日期公式
STRING_FORMULA 线性公式
BOOLEAN_FORMULA 布尔表达式
FORMULA_ERROR 公式错误
DATE 日期

除此之外，还提供了Cell的子类，LabelCell，NumberCell，DateCell，分别表示字符单元格，数值单元格和日期单元格
```

###  释放资源

```java
 workbook.close();
```

当你完成对Excel电子表格数据的处理后，一定要使用close()方法来关闭先前创建的对象，以释放读取数据表的过程中所占用的内存空间，在读取大量数据时显得尤为重要。



## 写入Excel

通过WritableWorkbook，WritableSheet，Label这三个对象我们就可以实现Excel文件的插入工作。我们先想想一下插入，不管是什么样的Excel操作框架必定都要经历

1. 创建Exce工作薄
2. 创建工作表
3. 创建单元格

### 创建工作簿

API提供了两种方式来处理可写入的输出流，

- 一直接生成本地文件，如果文件名不带全路径的话，缺省的文件会定位在当前目录，如果文件名带有全路径的话，则生成的Excel文件则会定位在相应的目录；
- 将Excel对象直接写入到输出流，例如：用户通过浏览器来访问web服务器，如果HTTP头设置正确的话，浏览器自动调用客户端的Excel应用程序，来显示动态生成的Excel电子表格。

**创建可以写入的Excel工作簿**：

```java
// 1. 写入到本地文件中
WritableWorkbook workbook = Workbook.createWorkbook(new File("D:\\exceldemo\\", "test.xls"));
// 写入到本地文件中，加上workSettings
Workbook.createWorkbook(new File("D:\\exceldemo\\", "test.xls"), ws);

// 2. 写入到输出流中，web环境下使用
OutputStream outputStream = new FileOutputStream(new File("D:\\exceldemo\\", "test.xls"));
WritableWorkbook workbook3 = Workbook.createWorkbook(outputStream, ws);
```

### 创建工作表

```java
 WritableSheet 工作表1 = workbook3.createSheet("工作表1", 0);
```

### 创建单元格

```java
// 1. 创建字符单元格对象
Label labelC = new Label(0, 0, "This is a Label cell");
工作表1.addCell(labelC);

// 2. 创建有格式字符单元格对象
// 定义字体
WritableFont font = new WritableFont(
  	WritableFont.ARIAL,  // 字体Arial
  	10,WritableFont.NO_BOLD, // 不加粗
  	false, 
  	UnderlineStyle.NO_UNDERLINE, // 无下划线
  	jxl.format.Colour.RED); // 字体颜色红色
// 使用改字体定义一个单元格格式化对象
WritableCellFormat wcfF = new WritableCellFormat(font);
// 使用格式化对象定义一个单元格
Label labelCF = new Label(1, 0, "This is a formatting cell", wcfF);
工作表1.addCell(labelCF);


// 3. 创建数字类型单元格对象
jxl.write.Number labelN = new jxl.write.Number(0, 1, 3.1415926);
工作表1.addCell(labelN);

// 4. 创建有格式数值单元格对象
NumberFormat nf = new NumberFormat("#.##"); // 保留两位小数
WritableCellFormat wcfN = new WritableCellFormat(nf);
jxl.write.Number labelNF = new jxl.write.Number(1, 1, 3.1415926, wcfN);
工作表1.addCell(labelNF);

// 5. 添加boolen对象
jxl.write.Boolean labelB = new jxl.write.Boolean(0, 2, false);
工作表1.addCell(labelB);

// 6. 添加DateTime单元格对象，日期时间类型
DateTime labelDT = new DateTime(0, 3, new java.util.Date());
工作表1.addCell(labelDT);

// 7. 添加格式化的DateTime对象
DateFormat df = new DateFormat("dd MM yyyy hh:mm:ss");
WritableCellFormat wcfDF = new WritableCellFormat(df);
DateTime labelDTF = new DateTime(1, 3, new Date(), wcfDF);
工作表1.addCell(labelDTF);

// 8. 添加图像（只支持png）  10 10 代表 width 和 height
WritableImage wrimage=new WritableImage(1,4,10,10,new File(imageFilepath));
工作表1.addImage(wrimage);
```

合并单元格：

```java
// 合并1行6列 到 4行8列 之间的所有单元格，按照点来计算的 
工作表1.mergeCells(0, 6, 3, 8);
label = new Label(0, 6, "合并了12个单元格");
工作表1.addCell(label);
```

添加单元格样式：

```java
WritableCellFormat wc = new WritableCellFormat();
wc.setAlignment(Alignment.CENTRE); // 设置居中
wc.setBorder(Border.ALL, BorderLineStyle.THIN); // 设置边框线
wc.setBackground(jxl.format.Colour.RED); // 设置单元格的背景颜色
Label label = new Label(1, 5, "字体", wc);
工作表1.addCell(label);
```

### 写入释放

```
workbook.write();
workbook.close();
```



## 其他常用API

Workbook类提供的方法

- `int getNumberOfSheets()` 获取工作表的总个数
- `Sheet[] getSheets()` 获取数组型的工作表
- `Sheet getSheet(String name);` //得到此对应名称的工作表

Sheet接口提供的方法

- `String getName()` 获取工作表的名称
- `int getColumns()` 获取Sheet表中所包含的总列数
- `Cell[] getColumn(int column)` 获取某一列的所有单元格，返回的是单元格对象数组
- `int getRows()` 获取Sheet表中所包含的总行数
- `Cell[] getRow(int row)` 获取某一行的所有单元格，返回的是单元格对象数组
- `Cell getCell(int column, int row)`获取指定单元格的对象引用，需要注意的是它的两个参数，第一个是列数，第二个是行数，这与通常的行、列组合有些不同
- `WritableSheet.setRowView(int i,int height);`指定第i+1行的高度
- `WritableSheet.setColumnView(int i,int width);` 指定第i+1列的宽度



# 使用POI

## 添加依赖

maven repository 搜索关键词 POI

```xml
<!-- https://mvnrepository.com/artifact/org.apache.poi/poi -->
<dependency>
    <groupId>org.apache.poi</groupId>
    <artifactId>poi</artifactId>
    <version>3.15</version>
</dependency>
```

## 简述

