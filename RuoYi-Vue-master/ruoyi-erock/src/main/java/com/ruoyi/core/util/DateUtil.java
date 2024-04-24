package com.ruoyi.core.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public  class DateUtil {
    /**
     * 根据日期时间来判断第几节课
     * @param date 日期时间
     * @return
     */
    public static String getTime(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sdf.format(date);
        String s = format.split(" ")[1].split(":")[0];
        int i = Integer.parseInt(s);
        if (i < 10){
            return "一二节";
        }else if (i < 12){
            return "三四节";
        }else if (i < 16){
            return "五六节";
        }else {
            return "七八节";
        }

    }

    /**
     *  数据库LocalDateTime类型转换为Date类型
     * @param localDateTime 数据库LocalDateTime类型
     * @return
     */
    public static Date LocalDateTimeConvertDate(LocalDateTime localDateTime){
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        Date date = Date.from(instant);
        return date;

    }

    /**
     *  字符串转换为Date格式
     * @param string 字符串
     * @return
     */
    public static Date StringConvertDate(String string){
        // 定义日期时间格式化器
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // 解析字符串为 Date 对象
        Date date = null;
        try {
            date = (Date) dateFormat.parse(string);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;

    }

    /**
     *  Date转换为字符串格式
     * @param date 日期
     * @return
     */
    public static String DateConvertString(Date date){
        // 定义日期时间格式化器
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String format = sdf.format(date);
        return format;
    }

    /**
     * LocalDateTime转换为字符串格式
     * @param localDateTime 数据库LocalDateTime类型
     * @return
     */
    public static String LocalDateTimeConvertString(LocalDateTime localDateTime){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String format = dateTimeFormatter.format(localDateTime);
        return format;
    }

}
