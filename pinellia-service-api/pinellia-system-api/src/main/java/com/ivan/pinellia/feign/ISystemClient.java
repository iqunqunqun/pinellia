package com.ivan.pinellia.feign;

import com.ivan.pinellia.tool.api.R;
import com.ivan.pinellia.entity.Role;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * <p></p>
 *
 * @author ivan
 * @className ISystemClient
 * @since 2020/7/26 16:16
 */

@FeignClient(value = "system-service", fallback = ISystemClientFallback.class)
public interface ISystemClient {

    String ROLE_API_PREFIX = "/role";

    /**
     * 编辑或新增角色
     * @param role
     * @return
     */
    @PostMapping(ROLE_API_PREFIX + "/submit")
    R submit(@RequestBody Role role);

}
