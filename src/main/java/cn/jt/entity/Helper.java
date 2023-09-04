package cn.jt.entity;

import cn.jt.common.entity.Entity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * <p>
 * 助手
 * </p>
 *
 * @author JunTian
 * @since 2023-05-23 14:59:09
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@TableName("jt_helper")
@Schema(description = "核心")
public class Helper extends Entity<Long> {
    private static final long serialVersionUID = 1L;
    @Schema(description = "笔记")
    @TableField("notebook")
    private String notebook;

    @Schema(description = "星标")
    @TableField("sign")
    private Boolean sign;
}
