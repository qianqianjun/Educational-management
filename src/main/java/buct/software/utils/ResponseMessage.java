package buct.software.utils;

import lombok.Data;

/**
 * @author  高谦
 * 返回给浏览器的信息，用于格式化信息的返回数据
 */

public class ResponseMessage {

    /**
     * 静态属性设置的是http 状态码，前端根据这个状态码采取不一样的操作。
     */
    public static final Integer SUCCESS=200;
    public static final Integer WRONG=400;
    public static final Integer Exception=500;
    public static final Integer EmptyDate = 404;

    /**
     * 设置消息的静态工具函数，防止从多个方法中重复编写代码；
     * @param condition  条件
     * @param successCode  成功状态码
     * @param successMsg  成功提示消息
     * @param otherCode  失败状态码
     * @param otherMsg  失败提示消息
     * @return
     */
    public static ResponseMessage getMessage(Boolean condition,
                                       Integer successCode,String successMsg,
                                       Integer otherCode,String otherMsg){
        ResponseMessage message=new ResponseMessage();
        if(condition){
            message.setMessage(successMsg);
            message.setCode(successCode);
        }
        else{
            message.setMessage(otherMsg);
            message.setCode(otherCode);
        }
        return message;
    }

    /**
     * 一条返回消息包含三个字段：状态码，提示信息，返回的数据。
     * 状态码方便前端的验证，提示信息是给前端的一个提醒
     * 返回的数据在正常情况下是我们所期望的数据。
     */

    private Integer code;
    private String message;
    private Object data;

    /**
     * 构造函数
     * @param code  状态码，这里可以使用静态变量来进行设置
     * @param message  这里是反馈给前端的信息
     * @param data  这里是返回给前端的数据
     */
    public ResponseMessage(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 添加上get set 方法来符合java bean 规范
     */
    public ResponseMessage() {}

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
