package cn.jt.service.impl;

import cn.jt.dto.HelperDto;
import cn.jt.entity.Helper;
import cn.jt.entity.Label;
import cn.jt.mapper.HelperMapper;
import cn.jt.service.HelperService;
import cn.jt.utils.DozerUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 助手 服务实现类
 * </p>
 *
 * @author JunTian
 * @since 2023-05-23 14:59:09
 */
@Service
public class HelperServiceImpl extends ServiceImpl<HelperMapper, Helper> implements HelperService {

    @Autowired
    private HelperMapper helperMapper;

    @Autowired
    private DozerUtils dozerUtils;

    @Override
    public IPage<HelperDto> helperPage(IPage<Helper> page, Helper data) {
        LambdaQueryWrapper<Helper> query = new LambdaQueryWrapper<>();
        query.like(data.getNotebook() != null, Helper::getNotebook, data.getNotebook())
                .eq(data.getSign() != null, Helper::getSign, data.getSign())
                .orderByDesc(Helper::getUpdateTime);
        this.page(page, query);
        //解决有数据,但是由于页数大于数据数时返回空的情况,前端发送请求后应该根据返回结果调整当前页和每页多少条
        while (page.getTotal() > 0 && page.getRecords().size() == 0 && page.getCurrent() > 1) {
            page.setCurrent(page.getCurrent() - 1);
            this.page(page, query);
        }
        List<Helper> helperList = page.getRecords();
        List<HelperDto> helperDtoList;
        helperDtoList = helperList.stream().map(item -> {
            List<Label> labelList = helperMapper.getByHelperId(item.getId());
            HelperDto helperDto = new HelperDto()
                    .setId(item.getId())
                    .setSign(item.getSign())
                    .setNotebook(item.getNotebook())
                    .setLabelList(labelList)
                    .setUpdateTime(item.getUpdateTime());
            return helperDto;
        }).collect(Collectors.toList());
        IPage<HelperDto> helperDtoPage = new Page<>();
        BeanUtils.copyProperties(page, helperDtoPage, "records");
        helperDtoPage.setRecords(helperDtoList);
        return helperDtoPage;
    }

    @Override
    public HelperDto helperOne(Long id) {
        Helper helper = helperMapper.selectById(id);
        List<Label> labelList = helperMapper.getByHelperId(id);
        HelperDto helperDto = new HelperDto().setId(helper.getId())
                .setSign(helper.getSign())
                .setNotebook(helper.getNotebook())
                .setLabelList(labelList)
                .setUpdateTime(helper.getUpdateTime());
        return helperDto;
    }

    @Override
    public List<HelperDto> getPageByHelperId(Long labelId, IPage<Helper> page, Helper data) {
        List<Helper> helperList = helperMapper.getPageByHelperId(labelId, page.getCurrent() - 1L, page.getSize(), data);
        List<HelperDto> helperDtoList = helperList.stream().map(item -> {
            //根据helperId查所有label
            Long helperId = item.getId();
            HelperDto helperDto = this.helperOne(helperId);
            return helperDto;
        }).toList();
        return helperDtoList;
    }
}
