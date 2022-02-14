package edu.bbte.idde.zdim1981.backend.dataaccessobject.jdbc;

import edu.bbte.idde.zdim1981.backend.dataaccessobject.CpuDao;
import edu.bbte.idde.zdim1981.backend.model.Cpu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class CpuJdbcDao implements CpuDao {
    public static final Logger LOG = LoggerFactory.getLogger(CpuJdbcDao.class);

    private Cpu createEntityFromResult(ResultSet set) throws SQLException {
        return new edu.bbte.idde.zdim1981.backend.model.Cpu(set.getString(3),
                set.getDouble(4), set.getDouble(5),
                set.getInt(6), set.getInt(7), set.getBoolean(2));
    }

    @Override
    public void create(edu.bbte.idde.zdim1981.backend.model.Cpu entity) {
        try (Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement querry = connection.prepareStatement(
                    "insert into cpu(name, price, clockspeed,"
                            + " overclocking, corecount, deleted) values(?, ?, ?, ?, ?, ?)");
            querry.setString(1, entity.getName());
            querry.setDouble(2, entity.getPrice());
            querry.setDouble(3, entity.getClockSpeed());
            querry.setInt(4, entity.getOverClocking());
            querry.setInt(5, entity.getCoreCount());
            querry.setBoolean(6, false);
            querry.executeUpdate();
            LOG.info("CPu created in database");
        } catch (SQLException e) {
            LOG.error("Error: ", e);
        }
    }

    @Override
    public void delete(Long id) {
        try (Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement querry = connection.prepareStatement(
                    "update cpu set deleted = true where id = ?");
            querry.setLong(1, id);
            querry.executeUpdate();
            LOG.info("CPU deleted from database");
        } catch (SQLException e) {
            LOG.error("Error: ", e);
        }
    }

    @Override
    public Cpu read(Long id) {
        try (Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement querry = connection.prepareStatement(
                    "select * from cpu where id = ? and not deleted");
            querry.setLong(1, id);
            ResultSet set = querry.executeQuery();
            if (set.next()) {
                Cpu cpu = createEntityFromResult(set);
                cpu.setId(set.getLong(1));
                LOG.info("{}", cpu.getDeleted());
                LOG.info("CPU readed by id from database");
                return cpu;
            }
        } catch (SQLException e) {
            LOG.error("Error: ", e);
        }
        return null;
    }

    @Override
    public void update(edu.bbte.idde.zdim1981.backend.model.Cpu entity, Long id) {
        try (Connection connection = ConnectionPool.getConnection()) {
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
        Collection<edu.bbte.idde.zdim1981.backend.model.Cpu> cpus = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement querry = connection.prepareStatement("select * from cpu where not deleted");
            ResultSet set = querry.executeQuery();
            while (set.next()) {
                edu.bbte.idde.zdim1981.backend.model.Cpu cpu = createEntityFromResult(set);
                cpu.setId(set.getLong(1));
                cpus.add(cpu);
            }
        } catch (SQLException e) {
            LOG.error("Error: ", e);
        }
        LOG.info("All CPUs readed by id from database");
        return cpus;
    }

    @Override
    public Collection<Cpu> readByMinClockSpeed(Integer clockspeed) {
        ArrayList<edu.bbte.idde.zdim1981.backend.model.Cpu> cpus = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement querry =
                    connection.prepareStatement("select * from cpu where clockspeed >= ? and not deleted");
            querry.setLong(1, clockspeed);
            ResultSet set = querry.executeQuery();
            if (set.next()) {
                edu.bbte.idde.zdim1981.backend.model.Cpu cpu = createEntityFromResult(set);
                cpu.setId(set.getLong(1));
                LOG.info("CPU with clockspeed more than " + clockspeed + " readed from database");
                cpus.add(cpu);
            }
        } catch (SQLException e) {
            LOG.error("Error: ", e);
        }
        return cpus;
    }

    @Override
    public Cpu readEvenIfDeleted(Long id) {
        try (Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement querry = connection.prepareStatement(
                    "select * from cpu where id = ?");
            querry.setLong(1, id);
            ResultSet set = querry.executeQuery();
            if (set.next()) {
                Cpu cpu = createEntityFromResult(set);
                cpu.setId(set.getLong(1));
                LOG.info("{}", cpu.getDeleted());
                LOG.info("CPU readed by id from database");
                return cpu;
            }
        } catch (SQLException e) {
            LOG.error("Error: ", e);
        }
        return null;
    }

}
