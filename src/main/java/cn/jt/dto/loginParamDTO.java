package cn.jt.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@Schema(description = "登录参数")
public class loginParamDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "用户名")
    private String account;

    @Schema(description = "密码")
    private String password;

    @Schema(description = "机器唯一标识")
    private String key;

    @Schema(description = "验证码")
    private String code;

}
