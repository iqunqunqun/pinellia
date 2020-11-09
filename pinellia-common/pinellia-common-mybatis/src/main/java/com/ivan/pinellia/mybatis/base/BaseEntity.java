package com.ivan.pinellia.mybatis.base;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>基础实体类</p>
 *
 * @author chen
 * @className BaseEntity
 * @since 2020/5/4 21:07
 */

@Data
public class BaseEntity implements Serializable {
    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;
}
