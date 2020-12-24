package com.critina.eblog.search.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Date;

@Data
@Document(indexName="post", type="post", createIndex=true)
public class PostDocument implements Serializable {

    @Id
    private Long id;

    // ik分词器
    @Field(type = FieldType.Text, searchAnalyzer="ik_smart", analyzer = "ik_max_word")
    private String title; //标题

    //@Field(type = FieldType.Long)
    private Long authorId; //作者ID
    @Field(type = FieldType.Keyword)
    private String authorName; //作者姓名
    private String authorAvatar; //作者头像

    private Long categoryId; //分类ID
    @Field(type = FieldType.Keyword)
    private String categoryName; //分类名称

    private Integer level; //是否置顶
    private Boolean recommend; //是否推荐

    private Integer commentCount; //评论数量
    private Integer viewCount; //查阅次数

    @Field(type = FieldType.Date)
    private Date created; //发帖时间


}
