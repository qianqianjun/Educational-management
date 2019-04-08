package buct.software.service;

import buct.software.dao.SchedulingDao;
import buct.software.domain.Scheduling;
import buct.software.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 刘权达
 * 数据表 scheduling 服务层
 * 未完成
 */
@Service
public class SchedulingService {
    @Autowired
    SchedulingDao schedulingDao;


    public ResponseMessage getSchedulings(Scheduling scheduling){
        List<Scheduling> data = schedulingDao.getSchedulings(scheduling);
        ResponseMessage message=ResponseMessage.getMessage(data!=null,ResponseMessage.SUCCESS,
                "查询成功！", ResponseMessage.EmptyDate,"查询结果为空！");
        message.setData(data);
        return message;
    }
}
