package buct.software.service;

import buct.software.dao.MajorDao;
import buct.software.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MajorService {
    @Autowired
    MajorDao majorDao;

    public ResponseMessage getMajorNamesByCollegeName(String collegeName){
        List<String> data = majorDao.getMajorNamesByCollegeName(collegeName);
        ResponseMessage message=ResponseMessage.getMessage(data!=null,ResponseMessage.SUCCESS,
                "查询成功！", ResponseMessage.EmptyDate,"查询结果为空！");
        message.setData(data);
        return message;
    }
}
