package com.openexpense.exception;

/**
 * Created by jinyi on 16/7/3.
 */

import com.openexpense.dto.OeResult;

/**系统异常定义
 *2016/07/02.
 *@author xjouyi@163.com
 *@version 0.1
 */
public abstract class OeException extends Exception {

    /**获取返回对象
     *@return 根据异常获取返回对象
     */
    public abstract OeResult getResult();
}
