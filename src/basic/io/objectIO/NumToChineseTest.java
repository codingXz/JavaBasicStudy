package io.objectIO;

import java.util.Scanner;

/**
 * @Author: xzw
 * @Date: 2019/12/13
 */
public class NumToChineseTest {

    private static String toChinese(String str) {

        String[] s1 = { "零", "一", "二", "三", "四", "五", "六", "七", "八", "九" };
        String[] s2 = { "十", "百", "千", "万", "十", "百", "千", "亿", "十", "百", "千" };
        String result = "";
        int n = str.length();
        for (int i = 0; i < n; i++) {
            int num = str.charAt(i) - '0';
            if (i != n - 1 && num != 0) {
                result += s1[num] + s2[n - 2 - i];
            } else {
                result += s1[num];
            }
            System.out.println("  "+result);
        }
        System.out.println(result);
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("输入字符串：");
        String str = scanner.next();
        // 将字符串数字转化为汉字
        toChinese(str);
    }


    // 现要求输入：2019-12-12
    // 输出：二〇一九 年 十二 月 十二 日

    // 现要求输入：2019-09-09
    // 输出：二〇一九 年 九 月 九 日

    // 现要求输入：2019-12-22
    // 输出：二〇一九 年 十二 月 二十二 日





}
