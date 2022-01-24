package edu.bbte.idde.zdim1981.backend.dataaccessobject.mem;

import edu.bbte.idde.zdim1981.backend.dataaccessobject.CpuDao;
import edu.bbte.idde.zdim1981.backend.dataaccessobject.DaoFactory;
import edu.bbte.idde.zdim1981.backend.dataaccessobject.MotherboardDao;

public class MemDaoFactory extends DaoFactory {
    private static CpuDao cpuDao;
    private static MotherboardDao mbdao;

    @Override
    public synchronized CpuDao getCpuDao() {
        if (cpuDao == null) {
            cpuDao = new CpuMemDao();
        }
        return cpuDao;
    }

    @Override
    public synchronized MotherboardDao getMotherboardDao() {
        if (mbdao == null) {
            mbdao = new MotherboardMemDao();
        }
        return mbdao;
    }
}
