package com.critina.eblog.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.critina.eblog.entity.Category;
import com.critina.eblog.service.CategoryService;
import com.critina.eblog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;
import java.util.List;

/**
 * @program: eblog
 * @description: 项目启动配置类
 * @author: sunzhen
 * @create: 2020-12-03 13:35
 **/
@Component
public class ContextStartup implements ApplicationRunner, ServletContextAware {

    @Autowired
    CategoryService categoryService;

    ServletContext servletContext;

    @Autowired
    PostService postService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<Category> categories = categoryService.list(new QueryWrapper<Category>()
                .eq("status", 0)
        );
        servletContext.setAttribute("categories", categories);

        //初始化周榜
        postService.initWeekRank();
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }
}
