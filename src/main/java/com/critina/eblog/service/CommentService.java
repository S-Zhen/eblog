package com.critina.eblog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.critina.eblog.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.critina.eblog.vo.CommentVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author critina
 * @since 2020-12-02
 */
public interface CommentService extends IService<Comment> {

    IPage<CommentVo> paging(Page page, Long postId, Long userId, String order);
}
