package buct.software.mapperTest;

import buct.software.dao.SchedulingDao;
import buct.software.domain.Scheduling;
import buct.software.views.MobileSchedulingView;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class Test2 {
    @Autowired
    SchedulingDao schedulingDao;
    @Test
    public void test(){
        List<MobileSchedulingView> coursesByTnoAndTnameAndAddress = schedulingDao.getCoursesByTnoAndTnameAndAddress(1000000, "陈雪", "图书");
        System.out.println(coursesByTnoAndTnameAndAddress);
    }
}
