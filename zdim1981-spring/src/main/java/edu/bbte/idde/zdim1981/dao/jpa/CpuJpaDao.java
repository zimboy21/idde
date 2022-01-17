package edu.bbte.idde.zdim1981.dao.jpa;

import edu.bbte.idde.zdim1981.dao.CpuDao;
import edu.bbte.idde.zdim1981.model.Cpu;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Profile("jpa")
@Repository
public interface CpuJpaDao extends CpuDao, JpaRepository<Cpu, Long> {
}
