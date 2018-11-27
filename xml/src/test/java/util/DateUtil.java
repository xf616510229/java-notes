package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * 本类用来演示:日期工具类.
 *
 * @author success
 *
 * 2017年4月5日下午4:45:04
 */
public class DateUtil {
	/**
	 * 判断某年是否为闰年
	 */
	public static boolean isLeapYear(int year){
		return (year%4==0&&year%100!=0)||year%400==0;
	}

	/**
	 * 判断某年某月最大天数
	 */
	public static int getMaxDays(int year,int month){
		int days = -1;
		switch (month) {
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				days = 31;
				break;
			case 2:
				days = isLeapYear(year)?29:28;
				break;
			default:
				days = 30;
				break;
		}
		return days;
	}

	/**
	 * 获取某年某月的某号是周几.
	 */
	public static int getDayOfWeek(int year,int month,int date){
		Calendar cal = Calendar.getInstance();
		cal.set(year, month-1, date);
		return cal.get(Calendar.DAY_OF_WEEK)-1;
	}

	/**
	 * 根据指定的年月日来构建一个Date对象
	 * new Date(int year,int month,int date)->过时的
	 */
	public static Date createDate(int year,int month,int date){
		//1.构建一个Calendar对象
		Calendar calendar = Calendar.getInstance();
		//2.重新对日历中的字段进行赋值.

		calendar.set(year, month-1, date);

		return calendar.getTime();
	}

	/**
	 * 根据传入的指定的模板来格式化日期对象
	 * 将日期对象转换成对应模板形式的字符串表现形式.
	 */
	public static String format(Date d,String pattern){
		//1.构建一个java.text.SimpleDateFormat对象
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

		String sdate = null;

		if(null!=d && null!=pattern){
			sdate = simpleDateFormat.format(d);
		}
		return sdate;
	}

	/**
	 * 将字符串转换成日期Date类型
	 * @param pattern
	 */
	public static Date parse(String pattern,String sdate){
		//1.构建一个java.text.SimpleDateFormat对象
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		Date d = null;

		if(null!=sdate && null!=pattern){
			try {
				d = simpleDateFormat.parse(sdate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		return d;
	}
}
