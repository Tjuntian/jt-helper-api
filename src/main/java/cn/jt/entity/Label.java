package cn.jt.entity;

import cn.jt.common.entity.Entity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author JunTian
 * @since 2023-05-24 20:12:09
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("jt_label")
@Schema(description = "标签")
public class Label extends Entity<Long> {

    private static final long serialVersionUID = 1L;

    @Schema(description = "标签")
    @TableField("label")
    private String label;
}
