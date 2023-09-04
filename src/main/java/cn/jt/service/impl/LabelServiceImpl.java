package cn.jt.service.impl;

import cn.jt.entity.Label;
import cn.jt.entity.LabelHelper;
import cn.jt.mapper.LabelMapper;
import cn.jt.service.LabelHelperService;
import cn.jt.service.LabelService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author JunTian
 * @since 2023-05-24 20:12:09
 */
@Service
public class LabelServiceImpl extends ServiceImpl<LabelMapper, Label> implements LabelService {
    @Autowired
    LabelHelperService labelHelperService;

    /**
     * 根据HelperId和LabelId删除指定label,同时,只要该label没有helper则删除该label
     */
    public Boolean delLabelByHelperIdAndLabelId(Long helperId, Long labelId) {
        LambdaQueryWrapper<LabelHelper> lqwLH = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<LabelHelper> lqwLH2 = new LambdaQueryWrapper<>();
        if (helperId != null && labelId != null) {
            lqwLH.eq(LabelHelper::getLabelId, labelId);
            lqwLH.eq(LabelHelper::getHelperId, helperId);
            labelHelperService.remove(lqwLH);
            lqwLH2.eq(LabelHelper::getLabelId, labelId);
            List<LabelHelper> labelHelperList = labelHelperService.list(lqwLH2);
            if (labelHelperList.size() == 0) {
                this.removeById(labelId);
            }
            return true;
        }
        return false;
    }

    /**
     * 根据helperId添加label,如果label存在则添加此label,同一个helper不能有重复一个label
     */
    public Boolean saveLabelByHelperId(Long helperId, Label label) {
        LambdaQueryWrapper<LabelHelper> lqw = new LambdaQueryWrapper<>();
        Long labelId = label.getId();
        if (labelId == null) {
            boolean flagL = this.save(label);
            if (!flagL) {
                return false;
            }
        }
        labelId = label.getId();
        boolean flagLH = labelHelperService.save(new LabelHelper(labelId, helperId));
        return flagLH;
    }
}
