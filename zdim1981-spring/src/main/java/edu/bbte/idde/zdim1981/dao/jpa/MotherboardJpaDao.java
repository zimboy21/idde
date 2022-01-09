package edu.bbte.idde.zdim1981.dao.jpa;

import edu.bbte.idde.zdim1981.dao.MotherboardDao;
import edu.bbte.idde.zdim1981.model.Motherboard;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Profile("jpa")
@Repository
public interface MotherboardJpaDao extends MotherboardDao, JpaRepository<Motherboard, Long> {
}
