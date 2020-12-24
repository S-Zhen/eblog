package com.critina.eblog.search.mq;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor //含有所有属性作为参数的构造方法
public class PostMqIndexMessage implements Serializable {

    // 两种type
    public final static String CREATE_OR_UPDATE = "create_update";
    public final static String REMOVE = "remove";

    private Long postId;
    private String type;

}
