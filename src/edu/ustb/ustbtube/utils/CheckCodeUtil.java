package edu.ustb.ustbtube.utils;

import java.util.Random;

public class CheckCodeUtil {

    /**
     * 生成指定位数的随机数字
     * @param len 位数
     * @return
     */


    public static String getCode(int len){
        StringBuilder sb = new StringBuilder();

        for(int i = 0; sb.length() < len; i++){
            int num = new Random().nextInt(10);
            sb.append(num);
        }

        return  sb.toString();

    }

}
