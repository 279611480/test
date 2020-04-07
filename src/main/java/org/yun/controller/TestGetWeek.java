package org.yun.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author 芸
 * @date 2020-28-20 14:28:48
 * @return
 **/

public class TestGetWeek {

    public static void main(String[] args) {

        int currentMaxDays = getCurrentMonthDay();

        int maxDaysByDate = getDaysByYearMonth(2020, 03);

        String week = getDayOfWeekByDate("2020-03-20");

        System.out.println("本月天数：" + currentMaxDays);
        System.out.println("2020年03月天数：" + maxDaysByDate);
        System.out.println("2020-03-20是：" + week);


//        在java中，一个用+连接的表达式中，只要出现了字符串类型，不管表达式中相加的是数值类型还是char类型，都会被全部隐式类型转换成字符串，而且这种隐式转换的优先级还高于+。
//        此例中的int型2被转换成string类型的“2”，然后和前面的“5”进行连接，所以最后的输出结果就是字符串“52”。
//        再如System.out.println("5" + 2 + 3); 的结果应该523，而不会是55。
        System.out.println("5" + 3);//53  优先级还高于+
        System.out.println("5" + 2 + 3);//523  优先级还高于+
    }

    /**
     * 获取当月的 天数
     */
    public static int getCurrentMonthDay() {

        Calendar a = Calendar.getInstance();
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }

    /**
     * 根据年 月 获取对应的月份 天数
     */
    public static int getDaysByYearMonth(int year, int month) {

        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }

    /**
     * 根据日期 找到对应日期的 星期
     */
    public static String getDayOfWeekByDate(String date) {
        String dayOfweek = "-1";
        try {
            SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
            Date myDate = myFormatter.parse(date);
            SimpleDateFormat formatter = new SimpleDateFormat("E");
            String str = formatter.format(myDate);
            dayOfweek = str;

        } catch (Exception e) {
            System.out.println("错误!");
        }
        return dayOfweek;
    }
}