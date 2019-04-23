package buct.software.controller;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IDEA
 * author:Yarm.Yang
 * Date:2019/1/21
 * Time:10:01
 * Des:文件
 */
@Controller
public class FileController {

    private final static String EXCEL_2003_DOWN = ".xls"; // 2003- 版本的excel
    private final static String EXCEL_2007_UP = ".xlsx"; // 2007+ 版本的excel

    @RequestMapping("excel")
    @ResponseBody
    public List<WhiteListExcelVo> fileUpload(@RequestParam("file")MultipartFile file){
        String fileName = file.getOriginalFilename();
        System.out.println(fileName);


        try {
            InputStream in = file.getInputStream();
            List<WhiteListExcelVo> list = new ArrayList<>();

            // 创建Excel工作薄
            Workbook work = this.getWorkbook(in, fileName);
            if (null == work) {
                throw new Exception("创建Excel工作薄为空或excel格式不对！");
            }
            Sheet sheet = work.getSheetAt(0);
            //遍历行
            for (int i = sheet.getFirstRowNum(); i < sheet.getLastRowNum() + 1; i++) {
                Row row= sheet.getRow(i);
                if (row == null) {
                    continue;
                }
                //遍历列
                WhiteListExcelVo wev = new WhiteListExcelVo();
                for (int j = row.getFirstCellNum(); j < row.getLastCellNum(); j++) {
                    Cell cell = row.getCell(j);
                    Object cellValue = this.getCellValue(cell);
                    if(cell != null){
                        switch (j){
                            case 0 : //第一列
                                wev.setFirst(cellValue.toString());
                                break;
                            case 1: //第二列，依此类推
                                wev.setSecond(cellValue.toString());
                                break;
                            default:
                                continue;
                        }
                    }
                }
                if(wev != null){
                    list.add(wev);
                }

            }
            System.out.println("excel:"+list);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public Workbook getWorkbook(InputStream inStr, String fileName) throws Exception {
        Workbook wb = null;
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        if (EXCEL_2003_DOWN.equals(fileType)) {
            wb = new HSSFWorkbook(inStr); // 2003-
        } else if (EXCEL_2007_UP.equals(fileType)) {
            wb = new XSSFWorkbook(inStr); //2007+
        }
        return wb;
    }

    private Object getCellValue(Cell cell) {
        Object value = null;
        DecimalFormat df = new DecimalFormat("0"); // 格式化number String字符
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd"); // 日期格式化
        DecimalFormat df2 = new DecimalFormat("0.00"); // 格式化数字
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                value = cell.getRichStringCellValue().getString();
                break;
            case Cell.CELL_TYPE_NUMERIC:
                if ("General".equals(cell.getCellStyle().getDataFormatString())) {
                    value = df.format(cell.getNumericCellValue());
                } else if ("m/d/yy".equals(cell.getCellStyle().getDataFormatString())) {
                    value = sdf.format(cell.getDateCellValue());
                } else {
                    value = df2.format(cell.getNumericCellValue());
                }
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                value = cell.getBooleanCellValue();
                break;
            case Cell.CELL_TYPE_BLANK:
                value = "";
                break;
            default:
                break;
        }
        return value;
    }

}