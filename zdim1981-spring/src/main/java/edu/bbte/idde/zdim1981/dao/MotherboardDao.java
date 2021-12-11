package edu.bbte.idde.zdim1981.dao;

import edu.bbte.idde.zdim1981.model.Motherboard;

import java.util.Collection;

public interface MotherboardDao extends  Dao<Motherboard> {
    Collection<Motherboard> readByMinMemory(Integer memory);
}
