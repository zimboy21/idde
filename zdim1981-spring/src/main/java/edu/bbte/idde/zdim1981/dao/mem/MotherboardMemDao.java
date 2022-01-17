package edu.bbte.idde.zdim1981.dao.mem;

import edu.bbte.idde.zdim1981.dao.MotherboardDao;
import edu.bbte.idde.zdim1981.model.Cpu;
import edu.bbte.idde.zdim1981.model.Motherboard;
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
public class MotherboardMemDao implements MotherboardDao {
    private final ConcurrentHashMap<Long, Motherboard> motherboardDatabse;
    private final AtomicLong key;

    public MotherboardMemDao() {
        log.info("MotherboardMemDao constructed.");
        motherboardDatabse = new ConcurrentHashMap<>();
        key = new AtomicLong(0L);
        Cpu cpu = new Cpu("AMD Ryzen 7 5800X", 2000D, 3.4, 1, 16, new ArrayList<>());
        this.saveAndFlush(new Motherboard("ASUS PTGD1-LA",  550D, 800, "HP BIOS", 128, cpu));
        this.saveAndFlush(new Motherboard("Asus TUF Z590-Plus", 251.38, 3600, "UEFI BIOS", 512, cpu));
    }

    @Override
    public Motherboard saveAndFlush(Motherboard entity) {
        log.info("Motherboard created.");
        entity.setId(key.getAndIncrement());
        motherboardDatabse.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public Motherboard getById(Long id) {
        log.info("Motherboard " + id +  " read.");
        return motherboardDatabse.get(id);
    }

    @Override
    public Motherboard save(Motherboard entity) {
        log.info("Motherboard updated.");
        motherboardDatabse.computeIfPresent(entity.getId(), (key, value) -> entity);
        return entity;
    }

    @Override
    public void deleteById(Long id) {
        log.info("Motherboard " + id + " deleted.");
        motherboardDatabse.remove(id);
    }

    @Override
    public Collection<Motherboard> findAll() {
        log.info("Motherboard readAll().");
        return motherboardDatabse.values();
    }

    @Override
    public Collection<Motherboard> readByMemory(Integer memory) {
        ArrayList<Motherboard> motherboards = new ArrayList<>();
        for (Motherboard i : motherboardDatabse.values()) {
            if (i.getPrice() >= memory) {
                motherboards.add(i);
            }
        }
        return motherboards;
    }
}
