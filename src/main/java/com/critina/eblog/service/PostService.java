package com.critina.eblog.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.critina.eblog.entity.Post;
import com.baomidou.mybatisplus.extension.service.IService;
import com.critina.eblog.vo.PostVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author critina
 * @since 2020-12-02
 */
public interface PostService extends IService<Post> {

    IPage paging(Page page, Long categoryId, Long userId, Integer level, Boolean recommend, String order);

    PostVo selectOnePost(QueryWrapper<Post> wrapper);

    void initWeekRank();

    void incrCommentCountAndUnionForWeekRank(long postId, boolean isIncr);

    void putViewCount(PostVo vo);
}
