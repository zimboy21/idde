package edu.bbte.idde.zdim1981.backend.dataaccessobject;

import edu.bbte.idde.zdim1981.backend.model.Cpu;

import java.util.Collection;

public interface CpuDao extends Dao<Cpu> {
    Collection<Cpu> readByMinClockSpeed(Integer clockSpeed);
}
