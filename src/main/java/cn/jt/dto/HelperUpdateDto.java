package cn.jt.dto;

import cn.jt.common.entity.SuperEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@Schema(description = "修改返回参数")
public class HelperUpdateDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    @NotNull(message = "id不能为空", groups = SuperEntity.Update.class)
    private Long id;


    private String notebook;


    private Boolean sign;
}
