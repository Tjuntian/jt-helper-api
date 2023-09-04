package cn.jt.common.entity.other;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * 认证服务端 属性
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Accessors(chain = true)
@Schema(description = "公钥私钥过期")
public class Authority {
    /**
     * 过期时间
     * 一天
     */
    @Schema(description = "过期时间(一天86400)")
    private Integer expire = 36000;
    /**
     * 加密 服务使用
     * 公钥
     */
    @Schema(description = "公钥")
    private String priKey = "keys/pri.key";
    /**
     * 解密
     * 私钥
     */
    @Schema(description = "私钥")
    private String pubKey = "keys/pub.key";

}
