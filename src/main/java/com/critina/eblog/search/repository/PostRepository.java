package com.critina.eblog.search.repository;

import com.critina.eblog.search.model.PostDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends ElasticsearchRepository<PostDocument, Long> {
    // 符合jpa命名规范的接口
}
