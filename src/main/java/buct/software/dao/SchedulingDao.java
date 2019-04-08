package buct.software.dao;

import buct.software.domain.Scheduling;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 高谦
 * 排课表 scheduling 数据库操作类
 */
@Repository
public interface SchedulingDao {

    public List<Scheduling> getSchedulings(Scheduling scheduling);
}
