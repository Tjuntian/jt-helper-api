package cn.jt;

import cn.jt.utils.TXCosUtil;
import com.qcloud.cos.model.COSObjectInputStream;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

//@SpringBootTest
@Slf4j
public class CosTest {

    /**
     * 下载
     *
     * @throws IOException
     */
    @Test
    void test1() throws IOException {
        String key = "/coupon/11223344556.png";
        String path = "D:\\cosFile\\";
        COSObjectInputStream inputStream = TXCosUtil.downloadImage(key);
        File file = new File(path + "123.png");
        OutputStream outputStream = new FileOutputStream(file);
        int len = 0;
        byte[] bytes = new byte[1024];
        while ((len = inputStream.read(bytes)) != -1) {
            outputStream.write(bytes, 0, len);
            outputStream.flush();
        }
    }

    /**
     * 上传文件
     */
    @Test
    void test2() {
        String path = "D:\\cosFile\\123.png";
        File file = new File(path);
        if (file.isFile()) {
            String s = TXCosUtil.uploadFile(file, "/coupon/123456.png");
            log.info(s);
        }

    }

    /**
     * 获取url
     */
    @Test
    void test3() {
        TXCosUtil.getUrl("/coupon/11223344556.png");
    }


}
