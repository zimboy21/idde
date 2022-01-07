package edu.bbte.idde.zdim1981.dao.mem;

import edu.bbte.idde.zdim1981.dao.MotherboardDao;
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
        this.create(new Motherboard("ASUS PTGD1-LA",  550D, 800, "HP BIOS", 128, 2L));
        this.create(new Motherboard("Asus TUF Z590-Plus", 251.38, 3600, "UEFI BIOS", 512, 1L));
    }

    @Override
    public Motherboard create(Motherboard entity) {
        log.info("Motherboard created.");
        entity.setId(key.getAndIncrement());
        motherboardDatabse.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public Motherboard read(Long id) {
        log.info("Motherboard " + id +  " read.");
        return motherboardDatabse.get(id);
    }

    @Override
    public void update(Motherboard entity, Long id) {
        log.info("Motherboard " + id + " updated.");
        motherboardDatabse.computeIfPresent(id, (key, value) -> entity);
    }

    @Override
    public Boolean delete(Long id) {
        log.info("Motherboard " + id + " deleted.");
        motherboardDatabse.remove(id);
        return true;
    }

    @Override
    public Collection<Motherboard> readAll() {
        log.info("Motherboard readAll().");
        return motherboardDatabse.values();
    }

    @Override
    public Collection<Motherboard> readByMinMemory(Integer memory) {
        ArrayList<Motherboard> motherboards = new ArrayList<>();
        for (Motherboard i : motherboardDatabse.values()) {
            if (i.getPrice() >= memory) {
                motherboards.add(i);
            }
        }
        return motherboards;
    }
}
