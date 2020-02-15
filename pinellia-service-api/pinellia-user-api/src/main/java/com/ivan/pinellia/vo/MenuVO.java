package com.ivan.pinellia.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ivan.pinellia.entity.Menu;
import com.ivan.pinellia.tool.node.INode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenyf
 * @program: pinellia
 * @description: 菜单视图对象
 * @create 2019-12-29 10:56 上午
 */
@ApiModel("菜单视图对象")
@Data
@EqualsAndHashCode(callSuper = true)
public class MenuVO extends Menu implements INode {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @ApiModelProperty("主键ID")
    private Integer id;


    /**
     * 子孙节点
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<INode> children;

    @Override
    public List<INode> getChildren() {
        if (this.children == null) {
            this.children = new ArrayList<>();
        }
        return this.children;
    }

    /**
     * 上级菜单
     */
    @ApiModelProperty("上级菜单")
    private String parentName;

    /**
     * 菜单类型
     */
    @ApiModelProperty("菜单类型")
    private String categoryName;

    /**
     * 按钮功能
     */
    @ApiModelProperty("按钮功能")
    private String actionName;

    /**
     * 是否新窗口打开
     */
    @ApiModelProperty("是否新窗口打开")
    private String isOpenName;
}
