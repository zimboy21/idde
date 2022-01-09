package edu.bbte.idde.zdim1981.dao.jpa;

import edu.bbte.idde.zdim1981.dao.MotherboardDao;
import edu.bbte.idde.zdim1981.model.Motherboard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MotherboardJpaDao extends MotherboardDao, JpaRepository<Motherboard, Long> {
}
