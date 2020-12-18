package com.critina.eblog.vo;

import com.critina.eblog.entity.UserMessage;
import lombok.Data;

@Data
public class UserMessageVo extends UserMessage {

    private String toUserName; //目标用户名
    private String fromUserName; //源用户名
    private String postTitle; //帖子标题
    private String commentContent; //评论内容
    private String commentParentContent; //评论的评论内容

}
