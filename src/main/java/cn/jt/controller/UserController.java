package cn.jt.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cn.jt.common.controller.BaseController;

/**
 * <p>
 * 用户信息 前端控制器
 * </p>
 *
 * @author JunTian
 * @since 2023-05-23 23:10:38
 */
@RestController
@RequestMapping("/user")
@Tag(name = "用户控制器")
public class UserController extends BaseController {

}
