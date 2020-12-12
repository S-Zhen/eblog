package com.critina.eblog.vo;

import com.critina.eblog.entity.Comment;
import lombok.Data;

/**
 * @program: eblog
 * @description:
 * @author: sunzhen
 * @create: 2020-12-06 15:43
 **/
@Data
public class CommentVo extends Comment {

    private Long authorId; //作者id
    private String authorName; //作者名称
    private String authorAvatar; //作者头像

}
