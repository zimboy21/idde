package edu.bbte.idde.zdim1981.backend.dataaccessobject;

import edu.bbte.idde.zdim1981.backend.model.CpuShop;
import java.util.Collection;

public interface CpuShopDao {
    void create(CpuShop entity);

    void delete(Long id);

    CpuShop read(Long id);

    void update(CpuShop entity, Long id);

    Collection<CpuShop> readAll();
}
