package fun.moblog.blog.common.mybatisPlus.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {


    @Override
    public void insertFill(MetaObject metaObject) {
        boolean createTime = metaObject.hasSetter("createTime");
        if (createTime) {
            this.setInsertFieldValByName("createTime", LocalDateTime.now(), metaObject);
        }
        boolean updateTime = metaObject.hasSetter("updateTime");
        if (updateTime) {
            this.setInsertFieldValByName("updateTime", LocalDateTime.now(), metaObject);
        }
        boolean isDel = metaObject.hasSetter("isDel");
        if (isDel) {
            this.setInsertFieldValByName("isDel", 0, metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        boolean updateTime = metaObject.hasSetter("updateTime");
        if (updateTime) {
            this.setInsertFieldValByName("updateTime", LocalDateTime.now(), metaObject);
        }
    }


}