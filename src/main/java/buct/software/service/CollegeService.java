package buct.software.service;
import buct.software.dao.CollegeDao;
import buct.software.domain.College;
import buct.software.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @author  高谦
 * 数据表 college 服务层。
 * 以下代码还可以继续优化。
 */
@Service
public class CollegeService {
    @Autowired
    CollegeDao collegeDao;
    /**
     * 获取全部学院的功能
     * @return
     */
    public ResponseMessage getAllCollege(){

        List<College> data=collegeDao.getColleges();
        ResponseMessage message=ResponseMessage.getMessage(data.size()>0,ResponseMessage.SUCCESS,
                "查询成功！", ResponseMessage.EmptyDate,"查询结果为空！");
        message.setData(data);
        return message;
    }

    /**
     * 根据学院id，来获取学院的其他信息
     * @param id  学院的id
     * @return
     */
    public ResponseMessage getCollegeById(String id) {
        College Co=collegeDao.getCollegeById(id);
        ResponseMessage message=ResponseMessage.getMessage(Co!=null,ResponseMessage.SUCCESS,
                "查询成功！", ResponseMessage.EmptyDate,"查询结果为空！");
        message.setData(Co);
        return message;
    }
}
