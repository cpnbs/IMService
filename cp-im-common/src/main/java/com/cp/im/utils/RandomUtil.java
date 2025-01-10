package com.cp.im.utils;

import java.util.Random;

/**
 * @ClassName RandomUtil
 * @Description: TODO 产生ID值
 * @Author xubin
 * @Date 2020/4/8
 * @Version V1.0
 **/
public class RandomUtil {

    public static String getRandom() {
        return System.currentTimeMillis() + "" + cn.hutool.core.util.RandomUtil.randomInt(999);
    }

    /**
     * 六位随机数
     * @return
     */
    public static String getRandomSixNum() {
        int rannum = (int)(Math.random()*(999999-100000+1))+100000;
        return ""+rannum;
    }

    /**
     * 生成随机数字和字母
     * @return
     */
    public static String getStringRandom(int length) {
        String val = "";
        Random random = new Random();

        //参数length，表示生成几位随机数
        for(int i = 0; i < length; i++) {

            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            //输出字母还是数字
            if( "char".equalsIgnoreCase(charOrNum) ) {
                //输出是大写字母还是小写字母
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char)(random.nextInt(26) + temp);
            } else if( "num".equalsIgnoreCase(charOrNum) ) {
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }

    public static String getNumberRandom(int length) {
        String val = "";
        Random random = new Random();
        //参数length，表示生成几位随机数
        for(int i = 0; i < length; i++) {
            val = val + random.nextInt(10);
        }
        return val;
    }

}
