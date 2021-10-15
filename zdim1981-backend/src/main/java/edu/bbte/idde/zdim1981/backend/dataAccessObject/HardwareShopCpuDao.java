package edu.bbte.idde.zdim1981.backend.dataAccessObject;

import edu.bbte.idde.zdim1981.backend.model.BaseEntity;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class HardwareShopCpuDao implements BaseEntityDao{
    private ConcurrentHashMap<Long, BaseEntity> cpuDatabase;
    private AtomicLong key;

    public HardwareShopCpuDao() {
        cpuDatabase = new ConcurrentHashMap<>();
        key = new AtomicLong(0L);
    }

    @Override
    public void create(BaseEntity entity) {
        entity.setId(key.getAndIncrement());
        cpuDatabase.put((entity.getId()), entity);
    }

    @Override
    public BaseEntity read(Long id) {
        return cpuDatabase.get(id);
    }

    @Override
    public void update(BaseEntity entity, Long id){
        cpuDatabase.computeIfPresent(id, (key, value) -> value = entity);
    }

    @Override
    public void delete(Long id) {
        cpuDatabase.remove(id);
    }

    public Collection<BaseEntity> readAll() {
        return cpuDatabase.values();
    }

}
