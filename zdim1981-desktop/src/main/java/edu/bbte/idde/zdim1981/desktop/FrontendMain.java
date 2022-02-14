package edu.bbte.idde.zdim1981.desktop;

import edu.bbte.idde.zdim1981.backend.dataaccessobject.CpuDao;
import edu.bbte.idde.zdim1981.backend.dataaccessobject.DaoFactory;
import edu.bbte.idde.zdim1981.backend.model.BaseEntity;
import edu.bbte.idde.zdim1981.backend.model.Cpu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FrontendMain {

    public static final Logger LOG = LoggerFactory.getLogger(FrontendMain.class);

    public static void main(String[] args) {
        DaoFactory daoFactory = DaoFactory.getInstance();
        CpuDao cpuDao = daoFactory.getCpuDao();
        LOG.debug("{}", cpuDao.read(0L));
        cpuDao.delete(0L);
        Cpu newcpu = new Cpu("AMD Ryzen 7 5800X", 2000D, 3.4, 1, 16, false);
        cpuDao.create(newcpu);
        Cpu intel = new Cpu("Intel Core i9-11600K", 1850D, 4.8, 1, 32, false);
        cpuDao.update(intel, 0L);
        cpuDao.create(intel);

        for (BaseEntity cpu : cpuDao.readAll()) {
            LOG.debug("{}", cpu);
        }
    }
}
