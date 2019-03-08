package com.edu.converter;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * xxd 
 * 参数绑定 字符串日期转换器 如果不使用， 接收日期类型的数据类型， 报异常
 */
public class DateConverter implements Converter<String, Date> {

	public Date convert(String s) {
		// 实现 将日期串转成日期类型(格式是yyyy-MM-dd)
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

		try {
			// 转成直接返回
			return simpleDateFormat.parse(s);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 如果参数绑定失败返回null
		return null;
	}
}
