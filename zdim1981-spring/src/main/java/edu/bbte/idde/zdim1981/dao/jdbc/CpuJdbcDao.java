package edu.bbte.idde.zdim1981.dao.jdbc;

import edu.bbte.idde.zdim1981.dao.CpuDao;
import edu.bbte.idde.zdim1981.model.Cpu;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

@Repository
@Slf4j
@Profile("jdbc")
public class CpuJdbcDao implements CpuDao {
    @Autowired
    private ConnectionPool connectionPool;

    private Cpu createEntityFromResult(ResultSet set) throws SQLException {
        return new Cpu(set.getString(2),
                set.getDouble(3), set.getDouble(4),
                set.getInt(5), set.getInt(6));
    }

    private Cpu getLastInserted() {
        try (Connection connection = connectionPool.getDataSource().getConnection()) {
            PreparedStatement querry = connection.prepareStatement("select * from cpu "
                    + "where id = (select MAX(id) from cpu)");
            ResultSet set = querry.executeQuery();
            if (set.next()) {
                Cpu cpu = createEntityFromResult(set);
                cpu.setId(set.getLong(1));
                log.info("Last inserted motherboard readed by ID from database");
                return cpu;
            }
        }  catch (SQLException e) {
            log.error("Error: ", e);
        }
        return null;
    }

    @Override
    public Cpu create(Cpu entity) {
        try (Connection connection = connectionPool.getDataSource().getConnection()) {
            PreparedStatement querry = connection.prepareStatement(
                    "insert into cpu(name, price, clockspeed,"
                            + " overclocking, corecount) values(?, ?, ?, ?, ?)");
            querry.setString(1, entity.getName());
            querry.setDouble(2, entity.getPrice());
            querry.setDouble(3, entity.getClockSpeed());
            querry.setInt(4, entity.getOverClocking());
            querry.setInt(5, entity.getCoreCount());
            querry.executeUpdate();
            log.info("CPu created in database");
            return getLastInserted();
        } catch (SQLException e) {
            log.error("Error: ", e);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        try (Connection connection = connectionPool.getDataSource().getConnection()) {
            PreparedStatement querry = connection.prepareStatement(
                    "delete from cpu where id = ?");
            querry.setLong(1, id);
            querry.executeUpdate();
            log.info("CPU deleted from database");
        } catch (SQLException e) {
            log.error("Error: ", e);
        }
    }

    @Override
    public Cpu read(Long id) {
        try (Connection connection = connectionPool.getDataSource().getConnection()) {
            PreparedStatement querry = connection.prepareStatement(
                    "select * from cpu where id = ?");
            querry.setLong(1, id);
            ResultSet set = querry.executeQuery();
            if (set.next()) {
                Cpu cpu = createEntityFromResult(set);
                cpu.setId(set.getLong(1));
                log.info("CPU readed by id from database");
                return cpu;
            }
        } catch (SQLException e) {
            log.error("Error: ", e);
        }
        return null;
    }

    @Override
    public void update(Cpu entity, Long id) {
        try (Connection connection = connectionPool.getDataSource().getConnection()) {
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
            log.info("CPU updated in database");
        } catch (SQLException e) {
            log.error("Error: ", e);
        }
    }

    @Override
    public Collection<Cpu> readAll() {
        Collection<Cpu> cpus = new ArrayList<>();
        try (Connection connection = connectionPool.getDataSource().getConnection()) {
            PreparedStatement querry = connection.prepareStatement("select * from cpu");
            ResultSet set = querry.executeQuery();
            while (set.next()) {
                Cpu cpu = createEntityFromResult(set);
                cpu.setId(set.getLong(1));
                cpus.add(cpu);
            }
        } catch (SQLException e) {
            log.error("Error: ", e);
        }
        log.info("All CPUs readed by id from database");
        return cpus;
    }

    @Override
    public Collection<Cpu> readByMinClockSpeed(Integer clockspeed) {
        ArrayList<Cpu> cpus = new ArrayList<>();
        try (Connection connection = connectionPool.getDataSource().getConnection()) {
            PreparedStatement querry = connection.prepareStatement("select * from cpu where clockspeed >= ?");
            querry.setLong(1, clockspeed);
            ResultSet set = querry.executeQuery();
            if (set.next()) {
                Cpu cpu = createEntityFromResult(set);
                cpu.setId(set.getLong(1));
                log.info("CPU with clockspeed more than " + clockspeed + " readed from database");
                cpus.add(cpu);
            }
        } catch (SQLException e) {
            log.error("Error: ", e);
        }
        return cpus;
    }
}
