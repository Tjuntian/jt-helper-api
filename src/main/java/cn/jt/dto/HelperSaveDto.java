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
@Schema(description = "核心保存接收参数")
public class HelperSaveDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String notebook;

    private Boolean sign;
}
