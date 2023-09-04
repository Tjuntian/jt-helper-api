package cn.jt;

import cn.jt.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Date;

//@SpringBootTest
@Slf4j
public class DateUtilsTest {
    @Test
    void formatTest() {
        Date date = DateUtils.parseAsDateTime("2018-03-12 12:24:52");
        log.info(date.toString());
        String dateBack = DateUtils.format(date, DateUtils.DEFAULT_DATE_FORMAT_EN);
        String dateBack1= DateUtils.format(date, DateUtils.DEFAULT_TIME_FORMAT);
        log.info(dateBack);
        log.info(dateBack1);
    }

}
