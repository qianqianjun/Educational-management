package buct.software.service;

import buct.software.dao.TeacherDao;
import buct.software.domain.Teacher;
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
 * @author 孟庆强
 */
@Service
public class TeacherService {
    @Autowired
    TeacherDao teacherDao;

    public Teacher getTeacherByTno(Integer tno) {
        Teacher teacher = teacherDao.getByTno(tno);
        return teacher;
    }

    @Transactional
    public void addTeachers(List<Teacher> teachers) {
        for (Teacher teacher : teachers) {
            teacherDao.insertTeacher(teacher);
        }
    }

    @Transactional
    public void updateTeachers(List<Teacher> teachers) {
        for (Teacher teacher : teachers) {
            teacherDao.updateTeacher(teacher);
        }
    }

    public void addTeacher(Teacher teachers) {
        teacherDao.insertTeacher(teachers);
    }

    public void updateTeacherById(Integer id, Teacher teacher) {
        teacher.setTno(id);
        teacherDao.updateTeacher(teacher);
    }

    public List<Teacher> getAllTeacher() {
        List<Teacher> teachers = teacherDao.queryByExample(new Teacher());
        return teachers;
    }

    public List<Teacher> getTeacherByExample(Teacher teacher) {
        return teacherDao.queryByExample(teacher);
    }

    public boolean excel(File file) {
        HSSFWorkbook hssfWorkbook = null;
        Teacher teacher = null;
        List<Teacher> teachers = new LinkedList<>();
        try {
            hssfWorkbook = new HSSFWorkbook(new FileInputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        HSSFSheet sheet = hssfWorkbook.getSheetAt(0);
        int lastRowNum = sheet.getLastRowNum();
        float tno = 0;
        float coid = 0;

        for (int i = 1; i <= lastRowNum; i++) {
            HSSFRow row = sheet.getRow(i);
            teacher = new Teacher();
            teacher.setTname(row.getCell(1).toString());
            teacher.setSex(row.getCell(2).toString());
            teacher.setCollegeId(Integer.parseInt(row.getCell(5).toString()));
            teacher.setEmail(row.getCell(4).toString());
            teacher.setPhone(row.getCell(3).toString());
            teacher.setOffice(row.getCell(6).toString());
            System.out.println();
            teacher.setTno(Integer.parseInt((row.getCell(0).toString())));
            teacher.setRank(row.getCell(7).toString());
            System.out.println(teacher);
            teachers.add(teacher);
        }
//        teacherDao.insertTeacher();
//        s(students);
        addTeachers(teachers);
//        sheet.getRow()
        return true;
    }
}
