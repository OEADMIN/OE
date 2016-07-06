package com.openexpense.dto;

import java.util.ArrayList;
import java.util.List;

/**服务返回类型
 *2016/07/06.
 *@author xjouyi@163.com
 */
public class OeResult {



    /**服务类型定义
     *2016/07/06.
     *@author xjouyi@163.com
     */
    public enum Type {
        /**成功 success*/
        success("success"),
        /**失败 fail*/
        fail("fail"),
        /**异常 error*/
        error("error");
        private String name;
        Type(String name) {
            this.name = name;
        }
        public String getName() {
            return this.name;
        }
    }

    private OeResult.Type type;
    private String code;
    private List<Object> data = null;


    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Object> getData(){
        return data;
    }

    private OeResult(){

    }

    /**根据类型初始化OeResult对象
     *@param type OeResult.Type 类型
     */
    public OeResult(OeResult.Type type){
        this.type = type;
    }

    /**根据类型和代码初始化OeResult对象
     *@param type OeResult.Type 类型
     *@param code string 代码
     */
    public OeResult(OeResult.Type type,String code){
        this.type = type;
        this.code = code;
    }

    /**获取返回成功对象
     *@param result Object 成功后返回数据
     *@return OeResult 返回成功对象,{type:success,data:[{}]}
     */
    public static OeResult getSuccessResult(Object result){
        OeResult oeResult = new OeResult(Type.success);
        oeResult.data = new ArrayList<>();
        oeResult.data.add(result);
        return oeResult;
    }

    /**获取返回失败对象
     *@param code Object 返回失败代码
     *@return OeResult 返回失败对象,{type:success,code:xxxx}
     */
    public static OeResult getFailResult(String code){
        return new OeResult(Type.fail,code);
    }

    /**获取返回错误对象
     *@param code Object 返回错误代码
     *@return OeResult 返回错误对象,{type:success,code:xxxx}
     */
    public static OeResult getErrorResult(String code){
        return new OeResult(Type.error,code);
    }

}
