package buct.software.service;

import buct.software.dao.StudentDao;
import buct.software.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author  高谦
 * 学生表相应的服务。
 */
@Service
public class StudentService {
    @Autowired
    StudentDao studentDao;

    /**
     * 根据学号获得学生信息
     * @param sno
     * @return
     */
    public Student getStudentBySno(Integer sno){
        return studentDao.getBySno(sno);
    }
}
