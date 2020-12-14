package com.critina.eblog.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.critina.eblog.common.lang.Result;
import com.critina.eblog.entity.User;
import com.critina.eblog.mapper.UserMapper;
import com.critina.eblog.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.critina.eblog.shiro.AccountProfile;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author critina
 * @since 2020-12-02
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public Result register(User user) {
        //查询数据库中是否存在email或者用户名相同的用户
        int count = this.count(new QueryWrapper<User>()
                .eq("email", user.getEmail())
                .or()
                .eq("username", user.getUsername())
        );
        if(count > 0) return Result.fail("用户名或邮箱已被占用");

        //设置用户信息及默认值
        User temp = new User();
        temp.setUsername(user.getUsername());
        temp.setPassword(SecureUtil.md5(user.getPassword()));
        temp.setEmail(user.getEmail());
        temp.setAvatar("/res/images/avatar/default.png"); //默认头像

        temp.setCreated(new Date());
        temp.setPoint(0);
        temp.setVipLevel(0);
        temp.setCommentCount(0);
        temp.setPostCount(0);
        temp.setGender("0");
        this.save(temp);

        return Result.success();
    }

    @Override
    public AccountProfile login(String email, String password) {

        //查询数据库中是否已经含有email为传入值的用户
        User user = this.getOne(new QueryWrapper<User>().eq("email", email));
        //不存在该账号
        if(user == null) {
            throw new UnknownAccountException();
        }
        //密码错误
        if(!user.getPassword().equals(password)){
            throw new IncorrectCredentialsException();
        }
        //设置最后的登录时间
        user.setLasted(new Date());
        //更新用户
        this.updateById(user);

        AccountProfile profile = new AccountProfile();
        BeanUtil.copyProperties(user, profile);

        return profile;
    }

}
