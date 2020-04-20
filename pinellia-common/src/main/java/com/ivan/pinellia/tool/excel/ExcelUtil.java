package com.ivan.pinellia.tool.excel;

import cn.hutool.poi.excel.ExcelFileUtil;
import com.alibaba.excel.EasyExcel;
import com.ivan.pinellia.tool.api.R;
import com.ivan.pinellia.tool.api.ResultCode;
import com.ivan.pinellia.tool.constant.PinelliaConstant;
import com.ivan.pinellia.tool.utils.StringPool;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * <p>Excel工具類</p>
 *
 * @author user
 * @className ExcelUtil
 * @since 2020/2/22 12:42 下午
 */

@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExcelUtil<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelUtil.class);

    private static final String CONTENT_DISPOSITION_HEADER = "Content-disposition";

    private static final String FILE_NAME_HEADER = "attachment;filename=";

    private static final String SHEET_ONE = "sheet1";

    private String fileName;

    private Class clazz;

    private List<T> data;

    public ExcelUtil(Class clazz) {
        this.clazz = clazz;
    }

    /**
     * 写Excel文件
     * @param response
     * @param excelParam
     */
    @SneakyThrows
    public static void write(HttpServletResponse response, ExcelParam excelParam) {
        try {
            response.setContentType(PinelliaConstant.EXCEL_TYPE);
            response.setCharacterEncoding(StringPool.UTF_8);
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = URLEncoder.encode(excelParam.getFileName(), StringPool.UTF_8);
            response.setHeader(CONTENT_DISPOSITION_HEADER, FILE_NAME_HEADER + fileName + StringPool.XLSX);
            EasyExcel.write(response.getOutputStream(), excelParam.getClazz()).autoCloseStream(Boolean.FALSE).sheet(SHEET_ONE).doWrite(excelParam.getDataList());
        } catch (Exception e) {
            LOGGER.error("错误--{}", e.getMessage());
            // 重置response
            response.reset();
            response.setContentType(PinelliaConstant.CONTENT_TYPE_NAME);
            response.setCharacterEncoding(StringPool.UTF_8);
            response.getWriter().println(R.fail(ResultCode.FAILURE));
        }

    }

    /**
     * 读取Excel文件内容
     * @param file Excel文件
     * @param clazz 读取Excel必要的参数
     * @return
     */
    public static <T> List<T> read(MultipartFile file, Class<?> clazz) {
        ExcelDataListener<T> listener = new ExcelDataListener<>();
        ExcelParam param = ExcelParam.builder().clazz(clazz).build();
        return ExcelUtil.read(file, param, listener);
    }

    /**
     * 读取Excel文件内容
     * @param file Excel文件
     * @param param 读取Excel必要的参数
     * @return
     */
    public static <T> List<T> read(MultipartFile file, ExcelParam param) {
        ExcelDataListener<T> listener = new ExcelDataListener<>();
        return ExcelUtil.read(file, param, listener);
    }



    /**
     * 读取Excel文件
     * @param file
     * @param param
     * @param listener
     * @return
     */
    @SneakyThrows
    public static <T> List<T> read(MultipartFile file, ExcelParam param, ExcelDataListener<T> listener) {
        InputStream inputStream = file.getInputStream();
        EasyExcel.read(inputStream, param.getClazz(), listener)
                .sheet()
                .autoTrim(true)
                .headRowNumber(param.getHeadRowNumber() == null? 1: param.getHeadRowNumber())
                .doRead();
        return listener.getDataList();
    }


    /**
     * 验证是否是Excel文件
     * @param file
     * @return
     */
    @SneakyThrows
    public boolean isExcel(MultipartFile file) {
        InputStream inputStream = file.getInputStream();
        return ExcelFileUtil.isXlsx(inputStream);
    }


}
