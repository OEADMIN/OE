package com.openexpense.exception;

/**
 * Created by jinyi on 16/7/3.
 */

import com.openexpense.dto.OeError;
import com.openexpense.dto.OeResult;

/**系统异常定义
 *2016/07/02.
 *@author xjouyi@163.com
 *@version 0.1
 */
public class OeException extends Exception {


    private OeError error;

    /**根据公司异常类型创建公司异常对象
     * @param error String 公司异常类型创
     */
    public OeException(OeError error){
        this.error = error;
    }

    public OeException(String message){
        this.error = new OeError("",message);
    }
    public OeException(String field,String message){
        this.error = new OeError(field,message);
    }

    /**获取返回对象
     *@return 根据异常获取返回对象
     */
    public OeResult getResult(){
        return OeResult.getFailResult(this.error);
    }
}
