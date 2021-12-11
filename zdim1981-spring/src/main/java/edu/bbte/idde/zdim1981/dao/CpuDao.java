package edu.bbte.idde.zdim1981.dao;

import edu.bbte.idde.zdim1981.model.Cpu;
import java.util.Collection;

public interface CpuDao extends Dao<Cpu> {
    Collection<Cpu> readByMinClockSpeed(Integer clockSpeed);
}
