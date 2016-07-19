package com.openexpense.common;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**工具类测试
 *2016/07/06.
 *@author xjouyi@163.com
 */
public class TestUiUtil {

    /**md5产生码测试
     */
    @Test
    public void md5() {
        assertEquals("7388782AAD9B52227B4C91064BFAD161",UiUtil.md5("xjouyi")) ;
    }

    /**产生用户密码测试
     */
    @Test
    public void getPassWord(){
       assertEquals("3413A156EF0EF7021355D626C8C03FBF",UiUtil.getPassWord("xjouyi","123"));
    }

    @Test
    public void testDemo(){
        for (int i =3;i<= 862;i++){
            String ss = "=VLOOKUP(A"+i+",Sheet1!A1:D15,4,FALSE) \n";
            System.out.println(ss);
        }
    }
}
