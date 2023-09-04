package cn.jt.mapper;

import cn.jt.entity.Helper;
import cn.jt.entity.Label;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 助手 Mapper 接口
 * </p>
 *
 * @author JunTian
 * @since 2023-05-23 14:59:09
 */
@Mapper
public interface HelperMapper extends BaseMapper<Helper> {
    List<Label> getByHelperId(Long helperId);

    List<Helper> getPageByHelperId(Long labelId, Long current, Long size, Helper data);
}
