package com.crm.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * TODO
 *
 * @author hanzeyu
 * @version 1.0
 * @name DateUtil
 * @date 2021/1/28 12:13
 */
public class DateUtil {

    public static String date2String(Date date, String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String dateStr = sdf.format(date);
        return dateStr;
    }

    public static Date string2Date(String dateStr,String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
