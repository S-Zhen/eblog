package com.critina.eblog.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.critina.eblog.service.*;
import com.critina.eblog.shiro.AccountProfile;
import org.apache.shiro.SecurityUtils;
import org.springframework.amqp.core.AmqpTemplate;
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

    //分页参数：默认页数
    public final static Long PAGE_CURRENT = 1L;
    //分页参数：默认每页大小
    public final static Long PAGE_SIZE = 2L;

    @Autowired
    HttpServletRequest request;

    @Autowired
    PostService postService;

    @Autowired
    UserService userService;

    @Autowired
    CommentService commentService;

    @Autowired
    UserCollectionService userCollectionService;

    @Autowired
    UserMessageService userMessageService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    WsService wsService;

    @Autowired
    SearchService searchService;

    @Autowired
    AmqpTemplate amqpTemplate;

    @Autowired
    ChatService chatService;

    /**
    * @Description: 获取由前端传来参数的page
    * @Param: []
    * @return: com.baomidou.mybatisplus.extension.plugins.pagination.Page
    * @Author: sunzhen
    * @Date: 2020/12/19
    */
    public Page getPage() {
        //页数设置，默认为1
        int pn = ServletRequestUtils.getIntParameter(request, "pn", PAGE_CURRENT.intValue());
        //页面大小设置，默认为2
        int size = ServletRequestUtils.getIntParameter(request, "size", PAGE_SIZE.intValue());
        return new Page(pn, size);
    }

    /**
    * @Description: 获得默认参数的page
    * @Param: []
    * @return: com.baomidou.mybatisplus.extension.plugins.pagination.Page
    * @Author: sunzhen
    * @Date: 2020/12/19
    */
    public Page getDefaultPage() {
        return new Page(PAGE_CURRENT, PAGE_SIZE);
    }

    protected AccountProfile getProfile() {
        return (AccountProfile) SecurityUtils.getSubject().getPrincipal();
    }

    protected Long getProfileId() {
        return getProfile().getId();
    }

}
