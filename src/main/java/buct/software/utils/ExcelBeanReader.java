package buct.software.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ExcelBeanReader<T> {
    private Class<T> clazz;
    private HSSFWorkbook hssfWorkbook;
    private Sheet sheet;

    public ExcelBeanReader(Class<T> clazz, HSSFWorkbook hssfWorkbook) {
        this.clazz = clazz;
        this.hssfWorkbook = hssfWorkbook;
    }

    public void selectSheet(int index) {
        sheet = hssfWorkbook.getSheetAt(index);
    }

    public void selectSheet(String name) {
        sheet = hssfWorkbook.getSheet(name);
    }

    public void test() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        ClassLoader classLoader = clazz.getClassLoader();
//        classLoader.
        Constructor<T> constructor = clazz.getConstructor();
        T t = constructor.newInstance();
        Field[] declaredFields = clazz.getDeclaredFields();
        Method[] methods = clazz.getMethods();
//        methods[0].invoke()
    }
}
