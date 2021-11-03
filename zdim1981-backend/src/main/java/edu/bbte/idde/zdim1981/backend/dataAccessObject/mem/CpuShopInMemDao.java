package edu.bbte.idde.zdim1981.backend.dataaccessobject.mem;

import edu.bbte.idde.zdim1981.backend.dataaccessobject.CpuShopDao;
import edu.bbte.idde.zdim1981.backend.model.CpuShop;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class CpuShopInMemDao implements CpuShopDao {
    private final ConcurrentHashMap<Long, CpuShop> cpuShopDatabase;
    private final AtomicLong key;
    public static final Logger LOG = LoggerFactory.getLogger(CpuShopInMemDao.class);

    public CpuShopInMemDao() {
        LOG.info("HardwareShopCpuInMemDao constructed.");
        cpuShopDatabase = new ConcurrentHashMap<>();
        key = new AtomicLong(0L);
        this.create(new CpuShop("AMD Ryzen 7 5800X", 2000D, 3.4, 1, 16));
        this.create(new CpuShop("Intel Core i7-11600K", 1750.6, 3.6, 2, 24));
        this.create(new CpuShop("Intel Core i5", 170D, 2.6, 0, 8));
    }

    @Override
    public void create(CpuShop entity) {
        LOG.info("HardwareShopCpu created.");
        entity.setId(key.getAndIncrement());
        cpuShopDatabase.put(entity.getId(), entity);
    }

    @Override
    public CpuShop read(Long id) {
        LOG.info("HardwareShopCpu " + id +  " read.");
        return cpuShopDatabase.get(id);
    }

    @Override
    public void update(CpuShop entity, Long id) {
        LOG.info("HardwareShopCpu " + id + " updated.");
        cpuShopDatabase.computeIfPresent(id, (key, value) -> entity);
    }

    @Override
    public void delete(Long id) {
        LOG.info("HardwareShopCpu " + id + " deleted.");
        cpuShopDatabase.remove(id);
    }

    @Override
    public Collection<CpuShop> readAll() {
        LOG.info("HardwareShopCpu readAll().");
        return cpuShopDatabase.values();
    }

}
