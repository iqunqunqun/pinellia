package com.ivan.pinellia.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.ivan.pinellia.mybatis.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * <p>
 * 字典表
 * </p>
 *
 * @author ivan
 * @since 2020-01-12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("pinellia_dict")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Dict extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "dict_id", type = IdType.AUTO)
    private Integer dictId;

    /**
     * 父主键
     */
    @TableField("parent_id")
    private Integer parentId;

    /**
     * 字典码
     */
    @TableField("code")
    private String code;

    /**
     * 字典值
     */
    @TableField("dict_key")
    private Integer dictKey;

    /**
     * 字典名称
     */
    @TableField("dict_value")
    private String dictValue;

    /**
     * 排序
     */
    @TableField("sort")
    private Integer sort;

    /**
     * 字典备注
     */
    @TableField("remark")
    private String remark;

    /**
     * 状态[0:未删除,1:删除]
     */
    @TableLogic
    @ApiModelProperty(value = "是否已删除")
    private Integer isDeleted;
}
