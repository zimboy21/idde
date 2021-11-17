package edu.bbte.idde.zdim1981.desktop;

import edu.bbte.idde.zdim1981.backend.dataaccessobject.CpuDao;
import edu.bbte.idde.zdim1981.backend.dataaccessobject.DaoFactory;
import edu.bbte.idde.zdim1981.backend.model.Cpu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FrontendMain {

    public static final Logger LOG = LoggerFactory.getLogger(FrontendMain.class);

    public static void main(String[] args) {
        DaoFactory daoFactory = DaoFactory.getInstance();
        CpuDao cpuDao = daoFactory.getCpuDao();
        Cpu newcpu = new Cpu("AMD Ryzen 7 5800X", 2000D, 3.4, 1, 16);
        cpuDao.create(newcpu);
    }
}
