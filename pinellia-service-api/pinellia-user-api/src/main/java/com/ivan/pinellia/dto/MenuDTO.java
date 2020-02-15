package com.ivan.pinellia.dto;


import lombok.Data;

import java.io.Serializable;


/**
 * @author chenyf
 * @program: pinellia
 * @description: 数据传输对象实体类
 * @create 2020-01-14 10:31 下午
 */

@Data
public class MenuDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String alias;
    private String path;
}
