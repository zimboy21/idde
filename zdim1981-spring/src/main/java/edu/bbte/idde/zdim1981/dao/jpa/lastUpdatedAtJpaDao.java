package edu.bbte.idde.zdim1981.dao.jpa;

import edu.bbte.idde.zdim1981.model.LastUpdatedAt;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

@Profile("jpa")
public interface lastUpdatedAtJpaDao extends JpaRepository<LastUpdatedAt, Long> {
}
