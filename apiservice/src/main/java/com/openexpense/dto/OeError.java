package com.openexpense.dto;

import org.springframework.validation.FieldError;

/**错误类型
 *2016/07/15.
 *@author xjouyi@163.com
 */
public class OeError {
    /**字段*/
    private String field;
    /**消息*/
    private String message;

    public OeError(String field,String message){
        this.field = field;
        this.message = message;
    }

    public OeError(FieldError error){
        this.field = error.getField();
        this.message = error.getDefaultMessage();
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
