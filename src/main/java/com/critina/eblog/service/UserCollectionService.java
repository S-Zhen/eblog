package com.critina.eblog.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.critina.eblog.entity.UserCollection;
import com.baomidou.mybatisplus.extension.service.IService;
import com.critina.eblog.vo.UserCollectionVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author critina
 * @since 2020-12-02
 */
public interface UserCollectionService extends IService<UserCollection> {

   IPage<UserCollectionVo> paging(Page page, QueryWrapper<UserCollection> wrapper);
}
