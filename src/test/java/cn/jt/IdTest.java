package cn.jt;

import cn.jt.utils.id.CodeGenerate;
import cn.jt.utils.id.idUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

//id生成器测试
//@SpringBootTest
@Slf4j
public class IdTest {

    @Test
    void SnowflakeIdGenerateTest() {
        long machineCode = 1;//这台机器最大分布式32台机器
        idUtils idGenerateSnowflake=new idUtils(machineCode);
        Long aLong = idGenerateSnowflake.generate();
        log.info(aLong.toString());
        CodeGenerate codeGenerate = new CodeGenerate(1);
        String next = codeGenerate.next();
        log.info(next);
    }
}
