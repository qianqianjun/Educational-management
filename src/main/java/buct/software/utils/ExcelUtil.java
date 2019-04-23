package buct.software.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Workbook;
import java.io.File;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;


import static org.apache.poi.ss.usermodel.CellType.*;

/**
 * 类名：
 * 功能：导入导出
 */
@Slf4j
public class ExcelUtil {


    /**
     * 方法名：setBrowser
     * 功能：使用浏览器下载
     * 描述：
     * 创建人：wyl
     */
//    private static void setBrowser(HttpServletResponse response, HSSFWorkbook workbook, String fileName) {
//        try {
//            //清空response
//            response.reset();
//            //设置response的Header
//            response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
//            OutputStream os = new BufferedOutputStream(response.getOutputStream());
//            response.setContentType("application/vnd.ms-excel;charset=gb2312");
//            //将excel写入到输出流中
//            workbook.write(os);
//            os.flush();
//            os.close();
//            log.info("设置浏览器下载成功！");
//        } catch (Exception e) {
//            log.info("设置浏览器下载失败！");
//            e.printStackTrace();
//        }
//
//    }


    /**
     * 方法名：importExcel
     * 功能：导入某学期某课程所有学生的成绩到数据库
     * 创建人：wyl
     */
    public static List<Object[]> importExcel(File file) {
        log.info("导入解析开始");
        try {
            //String encoding = "GBK";
            File excel = file;
            List<Object[]> list = new ArrayList<>();
            String[] split = excel.getName().split("\\.");  //.是特殊字符，需要转义！！！！！
            Workbook wb;
            //根据文件后缀（xls/xlsx）进行判断
            FileInputStream fis = new FileInputStream(excel);   //文件流对象
            if ( "xls".equals(split[1])){
                wb = new HSSFWorkbook(fis);
            }else if ("xlsx".equals(split[1])){
                wb = new XSSFWorkbook(fis);
            }else {
                System.out.println("文件类型错误!");
                return null;
            }

                //开始解析
                Sheet sheet = wb.getSheetAt(0);     //读取sheet 0
                //获取sheet的行数
                int rows = sheet.getPhysicalNumberOfRows();
                System.out.println(rows);
                for (int i = 0; i < rows; i++) {
                    //过滤表头行
                    if (i == 0) {
                        continue;
                    }
                    //获取当前行的数据
                    Row row = sheet.getRow(i);
                    Object[] objects = new Object[row.getPhysicalNumberOfCells()];
                    int index = 0;
                    for (Cell cell : row) {
                        if ( cell.getCellTypeEnum().equals(NUMERIC)) {
                            objects[index] = (int) cell.getNumericCellValue();
                        }
                        if (cell.getCellTypeEnum().equals(STRING)) {
                            objects[index] = cell.getStringCellValue();
                        }
                        if (cell.getCellTypeEnum().equals(BOOLEAN)) {
                            objects[index] = cell.getBooleanCellValue();
                        }
                        if (cell.getCellTypeEnum().equals(ERROR)) {
                            objects[index] = cell.getErrorCellValue();
                        }
                        index++;
                    }
                    list.add(objects);
                }
                System.out.println("导入文件解析成功！");
                return list;
            }catch (Exception e){
                System.out.println("导入文件解析失败！");
                e.printStackTrace();
            }
        return null;
    }



}