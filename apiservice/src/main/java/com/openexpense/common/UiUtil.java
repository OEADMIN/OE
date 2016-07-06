package com.openexpense.common;

import java.security.MessageDigest;

/**工具类
 *2016/07/06.
 *@author xjouyi@163.com
 */
public final class UiUtil {

    /**产生md5
     *@param str string 待生成字串
     *@return md5字串
     */
    public static String md5(String str){
        StringBuffer sb = new StringBuffer(32);
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] array = md.digest(str.getBytes("UTF-8"));
            for (int i = 0; i < array.length; i++) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).toUpperCase().substring(1, 3));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return sb.toString();
    }

    /**产生用户md5密码串
     *@param userId string 用户id
     *@param password string 密码
     *@return md5密码串
     */
    public static String getPassWord(String userId,String password){
        return md5(md5(userId) + md5(password));
    }
}
