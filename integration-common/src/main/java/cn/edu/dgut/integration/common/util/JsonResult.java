package cn.edu.dgut.integration.common.util;

import java.io.Serializable;

/**
 * @Description: 自定义响应数据结构 这个类是提供给门户，ios，安卓，微信商城用的 门户接受此类数据后需要使用本类的方法转换成对于的数据类型格式（类，或者list） 其他自行处理
 * 200：表示成功
 * 401：请求未授权
 * 500：表示错误，错误信息在msg字段中
 * 501：bean验证错误，不管多少个错误都以map形式返回
 * 502：拦截器拦截到用户token出错
 * 555：异常抛出信息
 */
public class JsonResult implements Serializable {

    private static final long serialVersionUID = -7300179958056769765L;

    // 响应业务状态
    private Integer code;

    // 响应消息
    private String msg;

    // 响应中的数据
    private Object data;


    public static JsonResult build(Integer code, String msg, Object data) {
        return new JsonResult(code, msg, data);
    }

    public static JsonResult ok(Object data) {
        return new JsonResult(data);
    }

    public static JsonResult ok() {
        return new JsonResult(null);
    }

    public static JsonResult errorMsg(String msg) {
        return new JsonResult(500, msg, null);
    }

    public static JsonResult errorMap(Object data) {
        return new JsonResult(501, "error", data);
    }

    public static JsonResult errorTokenMsg(String msg) {
        return new JsonResult(502, msg, null);
    }

    public static JsonResult errorException(String msg) {
        return new JsonResult(555, msg, null);
    }

    public static JsonResult errorAuthorized(String msg) {
        return new JsonResult(401, msg, null);
    }

    public JsonResult() {

    }

    public JsonResult(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 默认返回200(请求成功)
     */
    public JsonResult(Object data) {
        this.code = 200;
        this.msg = "OK";
        this.data = data;
    }

    /**
     * 判断返回码是否为 200(请求成功)
     *
     * @return
     */
    public Boolean isOK() {
        return this.code == 200;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


}
