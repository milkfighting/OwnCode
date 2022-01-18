package org.example.date;

import java.util.Calendar;

/**
 * DateUtils
 *
 * @author 王泓桥
 * @date 2021.11.23
 */
public class DateUtils {

    public static void main(String[] args) {
        long closeDate = 20211213235960l;
        System.out.println(DateUtils.calculateTimeMillis(closeDate, Calendar.DATE, -2));
        System.out.println(getWeekDay());
    }

    /**
     * 将指定的时间提前或退后
     */
    public static long calculateTimeMillis(long datetime1, int field, int number) {

        String datetime = Long.toString(datetime1);
//        System.out.println(datetime);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, Integer.parseInt(datetime.substring(0, 4)));
        calendar.set(Calendar.MONTH, Integer.parseInt(datetime.substring(4, 6)) - 1);
        calendar.set(Calendar.DATE, Integer.parseInt(datetime.substring(6, 8)));
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(datetime.substring(8, 10)));
        calendar.set(Calendar.MINUTE, Integer.parseInt(datetime.substring(10, 12)));
        calendar.set(Calendar.SECOND, Integer.parseInt(datetime.substring(12, 14)));
        calendar.add(field, number);
        long currentTime = getLongDateTime(calendar.getTime().getTime());
        return currentTime;
    }

    /**
     * 把毫秒数转换成数字日期和时间。
     */
    public static long getLongDateTime(long millis) {
        if (millis <= 0)
            return -1;
        Calendar rightNow = getCalendar();
        rightNow.setTime(new java.util.Date(millis));
        int year = rightNow.get(Calendar.YEAR);
        int month = rightNow.get(Calendar.MONTH) + 1;
        int day = rightNow.get(Calendar.DAY_OF_MONTH);
        int hour = rightNow.get(Calendar.HOUR_OF_DAY);
        int minute = rightNow.get(Calendar.MINUTE);
        int second = rightNow.get(Calendar.SECOND);
        String strDateTime =
                           year
                        + (month < 10 ? "0" + month : month + "")
                        + (day < 10 ? "0" + day : day + "")
                        + (hour < 10 ? "0" + hour : hour + "")
                        + (minute < 10 ? "0" + minute : minute + "")
                        + (second < 10 ? "0" + second : second + "");
        return Long.parseLong(strDateTime);
    }

    /**
     * 返回星期几
     */
    public static int getWeekDay() {
        Calendar rightNow = getCalendar();
        return rightNow.get(Calendar.DAY_OF_WEEK) - 1;
    }


    private static Calendar getCalendar() {
        return Calendar.getInstance();
    }
}
