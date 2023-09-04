package cn.jt.service.impl;


import cn.jt.common.R;
import cn.jt.utils.JwtTokenUtils;
import cn.jt.common.entity.other.JwtUser;
import cn.jt.common.entity.other.Token;
import cn.jt.exception.ExceptionCode;
import cn.jt.dto.LoginDTO;
import cn.jt.entity.User;
import cn.jt.service.LoginService;
import cn.jt.service.UserService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 认证管理器
 */
@Service
@Slf4j
public class
LoginServiceImpl implements LoginService {
    @Autowired
    private UserService userService;

    private JwtTokenUtils jwtTokenUtils = new JwtTokenUtils();


    //登录认证
    public R<LoginDTO> login(String account, String password) {
        //校验账号、密码是否正确
        R<User> userR = check(account, password);
        Boolean isError = userR.getIsError();
        if (isError) {
            return R.fail("账号或密码错误");
        }

        //为用户生成jwt令牌
        User user = userR.getData();
        Token token = generateUserToken(user);

        //封装返回结果
        LoginDTO loginDTO = new LoginDTO()
                .setToken(token.getToken())
                .setName(user.getName())
                .setExpire(token.getExpire().toString());
        return R.success(loginDTO);
    }

    //账号、密码校验
    public R<User> check(String account, String password) {
        User user = userService.getOne(Wrappers.<User>lambdaQuery().eq(User::getAccount, account));

        //将前端提交的密码进行md5加密
        String md5Hex = DigestUtils.md5Hex(password);

        if (user == null || !user.getPassword().equals(md5Hex)) {
            //认证失败
            return R.fail(ExceptionCode.JWT_USER_INVALID);
        }
        //认证成功
        return R.success(user);
    }

    //为当前登录用户生成对应的jwt令牌
    public Token generateUserToken(User user) {
        JwtUser jwtUser = new JwtUser(user.getName(), user.getId(), user.getAccount());
        return jwtTokenUtils.generateUserToken(jwtUser, null);
    }
}
