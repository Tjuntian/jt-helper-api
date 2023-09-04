package cn.jt.utils;


import cn.jt.common.entity.other.JwtUser;
import cn.jt.common.entity.other.Token;
import cn.jt.common.exception.BizException;
import cn.jt.common.entity.other.Authority;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * jwt token 工具
 *
 */
@AllArgsConstructor
@NoArgsConstructor
public class JwtTokenUtils {
    /**
     * 认证服务端使用，如 authority-server
     * 生成和 解析token
     */
    private Authority authority=new Authority();

    /**
     * 生成token
     * @param jwtInfo
     * @param expire
     * @return
     * @throws BizException
     */
    public Token generateUserToken(JwtUser jwtInfo, Integer expire) throws BizException {
        if (expire == null || expire <= 0) {
            expire = authority.getExpire();
        }
        return JwtUtils.generateUserToken(jwtInfo, authority.getPriKey(), expire);
    }

    /**
     * 解析token
     * @param token
     * @return
     * @throws BizException
     */
    public JwtUser getUserInfo(String token) throws BizException {
        return JwtUtils.getJwtFromToken(token, authority.getPubKey());
    }
}
