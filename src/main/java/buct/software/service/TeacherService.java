package buct.software.service;

import buct.software.dao.TeacherDao;
import buct.software.domain.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {
    @Autowired
    TeacherDao teacherDao;

    public Teacher getTeacherByTno(Integer tno) {
        if(tno!=null){
            return null;
        }
        Teacher teacher =teacherDao.getByTno(tno);
        if(teacher==null)
            return null;
        else
            return teacher;
    }
}
