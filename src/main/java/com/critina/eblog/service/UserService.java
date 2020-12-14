package com.critina.eblog.service;

import com.critina.eblog.common.lang.Result;
import com.critina.eblog.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.critina.eblog.shiro.AccountProfile;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author critina
 * @since 2020-12-02
 */
public interface UserService extends IService<User> {

    Result register(User user);

    AccountProfile login(String username, String password);
}
