package cn.jt.controller;

import cn.jt.common.R;
import cn.jt.common.controller.BaseController;
import cn.jt.dto.HelperDto;
import cn.jt.entity.Helper;
import cn.jt.entity.Label;
import cn.jt.entity.LabelHelper;
import cn.jt.service.HelperService;
import cn.jt.service.LabelHelperService;
import cn.jt.service.LabelService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author JunTian
 * @since 2023-05-24 20:12:09
 */
@RestController
@RequestMapping("/label")
@Tag(name = "标签", description = "标签控制器")
public class LabelController extends BaseController {

    @Autowired
    HelperService helperService;

    @Autowired
    private LabelService labelService;

    @Autowired
    LabelHelperService labelHelperService;

    /**
     * 查询标签
     */
    @Operation(summary = "查询标签")
    @GetMapping()
    public R<List<Label>> get() {
        return success(labelService.list());
    }

    /**
     * 根据labelId查所有helper
     */
    @Operation(summary = "根据标签查所有helper")
    @Parameters({
            @Parameter(name = "labelId", description = "labelId", required = true, example = "1666374568922853377"),
            @Parameter(name = "current", description = "当前页", required = true, example = "1"),
            @Parameter(name = "size", description = "每页条数", required = true, example = "10"),
            @Parameter(name = "notebook", description = "内容"),
            @Parameter(name = "sign", description = "是否星标"),
            @Parameter(name = "data", description = "数据", hidden = true),
    })
    @GetMapping("/getPageByHelperId")
    public R<IPage<HelperDto>> getPageByHelperId(Long labelId, Helper data) {
        IPage<Helper> page = super.getPage();
        List<HelperDto> pageByHelperId = helperService.getPageByHelperId(labelId, page, data);
        IPage<HelperDto> helperDtoIPage = new Page<>();
        helperDtoIPage.setRecords(pageByHelperId).setCurrent(page.getCurrent()).setSize(page.getSize()).setTotal(helperDtoIPage.getSize());
        return R.success(helperDtoIPage);
    }

    /**
     * 根据HelperId和LabelId删除指定label,同时,只要该label没有helper则删除该label
     */
    @Operation(summary = "根据HelperId和LabelId删除指定label")
    @DeleteMapping("/delLabelByHelperIdAndLabelId")
    public R<String> delLabelByHelperIdAndLabelId(Long helperId, Long labelId) {
        Boolean flag = labelService.delLabelByHelperIdAndLabelId(helperId, labelId);
        return flag ? R.success("删除成功") : R.fail("删除失败");
    }

    /**
     * 根据helperId添加label
     */
    @Operation(summary = "根据helperId添加label")
    @PostMapping("/saveLabelByHelperId")
    public R<Label> saveLabelByHelperId(Long helperId, @RequestBody Label label) {
        Boolean flag = false;
        LambdaQueryWrapper<LabelHelper> lqw = new LambdaQueryWrapper<>();
        if (helperId != null && label != null) {
            lqw.eq(LabelHelper::getHelperId, helperId);
            List<LabelHelper> labelHelperList = labelHelperService.list(lqw);
            if (labelHelperList.size() >= 3) {
                return R.fail("最多可以添加三个标签!");
            }
            for (LabelHelper labelHelper : labelHelperList) {
                if (labelHelper.getLabelId().equals(label.getId())) {
                    return R.success(label);
                }
            }
            flag = labelService.saveLabelByHelperId(helperId, label);
        }
        return flag ? R.success(label) : R.fail("添加失败");
    }

}
