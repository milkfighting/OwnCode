package org.example.string;

/**
 * 字符串常用方法类
 *
 * @author 王泓桥
 * @date 2021.10.14
 */
public class StringDemo {

    public static void main(String[] args) {
//        System.out.println(indexOfReturnValue());
//        splicingString();
//        subStr();
//        joinStr();
//        equalsStr();
        System.out.println(isEmpty(""));
    }

    /**
     * indexOf方法
     */
    private static int indexOfReturnValue() {
        String str1 = "abcabcbac";
        String str2 = "abce";
        // 0代表包含, -1代表不包含
        return str1.indexOf(str2);
    }

    /**
     * String的拼接, 任何类型的和String拼接都会变成String
     */
    private static void splicingString() {
        String str1 = "4545";
        long l1 = 126;
        double d1 = 123.4564;
        String str2 = str1 + l1 + d1;
        System.out.println(str2);
    }

    /**
     * String的截取, 截取括号以后的位数
     */
    private static void subStr() {
        String str = "1234567890";
        System.out.println(str.substring(8));
    }

    /**
     * 三元运算符与字符串拼接
     */
    private static void joinStr() {
        String str = "芒果";
        int i = 0;
        System.out.println(str + (i == 0 ? "庄周" : "蓝宝"));
    }

    /**
     * String比较
     */
    private static void equalsStr() {
        String srt = "";
        System.out.println(srt.equals(""));
    }

    /**
     * String空字符串
     */
    private static boolean isEmpty(CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    /**
     * String.intern()
     */
}
