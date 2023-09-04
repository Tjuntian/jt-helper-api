package cn.jt.controller;

import cn.jt.common.R;
import cn.jt.common.controller.BaseController;
import cn.jt.dto.LoginDTO;
import cn.jt.dto.loginParamDTO;
import cn.jt.service.ValidateCodeService;
import cn.jt.service.impl.LoginServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/anno")
@Tag(name = "登录")
public class LoginController extends BaseController {
    @Autowired
    private ValidateCodeService validateCodeService;

    @Autowired
    LoginServiceImpl loginServiceImpl;

    @GetMapping(value = "/captcha", produces = "image/png")
    @Operation(summary = "验证码", description = "生成验证码")
    @Parameters({
            @Parameter(name = "key", description = "本机器唯一标识", required = true, example = "juntian"),
            @Parameter(name = "response", hidden = true)
    })
    public void captcha(@RequestParam(value = "key") String key, HttpServletResponse response) throws IOException {
        validateCodeService.create(key, response);
    }

    @PostMapping("/login")
    @Operation(summary = "登录", description = "登录认证")
    @Parameters({
            @Parameter(schema = @Schema(name = "account"), name = "account", description = "用户名", required = true, example = "123"),
            @Parameter(name = "password", description = "密码", required = true, example = "qwe"),
            @Parameter(name = "key", description = "本机机器码", required = true, example = "juntian"),
            @Parameter(name = "code", description = "验证码", required = true, example = "")
    })
    public R<LoginDTO> login(@Validated @RequestBody loginParamDTO loginParamDTO) {
        //校验验证码是否正确
        boolean check = validateCodeService.check(loginParamDTO.getKey(), loginParamDTO.getCode());
        if (check) {
            //验证码校验通过，执行具体的登录认证逻辑
            return loginServiceImpl.login(loginParamDTO.getAccount(), loginParamDTO.getPassword());
        }
        //验证码校验不通过，直接返回
        return this.success(null);
    }


    //校验验证码
    @PostMapping("/check")
    @Operation(summary = "校验验证码")
    @Parameters({
            @Parameter(name = "code", description = "验证码", required = true),
            @Parameter(name = "key", description = "唯一机器码", required = true,example = "juntian")
    })
    public boolean check(@RequestBody loginParamDTO loginParamDTO) {
        //校验验证码是否正确
        return validateCodeService.check(loginParamDTO.getKey(), loginParamDTO.getCode());
    }
}
