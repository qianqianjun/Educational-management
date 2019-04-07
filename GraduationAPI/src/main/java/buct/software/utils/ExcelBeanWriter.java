package buct.software.utils;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.lang.reflect.Field;
import java.util.List;

/**
 * 把domain的字段写入Excel文件
 * <p>
 * 已经测试通过的字段类型有
 * Integer Double Date(sql) Date(util)
 * Boolean String
 * <p>
 * 适配 POI version :3.17
 *
 * @param <T> domain 范型
 * @author spring
 * @version 0.0.1
 */
public class ExcelBeanWriter<T> {
    /**
     * @clazz Bean字节码对象
     * @hssfWorkbook Excel对象
     * @declaredFields Bean中字段信息
     */
    private Class clazz;
    private HSSFWorkbook hssfWorkbook;
    private Field[] declaredFields;

    /**
     * 构造方法
     *
     * @param clazz        Bean的字节码
     * @param hssfWorkbook xls工作表
     */
    public ExcelBeanWriter(Class clazz, HSSFWorkbook hssfWorkbook) {
        this.clazz = clazz;
        this.hssfWorkbook = hssfWorkbook;
        declaredFields = clazz.getDeclaredFields();
    }

    /**
     * 把对象写入新的sheet，并且返回新创建的sheet
     * list为空时返回null
     *
     * @param list 存放对象的List
     * @return sheet.getSheetName() or null
     */
    public Sheet write(List<T> list) throws NoSuchFieldException, IllegalAccessException {
        if (list.size() == 0) return null;
        HSSFSheet sheet = hssfWorkbook.createSheet();
        writePropertyName(sheet);
        writeProperties(list, sheet, 1);
        return sheet;
    }

    /**
     * 把List封装的Bean写入表格
     * 允许存在NULL属性，用NULL填装
     *
     * @param list  存放对象的List
     * @param sheet Excel中sheet
     * @param index 行偏移
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    private void writeProperties(List<T> list, Sheet sheet, int index) throws NoSuchFieldException, IllegalAccessException {
        for (T t : list) {
            Row row = sheet.createRow(index);
            for (int i = 0; i < declaredFields.length; i++) {
                Cell cell = row.createCell(i);
                Field field = declaredFields[i];
                if (!field.isAccessible()) field.setAccessible(true);
                String value = (field.get(t) == null) ? "NULL" : field.get(t).toString();
                cell.setCellValue(value);
            }
        }
    }

    /**
     * 把数据的属性名写入第一行
     *
     * @param sheet Excel中sheet对象
     */
    private void writePropertyName(Sheet sheet) {
        Row row = sheet.createRow(0);
        for (int i = 0; i < declaredFields.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(declaredFields[i].getName());
        }
    }

    /**
     * 追加数据到最后一行
     *
     * @param sheet Excel中sheet对象
     * @param list  需要追加存放对象的List
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public void appendProperties(Sheet sheet, List<T> list) throws NoSuchFieldException, IllegalAccessException {
        int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
        writeProperties(list, sheet, physicalNumberOfRows);
    }
}
