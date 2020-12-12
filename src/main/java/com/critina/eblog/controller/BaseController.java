package com.critina.eblog.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.critina.eblog.service.CommentService;
import com.critina.eblog.service.PostService;
import com.critina.eblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: eblog
 * @description:
 * @author: sunzhen
 * @create: 2020-12-02 16:19
 **/
public class BaseController {

    @Autowired
    HttpServletRequest request;

    @Autowired
    PostService postService;

    @Autowired
    UserService userService;

    @Autowired
    CommentService commentService;

    public Page getPage() {
        //页数设置，默认为1
        int pn = ServletRequestUtils.getIntParameter(request, "pn", 1);
        //页面大小设置，默认为2
        int size = ServletRequestUtils.getIntParameter(request, "size", 2);
        return new Page(pn, size);
    }

}
