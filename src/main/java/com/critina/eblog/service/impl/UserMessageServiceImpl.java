package com.critina.eblog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.critina.eblog.entity.UserMessage;
import com.critina.eblog.mapper.UserMessageMapper;
import com.critina.eblog.service.UserMessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author critina
 * @since 2020-12-02
 */
@Service
public class UserMessageServiceImpl extends ServiceImpl<UserMessageMapper, UserMessage> implements UserMessageService {

    @Autowired
    UserMessageMapper messageMapper;

    @Override
    public IPage paging(Page page, QueryWrapper<UserMessage> wrapper) {
        return messageMapper.selectMessages(page, wrapper);
    }

    @Override
    public void updateToReaded(List<Long> ids) {
        if(ids.isEmpty()) return;

        messageMapper.updateToReaded(new QueryWrapper<UserMessage>()
                .in("id", ids)
        );

    }
}
