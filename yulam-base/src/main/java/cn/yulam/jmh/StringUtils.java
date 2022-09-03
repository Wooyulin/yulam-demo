package cn.yulam.jmh;

public class StringUtils {



    public static String testStringAdd(int length) {
        String a = "";
        for (int i = 0; i < length; i++) {
            a += i;
        }
        return a;
    }

    public static String testStringBuilderAdd(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(i);
        }
        return sb.toString();
    }
}
