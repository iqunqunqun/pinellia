package com.ivan.pinellia.feign;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ivan.pinellia.core.tool.api.R;
import com.ivan.pinellia.entity.User;
import com.ivan.pinellia.vo.UserVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p></p>
 *
 * @author chenyf
 * @className IUserClient
 * @since 2020/10/30 13:49
 */

@FeignClient("user-service")
public interface IUserClient {

    String USER_SERVICE_PREFIX = "/user";

    /**
     * 查询用户详情
     * @param id
     * @return
     */
    @GetMapping(USER_SERVICE_PREFIX + "/detail/{id}")
    R<UserVO> detail(@PathVariable("id") Integer id);

    /**
     * 查询用户信息
     * @param userName
     * @return
     */
    @ApiOperation(value = "查看详情", notes = "传入id")
    @GetMapping(USER_SERVICE_PREFIX + "/userInfo")
    R<UserVO> userInfo(@RequestParam("userName") String userName);
}
