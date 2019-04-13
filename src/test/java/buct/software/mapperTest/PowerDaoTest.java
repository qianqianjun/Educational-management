package buct.software.mapperTest;

import buct.software.dao.PowerDao;
import buct.software.domain.Power;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * PowerDao的测试类
 *
 * @author 孟庆强
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PowerDaoTest {
    @Autowired
    PowerDao powerDao;

    /**
     * 测试getPower
     */
    @Test
    public void getPower() {
        List<Power> power = powerDao.getPower();
        System.out.println(power.get(0));
    }

    /**
     * 测试updatePower
     */
    @Test
    public void updatePower() {
        Power power = new Power();
        power.setAbnormal(1);
        power.setScore(0);
        power.setSelectCourse(1);
        powerDao.updatePower(power);
    }

    /**
     * 测试insertPower
     */
    @Test
    public void insertPower() {
        Power power = new Power();
        power.setAbnormal(1);
        power.setScore(0);
        power.setSelectCourse(0);
        powerDao.insertPower(power);
    }

    /**
     * 测试deletePower
     */
    @Test
    public void deletePower() {
        powerDao.deletePower();
    }
}
