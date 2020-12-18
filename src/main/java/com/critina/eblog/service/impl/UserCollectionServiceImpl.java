package com.critina.eblog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.critina.eblog.entity.UserCollection;
import com.critina.eblog.mapper.UserCollectionMapper;
import com.critina.eblog.service.UserCollectionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.critina.eblog.vo.UserCollectionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author critina
 * @since 2020-12-02
 */
@Service
public class UserCollectionServiceImpl extends ServiceImpl<UserCollectionMapper, UserCollection> implements UserCollectionService {

    @Autowired
    UserCollectionMapper userCollectionMapper;

    @Override
    public IPage<UserCollectionVo> paging(Page page, QueryWrapper<UserCollection> wrapper) {
        return userCollectionMapper.selectCollection(page, wrapper);
    }
}
