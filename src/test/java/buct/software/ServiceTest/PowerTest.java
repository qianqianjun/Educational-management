package buct.software.ServiceTest;

import buct.software.domain.Power;
import buct.software.service.PowerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 权限服务层测试
 *
 * @author 孟庆强
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PowerTest {
    @Autowired
    PowerService powerService;

    /**
     * 初始化权限测试
     */
    @Test
    public void initPower() {
        powerService.initPower();
    }
    /**
     * 获取信息测试
     */
    @Test
    public void getStatus() {
        Power status = powerService.getStatus();
        System.out.println(status);
    }
    /**
     * 获得选课权限测试
     */
    @Test
    public void getSelectCourse() {
        Boolean selectCourse = powerService.getSelectCourse();
        System.out.println(selectCourse);
    }

    /**
     * 开启选课权限测试
     */
    @Test
    public void openSelectCourse() {
        powerService.openSelectCourse();
        System.out.println(powerService.getStatus());
    }

    /**
     * 关闭选课权限测试
     */
    @Test
    public void closeSelectCourse() {
        powerService.closeSelectCourse();
        System.out.println(powerService.getStatus());
    }
    /**
     * 获得成绩录入权限测试
     */
    @Test
    public void getScore() {
        Boolean score = powerService.getScore();
        System.out.println(score);
    }

    /**
     * 开启成绩录入权限测试
     */
    @Test
    public void openScore() {
        powerService.openScore();
        System.out.println(powerService.getStatus());
    }

    /**
     * 关闭成绩录入权限测试
     */
    @Test
    public void closeScore() {
        powerService.closeScore();
        System.out.println(powerService.getStatus());
    }
    /**
     * 获得学籍异动选课权限测试
     */
    @Test
    public void getAbnormal() {
        Boolean abnormal = powerService.getAbnormal();
        System.out.println(abnormal);
    }

    /**
     * 开启学籍异动选课权限测试
     */
    @Test
    public void openAbnormal() {
        powerService.openAbnormal();
        System.out.println(powerService.getStatus());
    }

    /**
     * 关闭学籍异动选课权限测试
     */
    @Test
    public void closeAbnormal() {
        powerService.closeAbnormal();
        System.out.println(powerService.getStatus());
    }
}
