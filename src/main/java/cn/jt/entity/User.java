package cn.jt.entity;

import cn.jt.common.entity.Entity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户信息
 * </p>
 *
 * @author JunTian
 * @since 2023-05-23 23:10:38
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@TableName("jt_user")
@Schema(description = "用户")
public class User extends Entity<Long> {

    private static final long serialVersionUID = 1L;

    @Schema(description = "姓名")
    @TableField("name")
    private String name;

    @Schema(description = "用户名")
    @TableField("account")
    private String account;

    @Schema(description = "密码")
    @TableField("`password`")
    private String password;
}
