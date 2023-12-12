package com.csm.ds.class07;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 求几个字符串拼接得到字符串字典序最小（贪心）
 */
public class Code02_LowestLexicography {
    /*
     * 字符串的compareTo返回两个字符串中字典序最小的
     * 这里把a + b 和 b + a两种拼接方式进行比较
     * 返回两种拼接方式中更小的
     */
    public static class MyComparator implements Comparator<String> {
        @Override
        public int compare(String a, String b) {
            return (a + b).compareTo(b + a);
        }
    }

    public static String lowestString(String[] str) {
        if (str == null || str.length == 0) {
            return "";
        }
        StringBuilder res = new StringBuilder();
        //把字符串按拼接方式最小的进行排序
        Arrays.sort(str, new MyComparator());
        for (String s : str) {
            res.append(s);
        }
        return res.toString();
    }

    public static void main(String[] args) {
        String[] str1 = {"ba", "b"};
        System.out.println(lowestString(str1));
        String[] str2={"jibw","ji","jp","bw","ac"};
        System.out.println(lowestString(str2));
    }
}
