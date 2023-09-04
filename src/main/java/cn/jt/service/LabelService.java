package cn.jt.service;

import cn.jt.entity.Label;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author JunTian
 * @since 2023-05-24 20:12:09
 */
public interface LabelService extends IService<Label> {

    /**
     * 根据HelperId和LabelId删除指定label,同时,只要该label没有helper则删除该label
     */
    public Boolean delLabelByHelperIdAndLabelId(Long helperId, Long labelId);

    /**
     * 根据helperId添加label
     */
    public Boolean saveLabelByHelperId(Long helperId, Label label);
}
