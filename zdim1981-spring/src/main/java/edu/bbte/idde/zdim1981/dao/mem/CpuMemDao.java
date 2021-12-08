package edu.bbte.idde.zdim1981.dao.mem;

import edu.bbte.idde.zdim1981.dao.CpuDao;
import edu.bbte.idde.zdim1981.model.Cpu;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
@Profile("mem")
@Slf4j
public class CpuMemDao implements CpuDao {
    private final ConcurrentHashMap<Long, Cpu> cpuShopDatabase;
    private final AtomicLong key;

    public CpuMemDao() {
        log.info("CpuMemDao constructed.");
        cpuShopDatabase = new ConcurrentHashMap<>();
        key = new AtomicLong(0L);
        this.create(new Cpu("AMD Ryzen 7 5800X", 2000D, 3.4, 1, 16));
        this.create(new Cpu("Intel Core i7-11600K", 1750.6, 3.6, 2, 24));
        this.create(new Cpu("Intel Core i5", 170D, 2.6, 0, 8));
    }

    @Override
    public Cpu create(Cpu entity) {
        log.info("CPU created.");
        entity.setId(key.getAndIncrement());
        cpuShopDatabase.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public Cpu read(Long id) {
        log.info("CPU " + id +  " read.");
        return cpuShopDatabase.get(id);
    }

    @Override
    public void update(Cpu entity, Long id) {
        log.info("CPU " + id + " updated.");
        cpuShopDatabase.computeIfPresent(id, (key, value) -> entity);
    }

    @Override
    public void delete(Long id) {
        log.info("CPU " + id + " deleted.");
        cpuShopDatabase.remove(id);
    }

    @Override
    public Collection<Cpu> readAll() {
        log.info("CPU readAll().");
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

}
