package cn.jt.dto;

import cn.jt.entity.Label;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@Schema(description = "分页数据返回")
public class HelperDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private Long id;
    private String notebook;
    private Boolean sign;
    private List<Label> labelList;
    protected LocalDateTime updateTime;
}
