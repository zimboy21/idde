package edu.bbte.idde.zdim1981.backend;

import edu.bbte.idde.zdim1981.backend.dataaccessobject.CpuShopDao;
import edu.bbte.idde.zdim1981.backend.dataaccessobject.mem.CpuShopInMemDao;
import edu.bbte.idde.zdim1981.backend.model.CpuShop;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Backend {

    public static final Logger LOG = LoggerFactory.getLogger(Backend.class);

    public CpuShopDao getGoatDao() {
        CpuShopInMemDao goatInMemDao = new CpuShopInMemDao();
        goatInMemDao.create(new CpuShop("AMD Ryzen 7 5800X", 2000D, 3.4, 1, 16));
        goatInMemDao.create(new CpuShop("Intel Core i7-11600K", 1750.6, 3.6, 2, 24));
        return goatInMemDao;
    }
}