package edu.bbte.idde.zdim1981.backend.dataaccessobject;

import edu.bbte.idde.zdim1981.backend.model.HardwareShopCpu;
import java.util.Collection;

public interface HardwareShopCpuDao {
    void create(HardwareShopCpu entity);

    void delete(Long id);

    HardwareShopCpu read(Long id);

    void update(HardwareShopCpu entity, Long id);

    Collection<HardwareShopCpu> readAll();
}
