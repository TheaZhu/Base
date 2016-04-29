package com.thea.base.util;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author Thea (theazhu0321@gmail.com)
 */
public class DateFormatUtil {
    public static final String SECOND = "yy-MM-dd hh:mm:ss";
    public static final String DAY = "yyyy-MM-dd";
    public static final String DETAIL_DAY = "yyyy年MM月dd日";
    public static final String FILE_NAME = "yyyy-MM-dd-HH-mm-ss";
    public static final String TEMP_TIME = "yyyy-MM-dd HH:mm:ss";
    public static final String EXCEL_DATE = "yyyy/MM/dd";

    public static Date parseDate(String dateStr, String format) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.getDefault());
        return dateFormat.parse(dateStr);
    }

    public static String formatDate(Date date, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.getDefault());
        return dateFormat.format(date);
    }

    public static String formatDate(long millis, String format) {
        return formatDate(new Date(millis), format);
    }

    /**
     * 将double类型的数字格式化为字符串
     *
     * @param number double类型的数字
     * @param pattern 格式，如"#0.00"或"#.##"为保留两位小数（四舍五入）的格式
     * @return 格式化后的字符串
     */
    public static String formatNumber(double number, String pattern) {
        DecimalFormat df = new DecimalFormat(pattern);
        return df.format(number);
    }
}
