package edu.bbte.idde.zdim1981.backend.dataaccessobject;

import edu.bbte.idde.zdim1981.backend.model.BaseEntity;

import java.util.Collection;

public interface Dao<T extends BaseEntity> {
    void create(T entity);

    void delete(Long id);

    T read(Long id);

    void update(T entity, Long id);

    Collection<T> readAll();

    Collection<T> getByMaxPrice(Integer price);
}
