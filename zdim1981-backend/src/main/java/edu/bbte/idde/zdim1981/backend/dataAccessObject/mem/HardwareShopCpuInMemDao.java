package edu.bbte.idde.zdim1981.backend.dataaccessobject.mem;

import edu.bbte.idde.zdim1981.backend.dataaccessobject.HardwareShopCpuDao;
import edu.bbte.idde.zdim1981.backend.model.HardwareShopCpu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class HardwareShopCpuInMemDao implements HardwareShopCpuDao {
    private ConcurrentHashMap<Long, HardwareShopCpu> cpuDatabase;
    private AtomicLong key;
    public static final Logger LOG = LoggerFactory.getLogger(HardwareShopCpuInMemDao.class);

    public HardwareShopCpuInMemDao() {
        LOG.info("HardwareShopCpuInMemDao constructed.");
        cpuDatabase = new ConcurrentHashMap<>();
        key = new AtomicLong(0L);
    }

    @Override
    public void create(HardwareShopCpu entity) {
        LOG.info("HardwareShopCpu created.");
        entity.setId(key.getAndIncrement());
        cpuDatabase.put((entity.getId()), entity);
    }

    @Override
    public HardwareShopCpu read(Long id) {
        LOG.info("HardwareShopCpu " + id +  " read.");
        return cpuDatabase.get(id);
    }

    @Override
    public void update(HardwareShopCpu entity, Long id) {
        LOG.info("HardwareShopCpu " + id + " updated.");
        cpuDatabase.computeIfPresent(id, (key, value) -> entity);
    }

    @Override
    public void delete(Long id) {
        LOG.info("HardwareShopCpu " + id + " deleted.");
        cpuDatabase.remove(id);
    }

    public Collection<HardwareShopCpu> readAll() {
        LOG.info("HardwareShopCpu readAll().");
        return cpuDatabase.values();
    }

}
