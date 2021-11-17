package edu.bbte.idde.zdim1981.backend.dataaccessobject.mem;

import edu.bbte.idde.zdim1981.backend.dataaccessobject.MotherboardDao;
import edu.bbte.idde.zdim1981.backend.model.Motherboard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class MotherboardMemDao implements MotherboardDao {
    private final ConcurrentHashMap<Long, Motherboard> motherboardDatabse;
    private final AtomicLong key;
    public static final Logger LOG = LoggerFactory.getLogger(MotherboardMemDao.class);

    public MotherboardMemDao() {
        LOG.info("MotherboardMemDao constructed.");
        motherboardDatabse = new ConcurrentHashMap<>();
        key = new AtomicLong(0L);
        this.create(new Motherboard("ASUS PTGD1-LA",  550D, 800, "HP BIOS", 128, 2L));
        this.create(new Motherboard("Asus TUF Z590-Plus", 251.38, 3600, "UEFI BIOS", 512, 1L));
    }

    @Override
    public void create(Motherboard entity) {
        LOG.info("Motherboard created.");
        entity.setId(key.getAndIncrement());
        motherboardDatabse.put(entity.getId(), entity);
    }

    @Override
    public Motherboard read(Long id) {
        LOG.info("Motherboard " + id +  " read.");
        return motherboardDatabse.get(id);
    }

    @Override
    public void update(Motherboard entity, Long id) {
        LOG.info("Motherboard " + id + " updated.");
        motherboardDatabse.computeIfPresent(id, (key, value) -> entity);
    }

    @Override
    public void delete(Long id) {
        LOG.info("Motherboard " + id + " deleted.");
        motherboardDatabse.remove(id);
    }

    @Override
    public Collection<Motherboard> readAll() {
        LOG.info("Motherboard readAll().");
        return motherboardDatabse.values();
    }

    @Override
    public Collection<Motherboard> getByMaxPrice(Integer price) {
        ArrayList<Motherboard> motherboards = new ArrayList<>();
        for (Motherboard i : motherboardDatabse.values()) {
            if (i.getPrice() <= price) {
                motherboards.add(i);
            }
        }
        return motherboards;
    }
}
