package edu.bbte.idde.zdim1981.dao.jpa;

import edu.bbte.idde.zdim1981.dao.CpuDao;
import edu.bbte.idde.zdim1981.model.Cpu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CpuJpaDao extends CpuDao, JpaRepository<Cpu, Long> {
}
