package com.ivan.pinellia.mybatis.base;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * <p>基础业务实现类</p>
 *
 * @author chen
 * @className BaseServiceImpl
 * @since 2020/5/4 21:10
 */
@Validated
public class BaseServiceImpl<M extends BaseMapper<T>, T extends BaseEntity> extends ServiceImpl<M, T> implements BaseService<T> {


    @Override
    public boolean deleteLogic(@NotEmpty List<Integer> ids) {
        return super.removeByIds(ids);
    }

}
