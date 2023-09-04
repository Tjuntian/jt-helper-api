package cn.jt;

import cn.jt.utils.CheckCodeUtil;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

//@SpringBootTest
public class CheckCodeUtilsTest {

    /**
     * 输出图片,指定长度,最常用
     *
     * @throws IOException
     */
    @Test
    void Test04() throws IOException {
        OutputStream fs = new FileOutputStream(new File("D:\\image.png"));
        String verifyCode = CheckCodeUtil.outputVerifyImage(100,40,new File("D:\\image.png"),5);
        System.out.println(verifyCode);
    }

    /**
     * 生成验证码
     */
    @Test
    void Test01() {
        String verifyCode = CheckCodeUtil.generateVerifyCode(4);
        System.out.println(verifyCode);
    }

    /**
     * 生成验证码,并且指定字符串范围
     */
    @Test
    void Test02() {
        String verifyCode = CheckCodeUtil.generateVerifyCode(5, CheckCodeUtil.VERIFY_CODES);
        System.out.println(verifyCode);
    }

    /**
     * 指定验证码输出图片,指定宽,高,输出文件地址,码值
     */
    @Test
    void Test03() throws IOException {
        String verifyCode = CheckCodeUtil.generateVerifyCode(4);
        CheckCodeUtil.outputImage(80, 40, new File("D:\\JunTian\\Project\\jt-helper\\jt-helper-front\\src\\assets\\images\\checkCode.png"), verifyCode);
        System.out.println(verifyCode);
    }



}
