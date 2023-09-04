package cn.jt.common.entity.other;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 *
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "Token")
public class Token implements Serializable {
    private static final long serialVersionUID = -8482946147572784305L;
    /**
     * token
     */
    @Schema(description = "token字符串")
    private String token;
    /**
     * 有效时间：单位：秒
     */
    @Schema(description = "有效期")
    private Integer expire;

}
