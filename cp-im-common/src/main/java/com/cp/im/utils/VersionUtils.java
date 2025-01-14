package com.cp.im.utils;

/**
 * @Description:
 * @Author wangxuewen
 * @Date 2021/9/3
 * @Version V3.0
 **/
public class VersionUtils {
    /**
     * 比较版本号的大小
     *
     * @param version1 版本 1
     * @param version2 版本 2
     * @return 版本1大则返回一个正数, 版本2大返回一个负数, 相等则返回0
     */
    public static int compareVersion(String version1, String version2) {
        // 注意此处为正则匹配，不能用"."；
        String[] versionArray1 = version1.split("\\.");
        String[] versionArray2 = version2.split("\\.");
        int idx = 0;
        // 取最小长度值
        int minLength = Math.min(versionArray1.length, versionArray2.length);
        int diff = 0;
        while (idx < minLength
                // 先比较长度
                && (diff = versionArray1[idx].length() - versionArray2[idx].length()) == 0
                && (diff = versionArray1[idx].compareTo(versionArray2[idx])) == 0) {
            // 再比较字符
            ++idx;
        }
        // 如果已经分出大小，则直接返回，如果未分出大小，则再比较位数，有子版本的为大；
        diff = (diff != 0) ? diff : versionArray1.length - versionArray2.length;
        return diff;
    }
}
