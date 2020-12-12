package com.critina.eblog.template;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.critina.eblog.common.template.DirectiveHandler;
import com.critina.eblog.common.template.TemplateDirective;
import com.critina.eblog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @program: eblog
 * @description: posts自定义标签
 * @author: sunzhen
 * @create: 2020-12-05 16:57
 **/
@Component
public class PostsTemplate extends TemplateDirective {

    @Autowired
    PostService postService;

    @Override
    public String getName() {
        return "posts";
    }

    @Override
    public void execute(DirectiveHandler handler) throws Exception {

        Integer level = handler.getInteger("level");
        Integer pn = handler.getInteger("pn", 1);
        Integer size = handler.getInteger("size", 3);
        Long categoryId = handler.getLong("categoryId");

        IPage paging = postService.paging(new Page(pn, size), categoryId, null, level, null, "created");

        handler.put(RESULTS,paging).render();

    }
}
