package cn.jt.common.controller;

import cn.hutool.core.util.StrUtil;
import cn.jt.common.R;
import cn.jt.common.context.BaseContextHandler;
import cn.jt.common.exception.BizException;
import cn.jt.common.exception.code.BaseExceptionCode;
import cn.jt.utils.NumberUtils;
import cn.jt.utils.other.AntiSqlFilter;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static cn.jt.utils.DateUtils.DEFAULT_DATE_TIME_FORMAT;

/**
 * SuperController
 *
 */
public abstract class BaseController {
    @Resource
    protected HttpServletRequest request;
    @Resource
    protected HttpServletResponse response;
    /**
     * 当前页
     */
    protected static final String CURRENT = "current";
    /**
     * 每页显示条数
     */
    protected static final String SIZE = "size";

    /**
     * 默认每页条目20,最大条目数100
     */
    int DEFAULT_LIMIT = 10;
    int MAX_LIMIT = 10000;

    /**
     * 成功返回
     * @param data
     * @return R
     * @param <T>
     */
    public <T> R<T> success(T data) {
        return R.success(data);
    }

    public R<Boolean> success() {
        return R.success();
    }

    /**
     * 失败返回
     *
     * @param msg
     * @return
     */
    public <T> R<T> fail(String msg) {
        return R.fail(msg);
    }

    public <T> R<T> fail(String msg, Object... args) {
        return R.fail(msg, args);
    }

    /**
     * 失败返回
     *
     * @param code
     * @param msg
     * @return
     */
    public <T> R<T> fail(int code, String msg) {
        return R.fail(code, msg);
    }

    public <T> R<T> fail(BaseExceptionCode exceptionCode) {
        return R.fail(exceptionCode);
    }

    public <T> R<T> fail(BizException exception) {
        return R.fail(exception);
    }

    public <T> R<T> fail(Throwable throwable) {
        return R.fail(throwable);
    }

    public <T> R<T> validFail(String msg) {
        return R.validFail(msg);
    }

    public <T> R<T> validFail(String msg, Object... args) {
        return R.validFail(msg, args);
    }

    public <T> R<T> validFail(BaseExceptionCode exceptionCode) {
        return R.validFail(exceptionCode);
    }

    /**
     * 获取当前用户id
     */
    protected Long getUserId() {
        return BaseContextHandler.getUserId();
    }

    protected String getAccount() {
        return BaseContextHandler.getAccount();
    }

    protected String getName() {
        return BaseContextHandler.getName();
    }

    /**
     * 获取分页对象
     *
     * @return
     */
    protected <T> Page<T> getPage() {
        return getPage(false);
    }

    protected Integer getPageNo() {
        return NumberUtils.intValueOf(request.getParameter(CURRENT), 1);
    }

    protected Integer getPageSize() {
        return NumberUtils.intValueOf(request.getParameter(SIZE), DEFAULT_LIMIT);
    }

    /**
     * 获取分页对象
     *
     * @param openSort
     * @return
     */
    protected <T> Page<T> getPage(boolean openSort) {
        // 页数
        Integer pageNo = getPageNo();
        // 分页大小
        Integer pageSize = getPageSize();
        // 是否查询分页
        return buildPage(pageNo, pageSize);
    }

    private <T> Page<T> buildPage(long pageNo, long pageSize) {
        // 是否查询分页
        pageSize = pageSize > MAX_LIMIT ? MAX_LIMIT : pageSize;
        return new Page<>(pageNo, pageSize);
    }

    /**
     * 获取安全参数(SQL ORDER BY 过滤)
     *
     * @param parameter
     * @return
     */
    protected String[] getParameterSafeValues(String parameter) {
        return AntiSqlFilter.getSafeValues(request.getParameterValues(parameter));
    }

    private LocalDateTime getLocalDateTime(String endCreateTime) {
        String startCreateTime = request.getParameter(endCreateTime);
        if (StrUtil.isBlank(startCreateTime)) {
            return null;
        }
        String safeValue = AntiSqlFilter.getSafeValue(startCreateTime);
        if (StrUtil.isBlank(safeValue)) {
            return null;
        }
        return LocalDateTime.parse(safeValue, DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_FORMAT));
    }
}
