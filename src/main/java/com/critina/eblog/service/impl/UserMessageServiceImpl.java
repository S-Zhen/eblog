package com.critina.eblog.service.impl;

import com.critina.eblog.entity.UserMessage;
import com.critina.eblog.mapper.UserMessageMapper;
import com.critina.eblog.service.UserMessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class UserMessageServiceImpl extends ServiceImpl<UserMessageMapper, UserMessage> implements UserMessageService {

}
