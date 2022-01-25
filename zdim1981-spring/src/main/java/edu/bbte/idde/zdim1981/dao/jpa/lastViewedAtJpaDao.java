package edu.bbte.idde.zdim1981.dao.jpa;

import edu.bbte.idde.zdim1981.model.LastViewedAt;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

@Profile("jpa")
public interface lastViewedAtJpaDao extends JpaRepository<LastViewedAt, Long> {
}
