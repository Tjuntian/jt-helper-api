package cn.jt.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 标签-笔记
 * </p>
 *
 * @author JunTian
 * @since 2023-06-06 08:59:55
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("label_helper")
@Schema(description = "标签-笔记")
@NoArgsConstructor
@AllArgsConstructor
public class LabelHelper {

    private static final long serialVersionUID = 1L;

    @Schema(description = "标签id")
    @TableField("label_id")
    private Long labelId;


    @Schema(description = "笔记id")
    @TableField("helper_id")
    private Long helperId;
}
