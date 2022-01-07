package edu.bbte.idde.zdim1981.dao;

import edu.bbte.idde.zdim1981.model.BaseEntity;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@Profile("mem")
public interface Dao<T extends BaseEntity> {
    T create(T entity);

    Boolean delete(Long id);

    T read(Long id);

    void update(T entity, Long id);

    Collection<T> readAll();
}
