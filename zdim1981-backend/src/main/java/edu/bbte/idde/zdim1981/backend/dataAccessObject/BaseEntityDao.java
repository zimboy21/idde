package edu.bbte.idde.zdim1981.backend.dataaccessobject;

import edu.bbte.idde.zdim1981.backend.model.BaseEntity;

import java.util.Collection;
import java.util.List;

public interface BaseEntityDao {
    void create(BaseEntity entity);

    BaseEntity read(Long id);

    void update(BaseEntity entity, Long id);

    void delete(Long id);

    Collection<BaseEntity> readAll();
}
