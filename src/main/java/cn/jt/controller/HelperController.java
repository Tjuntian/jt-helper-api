package cn.jt.controller;

import cn.jt.common.R;
import cn.jt.common.controller.BaseController;
import cn.jt.common.entity.SuperEntity;
import cn.jt.dto.HelperDto;
import cn.jt.dto.HelperSaveDto;
import cn.jt.dto.HelperUpdateDto;
import cn.jt.entity.Helper;
import cn.jt.service.HelperService;
import cn.jt.utils.DozerUtils;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 助手 前端控制器
 * </p>
 *
 * @author JunTian
 * @since 2023-05-23 09:07:52
 */
@RestController
@RequestMapping("/helper")
@Tag(name = "核心", description = "核心控制器")
public class HelperController extends BaseController {

    @Autowired
    private HelperService helperService;

    @Autowired
    private DozerUtils dozer;

    @PostMapping("test")
    public R test() {
        String fileName = "D:\\file.txt"; // TXT文件的路径和名称
        List<HelperSaveDto> helperSaveDtoList = new ArrayList<>();
        List<String> lines = new ArrayList<>(); // 创建一个List用于保存每一行文本
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) { // 每次读取一行文本
                lines.add(line); // 将该行文本保存到List中
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 输出List中的元素
        for (String line : lines) {
            HelperSaveDto helperSaveDto = new HelperSaveDto();
            helperSaveDto.setNotebook(line);
            this.save(helperSaveDto);
        }
        return R.success();
    }


    /**
     * 新增笔记
     */
    @PostMapping
    @Operation(summary = "新增笔记")
    public R<Helper> save(@RequestBody @Validated HelperSaveDto data) {

        Helper helper = dozer.map(data, Helper.class);
        helperService.save(helper);
        return success(helper);
    }

    /**
     * 删除笔记
     */
    @DeleteMapping
    @Operation(summary = "删除笔记")
    @Parameters({
            @Parameter(name = "ids", required = true, example = "1661005338689953794")
    })
    public R<Boolean> delete(@RequestParam("ids") List<Long> ids) {
        helperService.removeByIds(ids);
        return success(true);
    }

    /**
     * 分页查询笔记
     */
    @Operation(summary = "分页查询笔记")
    @Parameters({
            @Parameter(name = "current", description = "当前页", required = true, example = "1"),
            @Parameter(name = "size", description = "每页条数", required = true, example = "10"),
            @Parameter(name = "notebook", description = "内容"),
            @Parameter(name = "sign", description = "是否星标"),
            @Parameter(name = "data", description = "数据", hidden = true),
    })
    @GetMapping("/page")
    public R<IPage<HelperDto>> page(Helper data) {
        IPage<Helper> page = getPage();
        IPage<HelperDto> helperDtoPage = helperService.helperPage(page, data);
        return success(helperDtoPage);
    }

    /**
     * 查询笔记
     */
    @Operation(summary = "查询笔记")
    @GetMapping("/{id}")
    public R<HelperDto> get(@PathVariable Long id) {
        return success(helperService.helperOne(id));
    }


    /**
     * 修改笔记
     */
    @Operation(summary = "修改笔记")
    @PutMapping
    public R<Helper> update(@RequestBody @Validated(SuperEntity.Update.class) HelperUpdateDto data) {
        Helper helper = dozer.map(data, Helper.class);
        helperService.updateById(helper);
        return success(helper);
    }
}