package edu.bbte.idde.zdim1981.desktop;

import edu.bbte.idde.zdim1981.backend.dataaccessobject.mem.HardwareShopCpuInMemDao;
import edu.bbte.idde.zdim1981.backend.model.BaseEntity;
import edu.bbte.idde.zdim1981.backend.model.HardwareShopCpu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FrontendMain {

    public static final Logger LOG = LoggerFactory.getLogger(FrontendMain.class);

    public static void main(String[] args) {
        HardwareShopCpuInMemDao cpuDao = new HardwareShopCpuInMemDao();
        cpuDao.create(new HardwareShopCpu("AMD Ryzen 7 5800X", 2000D, 3.4, 1, 16));
        HardwareShopCpu i7 = new HardwareShopCpu("Intel Core i7-11600K", 1750.6, 3.6, 2, 24);

        LOG.debug(cpuDao.read(0L).toString());
        cpuDao.update(i7, 0L);
        LOG.debug(cpuDao.read(0L).toString());
        cpuDao.delete(0L);

        cpuDao.create(new HardwareShopCpu("Intel Core i7-11600K", 2000D, 3.4, 1, 16));
        cpuDao.create(i7);

        for (BaseEntity cpu : cpuDao.readAll()) {
            LOG.debug("{}", cpu);
        }
    }
}
