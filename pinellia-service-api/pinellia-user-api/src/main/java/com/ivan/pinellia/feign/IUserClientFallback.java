package com.ivan.pinellia.feign;

import com.ivan.pinellia.core.tool.api.R;
import com.ivan.pinellia.vo.UserVO;

/**
 * <p></p>
 *
 * @author chenyf
 * @className IUserClientFallback
 * @since 2020/10/30 13:49
 */
public class IUserClientFallback implements IUserClient {
    @Override
    public R<UserVO> detail(Integer id) {
        return null;
    }

    @Override
    public R<UserVO> userInfo(String userName) {
        return null;
    }
}
