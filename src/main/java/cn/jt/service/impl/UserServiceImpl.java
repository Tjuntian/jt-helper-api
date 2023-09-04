package cn.jt.service.impl;

import cn.jt.entity.User;
import cn.jt.mapper.UserMapper;
import cn.jt.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息 服务实现类
 * </p>
 *
 * @author JunTian
 * @since 2023-05-23 23:10:38
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
