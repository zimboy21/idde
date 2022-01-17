package edu.bbte.idde.zdim1981.dao;

import edu.bbte.idde.zdim1981.model.BaseEntity;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@Profile("mem")
public interface Dao<T extends BaseEntity> {
    T saveAndFlush(T entity);

    void deleteById(Long id);

    T getById(Long id);

    T save(T entity);

    Collection<T> findAll();
}
