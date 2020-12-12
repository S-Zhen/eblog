package com.critina.eblog.vo;

import com.critina.eblog.entity.Post;
import lombok.Data;

/**
 * @program: eblog
 * @description:
 * @author: sunzhen
 * @create: 2020-12-05 14:07
 **/
@Data
public class PostVo extends Post {

    private Long authorId; //作者id
    private String authorName; //作者名称
    private String authorAvatar; //作者头像

    private String categoryName; //分类名称
}
