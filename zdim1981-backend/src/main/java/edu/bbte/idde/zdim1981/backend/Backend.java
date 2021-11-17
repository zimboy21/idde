package edu.bbte.idde.zdim1981.backend;

import edu.bbte.idde.zdim1981.backend.dataaccessobject.CpuDao;
import edu.bbte.idde.zdim1981.backend.dataaccessobject.mem.CpuMemDao;
import edu.bbte.idde.zdim1981.backend.model.Cpu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Backend {

    public static final Logger LOG = LoggerFactory.getLogger(Backend.class);

    public CpuDao getGoatDao() {
        CpuMemDao goatInMemDao = new CpuMemDao();
        goatInMemDao.create(new Cpu("AMD Ryzen 7 5800X", 2000D, 3.4, 1, 16));
        goatInMemDao.create(new Cpu("Intel Core i7-11600K", 1750.6, 3.6, 2, 24));
        return goatInMemDao;
    }
}