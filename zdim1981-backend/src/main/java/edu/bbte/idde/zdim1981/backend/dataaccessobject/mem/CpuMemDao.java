package edu.bbte.idde.zdim1981.backend.dataaccessobject.mem;

import edu.bbte.idde.zdim1981.backend.dataaccessobject.CpuDao;
import edu.bbte.idde.zdim1981.backend.model.Cpu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class CpuMemDao implements CpuDao {
    private final ConcurrentHashMap<Long, Cpu> cpuShopDatabase;
    private final AtomicLong key;
    public static final Logger LOG = LoggerFactory.getLogger(CpuMemDao.class);

    public CpuMemDao() {
        LOG.info("CpuMemDao constructed.");
        cpuShopDatabase = new ConcurrentHashMap<>();
        key = new AtomicLong(0L);
        this.create(new Cpu("AMD Ryzen 7 5800X", 2000D, 3.4, 1, 16));
        this.create(new Cpu("Intel Core i7-11600K", 1750.6, 3.6, 2, 24));
        this.create(new Cpu("Intel Core i5", 170D, 2.6, 0, 8));
    }

    @Override
    public void create(Cpu entity) {
        LOG.info("CPU created.");
        entity.setId(key.getAndIncrement());
        cpuShopDatabase.put(entity.getId(), entity);
    }

    @Override
    public Cpu read(Long id) {
        LOG.info("CPU " + id +  " read.");
        return cpuShopDatabase.get(id);
    }

    @Override
    public void update(Cpu entity, Long id) {
        LOG.info("CPU " + id + " updated.");
        cpuShopDatabase.computeIfPresent(id, (key, value) -> entity);
    }

    @Override
    public void delete(Long id) {
        LOG.info("CPU " + id + " deleted.");
        cpuShopDatabase.remove(id);
    }

    @Override
    public Collection<Cpu> readAll() {
        LOG.info("CPU readAll().");
        return cpuShopDatabase.values();
    }

    @Override
    public Collection<Cpu> readByMinClockSpeed(Integer clockSpeed) {
        ArrayList<Cpu> cpus = new ArrayList<>();
        for (Cpu i : cpuShopDatabase.values()) {
            if (i.getClockSpeed() >= clockSpeed) {
                cpus.add(i);
            }
        }
        return cpus;
    }

    @Override
    public Integer deleteByIntervalPrice(Double min, Double max) {
        LOG.info("CPUs deleted in price range: (" + min + ',' + max + ')');
        int counter = 0;
        for (Cpu i : cpuShopDatabase.values()) {
            if (i.getPrice() >= min && i.getPrice() <= max) {
                cpuShopDatabase.remove(i.getId());
                counter += 1;
            }
        }
        return counter;
    }
}
