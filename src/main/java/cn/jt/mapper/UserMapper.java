package cn.jt.mapper;

import cn.jt.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户信息 Mapper 接口
 * </p>
 *
 * @author JunTian
 * @since 2023-05-23 23:10:38
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
