package buct.software.dao;

import buct.software.domain.Power;

import java.util.List;

public interface PowerDao {
    public List<Power> getPower();

    public void deletePower();

    public void updatePower(Power power);

    public void insertPower(Power power);
}
