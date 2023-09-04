package cn.jt.common.entity.other;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * jwt 存储的 内容
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Jwt对象")
public class JwtUser implements Serializable {

    /**
     * 姓名
     */
    @Schema(description = "姓名")
    private String name;

    /**
     * 账号id
     */
    @Schema(description = "账号Id")
    private Long userId;

    /**
     * 账号
     */
    @Schema(description = "账号")
    private String account;
}
