- Calendar: 类是一个抽象类，它为特定瞬间与一组诸如 YEAR、MONTH、DAY_OF_MONTH、HOUR 等 日历字段之间的转换提供了一些方法，并为操作日历字段（例如获得下星期的日期）提供了一些方法。
- TimeZone: 表示时区偏移量。
- Locale： 表示了特定的地理、政治和文化地区。需要 Locale 来执行其任务的操作称为语言环境敏感的操作，它使用 Locale 为用户量身定制信息。
- SimpleDateFormat: 主要是用来格式化Date，用过之后就会发现，它其实不完善，对Calendar提供的支持很少.

经历几个项目发现apache提供的第三方扩展类库，org.apache.commons.lang.time包比较好用，可以将程序中时间处理变的简单一点，提高你的开发效率，下面介绍下常用的方法和具体使用。
org.apache.commons.lang.time 包括以下几个类：

1. DateFormatUtils        【格式化Calendar与Date并依赖于 FastDateFormat】
2. DateUtils                  【围绕Calendar与Date的实用方法】
3. DurationFormatUtils 【毫秒数格式化Calendar与Date】
4. FastDateFormat        【线程安全的SimpleDateFormat】
5. StopWatch               【提供一个方便的定时的API 】