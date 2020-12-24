package com.critina.eblog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.critina.eblog.common.convert.PostMapper;
import com.critina.eblog.entity.Post;
import com.critina.eblog.search.model.PostDocument;
import com.critina.eblog.search.mq.PostMqIndexMessage;
import com.critina.eblog.search.repository.PostRepository;
import com.critina.eblog.service.PostService;
import com.critina.eblog.service.SearchService;
import com.critina.eblog.vo.PostVo;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: eblog
 * @description:
 * @author: sunzhen
 * @create: 2020-12-24 10:13
 **/
@Slf4j
@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    PostRepository postRepository;

    /*@Autowired
    PostMapper postMapper;*/

    @Autowired
    PostService postService;

    @Override
    public IPage search(Page page, String keyword) {
        // 分页信息 mybatis plus的page 转成 jpa的page
        Long current = page.getCurrent() - 1;
        Long size = page.getSize();
        Pageable pageable = PageRequest.of(current.intValue(), size.intValue());

        // 搜索es得到pageData
        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery(keyword,
                "title", "authorName", "categoryName");

        org.springframework.data.domain.Page<PostDocument> documents = postRepository.search(multiMatchQueryBuilder, pageable);

        // 结果信息 jpa的pageData转成mybatis plus的pageData
        IPage pageData = new Page(page.getCurrent(), page.getSize(), documents.getTotalElements());
        pageData.setRecords(documents.getContent());
        return pageData;
    }

    @Override
    public int initEsData(List<PostVo> records) {
        if(records == null || records.isEmpty()) {
            return 0;
        }

        List<PostDocument> documents = new ArrayList<>();
        for(PostVo vo : records) {
            // 映射转换
            PostDocument postDocument = PostMapper.INSTANCE.postVoToPostDocument(vo);
            //PostDocument postDocument = postMapper.postVoToPostDocument(vo);
            documents.add(postDocument);

        }
        postRepository.saveAll(documents);
        return documents.size();
    }

    @Override
    public void createOrUpdateIndex(PostMqIndexMessage message) {
        Long postId = message.getPostId();
        PostVo postVo = postService.selectOnePost(new QueryWrapper<Post>().eq("p.id", postId));

        PostDocument postDocument = PostMapper.INSTANCE.postVoToPostDocument(postVo);

        postRepository.save(postDocument);

        log.info("es 索引更新成功！ ---> {}", postDocument.toString());
    }

    @Override
    public void removeIndex(PostMqIndexMessage message) {
        Long postId = message.getPostId();

        postRepository.deleteById(postId);
        log.info("es 索引删除成功！ ---> {}", message.toString());
    }

}
