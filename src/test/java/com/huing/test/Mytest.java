package com.huing.test;

import com.huing.utils.MD5Util;
import org.junit.Test;

/**
 * @author huing
 * @create 2022-06-07 9:58
 */
public class Mytest {
    @Test
    public void testMD5(){
        String mi = MD5Util.getMD5("000000");
        System.out.println(mi);
    }
}
