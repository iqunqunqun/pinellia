package com.ivan.pinellia.config;

import cn.hutool.core.lang.UUID;
import org.activiti.engine.impl.cfg.IdGenerator;
import org.springframework.stereotype.Component;

/**
 * <p></p>
 *
 * @author chenyf
 * @className UUIDGenerator
 * @since 2020/2/27 17:41
 */
@Component
public class UUIDGenerator implements IdGenerator {
    @Override
    public String getNextId() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}