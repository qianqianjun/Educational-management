package buct.software.service;

import buct.software.dao.StudentDao;
import buct.software.domain.Student;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * @author 高谦
 * 学生表相应的服务。
 */
@Service
public class StudentService {
    @Autowired
    StudentDao studentDao;

    /**
     * 根据学号获得学生信息
     *
     * @param sno
     * @return
     */

    public Student getStudentBySno(Integer sno) {
        return studentDao.getBySno(sno);
    }

    public List<Student> getAllStudent() {
        return studentDao.getAll();
    }

    public List<Student> getStudentsByExample(Student student) {
        return studentDao.getStudentsByExample(student);
    }

    @Transactional
    public void saveStudents(List<Student> students) {
        for (Student student : students) {
            studentDao.insertStudnet(student);
        }
    }

    public void saveStudent(Student student) {
        studentDao.insertStudnet(student);
    }

    public void updateStudent(Student student) {
        studentDao.updateStudent(student);
    }

    public boolean excel(File file) {
        HSSFWorkbook hssfWorkbook = null;
        Student student = null;
        List<Student> students = new LinkedList<>();
//        students.g
        try {
            hssfWorkbook = new HSSFWorkbook(new FileInputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        HSSFSheet sheet = hssfWorkbook.getSheetAt(0);
        int lastRowNum = sheet.getLastRowNum();
        for (int i = 1; i <= lastRowNum; i++) {
            HSSFRow row = sheet.getRow(i);
            System.out.println(row.getCell(10).toString());
            System.out.println(row.getCell(1).toString());
            student = new Student(
                    Integer.parseInt(row.getCell(0).toString()),
                    row.getCell(1).toString(),
                    row.getCell(2).toString(),
                    row.getCell(3).toString(),
                    row.getCell(4).toString(),
                    row.getCell(5).toString(),
                    row.getCell(6).toString(),
                    row.getCell(9).toString(),
                    Integer.parseInt(row.getCell(10).toString()),
                    row.getCell(7).toString(),
                    Integer.parseInt(row.getCell(8).toString())
            );
            students.add(student);
            System.out.println(student);
        }
        saveStudents(students);
//        sheet.getRow()
        return true;
    }

}
