package com.critina.eblog.common.lang;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: eblog
 * @description: 用于返回页面信息的载体
 * @author: sunzhen
 * @create: 2020-12-11 15:29
 **/
@Data
public class Result implements Serializable {

    // 0成功，-1失败
    private int status;
    private String msg; //提示消息
    private Object data; //操作数据
    private String action; //跳转路径

    public static Result success() {
        return Result.success("操作成功", null);
    }

    public static Result success(Object data) {
        return Result.success("操作成功", data);
    }

    public static Result success(String msg, Object data) {
        Result result = new Result();
        result.status = 0;
        result.msg = msg;
        result.data = data;
        return result;
    }

    public static Result fail(String msg) {
        Result result = new Result();
        result.status = -1;
        result.data = null;
        result.msg = msg;
        return result;
    }

    public Result action(String action){
        this.action = action;
        return this;
    }


}
