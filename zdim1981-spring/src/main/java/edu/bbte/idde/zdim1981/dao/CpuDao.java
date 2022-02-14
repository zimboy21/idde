package edu.bbte.idde.zdim1981.dao;

import edu.bbte.idde.zdim1981.model.Cpu;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface CpuDao extends Dao<Cpu> {
    Collection<Cpu> readByClockSpeed(Integer clockSpeed);
}
