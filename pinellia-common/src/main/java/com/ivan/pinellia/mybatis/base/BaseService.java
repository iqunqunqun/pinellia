package com.ivan.pinellia.mybatis.base;

import com.baomidou.mybatisplus.extension.service.IService;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * <p>基础业务类</p>
 *
 * @author chen
 * @className BaseService
 * @since 2020/5/4 21:09
 */
public interface BaseService<T> extends IService<T> {

    /**
     * 逻辑删除
     *
     * @param ids id集合(逗号分隔)
     * @return boolean
     */
    boolean deleteLogic(@NotEmpty List<Integer> ids);

}
