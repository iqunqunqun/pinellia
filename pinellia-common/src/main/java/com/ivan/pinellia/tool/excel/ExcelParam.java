package com.ivan.pinellia.tool.excel;

import lombok.*;

import java.util.List;

/**
 * <p></p>
 *
 * @author chen
 * @className ExcelParam
 * @since 2020/4/19 9:26
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class ExcelParam {
    /**
     * 文件名
     */
    private String fileName;

    /**
     * 类型
     */
    private Class<?> clazz;

    /**
     * 数据
     */
    private List<?> dataList;

    /**
     * 行头数
     */
    @Builder.Default
    private Integer headRowNumber = 1;

    public ExcelParam(String fileName, Class<?> clazz, List<Object> dataList) {
        this.fileName = fileName;
        this.clazz = clazz;
        this.dataList = dataList;
    }

    public static ExcelParam build() {
        return new ExcelParam();
    }
}
