package com.critina.eblog.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.critina.eblog.entity.Post;
import com.critina.eblog.service.CommentService;
import com.critina.eblog.vo.CommentVo;
import com.critina.eblog.vo.PostVo;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: eblog
 * @description: 博客分类、细节控制器
 * @author: sunzhen
 * @create: 2020-11-20 12:36
 **/
@Controller
public class PostController extends BaseController {

    @GetMapping("/category/{id:\\d*}")
    public String category(@PathVariable(name = "id") Long id) {

        int pn = ServletRequestUtils.getIntParameter(request, "pn", 1);

        request.setAttribute("currentCategoryId", id);
        request.setAttribute("pn", pn);
        return "post/category";
    }

    @GetMapping("/post/{id:\\d*}")
    public String detail(@PathVariable(name = "id") Long id) {

        PostVo vo = postService.selectOnePost(new QueryWrapper<Post>().eq("p.id", id));
        Assert.notNull(vo, "文章已被删除");

        //缓存阅读量
        postService.putViewCount(vo);

        //1.分页 2.文章id 3.用户id 4.排序
        IPage<CommentVo> results = commentService.paging(getPage(), vo.getId(), null, "created");

        //request.setAttribute("currentCategoryId", vo.getCategoryId());
        request.setAttribute("pageData", results);
        request.setAttribute("post", vo);
        return "post/detail";
    }
}
