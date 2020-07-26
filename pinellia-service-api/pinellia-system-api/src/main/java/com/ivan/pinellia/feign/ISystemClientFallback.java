package com.ivan.pinellia.feign;

import com.ivan.pinellia.core.tool.api.R;
import com.ivan.pinellia.entity.Role;

/**
 * <p></p>
 *
 * @author ivan
 * @className ISystemClientFallback
 * @since 2020/7/26 16:38
 */
public class ISystemClientFallback implements ISystemClient {
    @Override
    public R submit(Role role) {
        return null;
    }
}
