package buct.software.service;

import buct.software.dao.CourseDao;
import buct.software.domain.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    @Autowired
    CourseDao courseDao;

    public List<Course> getAll(){
        return courseDao.getAll();
    }

    public Course getByName(String cno){
        if(cno!=null){
            return null;
        }
        else{
            return courseDao.getByCno(cno);
        }
    }
}
