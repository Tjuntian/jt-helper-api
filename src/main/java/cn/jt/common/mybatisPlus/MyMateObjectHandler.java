package cn.jt.common.mybatisPlus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * mybatis-plus
 * 公共字段自动填充
 */
@Component
public class MyMateObjectHandler implements MetaObjectHandler {
    //插入时自动填充
    @Override
    public void insertFill(MetaObject metaObject) {
        if (metaObject.hasGetter("createTime")) {
            metaObject.setValue("createTime", LocalDateTime.now());
        }

        if (metaObject.hasGetter("updateTime")) {
            metaObject.setValue("updateTime", LocalDateTime.now());
        }
    }

    //修改时自动填充
    @Override
    public void updateFill(MetaObject metaObject) {
        if (metaObject.hasGetter("updateTime")) {
            metaObject.setValue("updateTime", LocalDateTime.now());
        }
    }
}
