package edu.bbte.idde.zdim1981.backend.dataaccessobject;

import edu.bbte.idde.zdim1981.backend.dataaccessobject.mem.MemDaoFactory;
import edu.bbte.idde.zdim1981.backend.config.Config;
import edu.bbte.idde.zdim1981.backend.config.ConfigFactory;
import edu.bbte.idde.zdim1981.backend.dataaccessobject.jdbc.JdbcDaoFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class DaoFactory {
    public static final Logger LOG = LoggerFactory.getLogger(DaoFactory.class);
    private static DaoFactory instance;

    public static synchronized DaoFactory getInstance() {
        if (instance == null) {
            Config config = ConfigFactory.getConfig();
            if ("jdbc".equals(config.getDaoType())) {
                instance = new JdbcDaoFactory();
            } else if ("mem".equals(config.getDaoType())) {
                instance = new MemDaoFactory();
            }
        }
        return instance;
    }

    public abstract MotherboardDao getMotherboardDao();

    public abstract CpuDao getCpuDao();
}
