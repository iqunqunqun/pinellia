package com.ivan.pinellia.tool.utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.property.ExcelHeadProperty;
import com.ivan.pinellia.mybatis.base.BaseService;
import com.ivan.pinellia.tool.api.IResultCode;
import com.ivan.pinellia.tool.api.R;
import com.ivan.pinellia.tool.api.ResultCode;
import com.ivan.pinellia.tool.constant.PinelliaConstant;
import com.ivan.pinellia.tool.excel.ExcelDataListener;
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

    @SneakyThrows
    public void write(HttpServletResponse response, ExcelUtil<?> excelUtil) {
        try {
            response.setContentType(PinelliaConstant.EXCEL_TYPE);
            response.setCharacterEncoding(StringPool.UTF_8);
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = URLEncoder.encode(excelUtil.getFileName(), StringPool.UTF_8);
            response.setHeader(CONTENT_DISPOSITION_HEADER, FILE_NAME_HEADER + fileName + StringPool.XLSX);
            EasyExcel.write(response.getOutputStream(), excelUtil.getClazz()).autoCloseStream(Boolean.FALSE).sheet(SHEET_ONE).doWrite(excelUtil.getData());
        } catch (Exception e) {
            LOGGER.error("错误--{}", e.getMessage());
            // 重置response
            response.reset();
            response.setContentType(PinelliaConstant.CONTENT_TYPE_NAME);
            response.setCharacterEncoding(StringPool.UTF_8);
            response.getWriter().println(R.fail(ResultCode.FAILURE));
        }

    }

    @SneakyThrows
    public List<T> read(MultipartFile file, ExcelUtil<T> excelUtil, ExcelDataListener<T> listener) {
        InputStream inputStream = file.getInputStream();
        EasyExcel.read(inputStream, excelUtil.getClazz(), listener).sheet().doRead();
        List<T> dataList = listener.getDataList();
        System.out.println("dataList = " + dataList);
        return dataList;
    }


}
