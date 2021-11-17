package edu.bbte.idde.zdim1981.backend.dataaccessobject.jdbc;

import edu.bbte.idde.zdim1981.backend.RepositoryException;
import edu.bbte.idde.zdim1981.backend.dataaccessobject.CpuDao;
import edu.bbte.idde.zdim1981.backend.dataaccessobject.DaoFactory;
import edu.bbte.idde.zdim1981.backend.dataaccessobject.MotherboardDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JdbcDaoFactory extends DaoFactory {
    public static final Logger LOG = LoggerFactory.getLogger(JdbcDaoFactory.class);
    private static CpuDao cpuDao;
    private static MotherboardDao mbDao;

    @Override
    public synchronized CpuDao getCpuDao() {
        if (cpuDao == null) {
            try {
                cpuDao = new CpuJdbcDao();
            } catch (RepositoryException e) {
                LOG.error("Error: ", e);
            }
        }
        return cpuDao;
    }

    @Override
    public synchronized MotherboardDao getMotherboardDao() {
        if (mbDao == null) {
            try {
                mbDao = new MotherboardJdbcDao();
            } catch (RepositoryException e) {
                LOG.error("Error: ", e);
            }
        }
        return mbDao;
    }

}
