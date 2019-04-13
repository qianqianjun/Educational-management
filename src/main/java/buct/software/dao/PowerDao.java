package buct.software.dao;

import buct.software.domain.Power;

import java.util.List;
/**
 * @author 孟庆强
 */
public interface PowerDao {
    public List<Power> getPower();

    public void deletePower();

    public void updatePower(Power power);

    public void insertPower(Power power);
}
