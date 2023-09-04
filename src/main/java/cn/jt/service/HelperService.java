package cn.jt.service;

import cn.jt.dto.HelperDto;
import cn.jt.entity.Helper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 助手 服务类
 * </p>
 *
 * @author JunTian
 * @since 2023-05-23 14:59:09
 */
public interface HelperService extends IService<Helper> {
    public IPage<HelperDto> helperPage(IPage<Helper> page, Helper data);

    public HelperDto helperOne(Long id);

    List<HelperDto> getPageByHelperId(Long labelId, IPage<Helper> page, Helper data);
}
