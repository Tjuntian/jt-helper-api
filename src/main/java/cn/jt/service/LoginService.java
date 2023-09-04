package cn.jt.service;

import cn.jt.common.R;
import cn.jt.common.entity.other.Token;
import cn.jt.dto.LoginDTO;
import cn.jt.entity.User;

public interface LoginService {

    //登录认证
    R<LoginDTO> login(String account, String password);


    //账号、密码校验
    public R<User> check(String account, String password);

    //为当前登录用户生成对应的jwt令牌
    public Token generateUserToken(User user);
}
