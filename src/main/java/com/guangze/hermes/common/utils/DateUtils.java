package com.guangze.hermes.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: hermes
 * @description: 日期处理工具类
 * @author: daiguangze
 * @create: 2024-06-16 05:45
 **/
public class DateUtils {

    /**
     * 年到毫秒 模式
     * 常量可以转到 interface中定义,此处不再增加复杂度
     */
    private final static String YEAR_TO_MILLISECOND_PATTERN = "yyyy-MM-dd HH:mm:ss.SSS";

    /**
     * 将Date格式化成yyyy-MM-dd HH:mm:ss.SSS形式
     *
     * @param date Date
     * @return 格式化后的字符串
     */
    public static String dateFormatToMillisecond(Date date) {
        return dateFormat(YEAR_TO_MILLISECOND_PATTERN, date);
    }

    /**
     * 将Date格式化成字符串
     *
     * @param pattern 模式字符串
     * @param date    Date
     * @return 格式化后的字符串
     */
    public static String dateFormat(String pattern, Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }
}
