package edu.bbte.idde.zdim1981.backend.dataaccessobject;

import edu.bbte.idde.zdim1981.backend.model.Motherboard;

import java.util.Collection;

public interface MotherboardDao extends  Dao<Motherboard> {
    Collection<Motherboard> readByMinMemory(Integer memory);
}
