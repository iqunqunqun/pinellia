package com.ivan.pinellia.mybatis.base;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ivan.pinellia.tool.constant.PinelliaConstant;
import com.ivan.pinellia.tool.utils.DateUtil;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * 业务封装基础类
 *
 * @param <M> mapper
 * @param <T> model
 * @author Chill
 */
@Validated
public class BaseServiceImpl<M extends BaseMapper<T>, T extends BaseEntity> extends ServiceImpl<M, T> implements BaseService<T> {

/*    @Override
    public boolean save(T entity) {
        PinelliaLoginUser user = SecureUtil.getUser();
        if (user != null) {
            entity.setCreateUser(user.getId());
            entity.setUpdateUser(user.getId());
        }
        LocalDateTime now = DateUtil.nowForTime();
        entity.setCreateTime(now);
        entity.setUpdateTime(now);
        if (entity.getStatus() == null) {
            entity.setStatus(PinelliaConstant.DB_STATUS_NORMAL);
        }
        entity.setIsDeleted(PinelliaConstant.DB_NOT_DELETED);
        return super.save(entity);
    }

    @Override
    public boolean updateById(T entity) {
        PinelliaLoginUser user = SecureUtil.getUser();
        if (user != null) {
            entity.setUpdateUser(user.getId());
        }
        entity.setUpdateTime(DateUtil.nowForTime());
        return super.updateById(entity);
    }*/

    @Override
    public boolean deleteLogic(@NotEmpty List<Integer> ids) {
        return super.removeByIds(ids);
    }

}
