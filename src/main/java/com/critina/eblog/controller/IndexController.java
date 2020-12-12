package com.critina.eblog.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.critina.eblog.entity.Post;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: eblog
 * @description: 首页控制器
 * @author: sunzhen
 * @create: 2020-11-20 12:36
 **/
@Controller
public class IndexController extends BaseController {

    @RequestMapping({"", "/", "index"})
    public String index() {

        //1.分页信息 2.分类 3.用户 4.置顶 5.精选 6.排序
        IPage results = postService.paging(getPage(), null, null, null, null, "created");

        request.setAttribute("pageData", results);
        request.setAttribute("currentCategoryId", 0);
        return "index";
    }

}
