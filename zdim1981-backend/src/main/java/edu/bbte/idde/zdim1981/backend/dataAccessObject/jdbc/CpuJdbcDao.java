package edu.bbte.idde.zdim1981.backend.dataaccessobject.jdbc;

import edu.bbte.idde.zdim1981.backend.RepositoryException;
import edu.bbte.idde.zdim1981.backend.dataaccessobject.CpuDao;
import edu.bbte.idde.zdim1981.backend.model.Cpu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class CpuJdbcDao implements CpuDao {
    private final Connection connection;
    public static final Logger LOG = LoggerFactory.getLogger(CpuJdbcDao.class);

    public CpuJdbcDao() throws RepositoryException {
        try {
            connection = JdbcPool.getConnection();
            LOG.info("connection created");
        } catch (SQLException e) {
            throw new RepositoryException();
        }
    }

    @Override
    public void create(Cpu entity) {
        try {
            PreparedStatement querry = connection.prepareStatement(
                    "insert into cpu(name, price, clockspeed,"
                            + " overclocking, corecount) values(?, ?, ?, ?, ?)");
            querry.setString(1, entity.getName());
            querry.setDouble(2, entity.getPrice());
            querry.setDouble(3, entity.getClockSpeed());
            querry.setInt(4, entity.getOverClocking());
            querry.setInt(5, entity.getCoreCount());
            querry.executeUpdate();
            LOG.info("CPu created in database");
        } catch (SQLException e) {
            LOG.error("Error: ", e);
        }
    }

    @Override
    public void delete(Long id) {
        try {
            PreparedStatement querry = connection.prepareStatement(
                    "delete from cpu where id = ?");
            querry.setLong(1, id);
            querry.executeUpdate();
            LOG.info("CPU deleted from database");
        } catch (SQLException e) {
            LOG.error("Error: ", e);
        }
    }

    @Override
    public Cpu read(Long id) {
        try {
            PreparedStatement querry = connection.prepareStatement(
                    "select * from cpu where id = ?");
            querry.setLong(1, id);
            ResultSet set = querry.executeQuery();
            if (set.next()) {
                Cpu cpu = new Cpu(set.getString(2),
                        set.getDouble(3), set.getDouble(4),
                        set.getInt(5), set.getInt(6));
                cpu.setId(set.getLong(1));
                LOG.info("CPU readed by id from database");
                return cpu;
            }
        } catch (SQLException e) {
            LOG.error("Error: ", e);
        }
        return null;
    }

    @Override
    public void update(Cpu entity, Long id) {
        try {
            PreparedStatement querry = connection.prepareStatement(
                    "update cpu set name = ?, price = ?, "
                            + "clockspeed = ?, overclocking = ?, corecount = ? where id = ?");
            querry.setString(1, entity.getName());
            querry.setDouble(2, entity.getPrice());
            querry.setDouble(3, entity.getClockSpeed());
            querry.setInt(4, entity.getOverClocking());
            querry.setInt(5, entity.getCoreCount());
            querry.setLong(6, id);
            querry.executeUpdate();
            LOG.info("CPU updated in database");
        } catch (SQLException e) {
            LOG.error("Error: ", e);
        }
    }

    @Override
    public Collection<Cpu> readAll() {
        Collection<Cpu> cpus = new ArrayList<>();
        try {
            PreparedStatement querry = connection.prepareStatement("select * from cpu");
            ResultSet set = querry.executeQuery();
            while (set.next()) {
                Cpu cpu = new Cpu(set.getString(2), set.getDouble(3),
                        set.getDouble(4), set.getInt(5), set.getInt(6));
                cpu.setId(set.getLong(1));
                cpus.add(cpu);
            }
        } catch (SQLException e) {
            LOG.error("Error: ", e);
        }
        LOG.info("All CPUs readed by id from database");
        return cpus;
    }

    public Cpu readByClockSpeed(Integer clockspeed) {
        try {
            PreparedStatement querry = connection.prepareStatement("select * from cpu where clockspeed = ?");
            querry.setLong(1, clockspeed);
            ResultSet set = querry.executeQuery();
            if (set.next()) {
                Cpu cpu = new Cpu(set.getString(2),
                        set.getDouble(3), set.getDouble(4),
                        set.getInt(5), set.getInt(6));
                cpu.setId(set.getLong(1));
                LOG.info("CPU readed by clockspeed from database");
                return cpu;
            }
        } catch (SQLException e) {
            LOG.error("Error: ", e);
        }
        return null;
    }

}
