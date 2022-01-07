package edu.bbte.idde.zdim1981.dao.jdbc;

import edu.bbte.idde.zdim1981.dao.MotherboardDao;
import edu.bbte.idde.zdim1981.model.Motherboard;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

@Repository
@Profile("jdbc")
@Slf4j
public class MotherboardJdbcDao implements MotherboardDao {
    @Autowired
    private ConnectionPool connectionPool;

    private Motherboard createEntityFromResult(ResultSet set) throws SQLException {
        return new Motherboard(set.getString(2),
                set.getDouble(3), set.getInt(4), set.getString(5),
                set.getInt(6), set.getLong(7));
    }

    private Motherboard getLastInserted() {
        try (Connection connection = connectionPool.getDataSource().getConnection()) {
            PreparedStatement querry = connection.prepareStatement("select * from cpu "
                    + "where id = (select MAX(id) from cpu)");
            ResultSet set = querry.executeQuery();
            if (set.next()) {
                Motherboard motherboard = createEntityFromResult(set);
                motherboard.setId(set.getLong(1));
                log.info("Last inserted motherboard readed by ID from database");
                return motherboard;
            }
        }  catch (SQLException e) {
            log.error("Error: ", e);
        }
        return null;
    }

    @Override
    public Motherboard create(Motherboard entity) {
        try (Connection connection = connectionPool.getDataSource().getConnection()) {
            PreparedStatement querry = connection.prepareStatement("insert into motherboard(name, "
                    + "price, fsb, bios, memory, cpuid) values(?, ?, ?, ?, ?, ?)");
            querry.setString(1, entity.getName());
            querry.setDouble(2, entity.getPrice());
            querry.setInt(3, entity.getFsb());
            querry.setString(4, entity.getBios());
            querry.setInt(5, entity.getMemory());
            querry.setLong(6, entity.getCpuId());
            querry.executeUpdate();
            log.info("New motherboard created in database");
            return getLastInserted();
        } catch (SQLException e) {
            log.error("Error: ", e);
        }
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        try (Connection connection = connectionPool.getDataSource().getConnection()) {
            PreparedStatement querry = connection.prepareStatement("delete from motherboard where id = ?");
            querry.setLong(1, id);
            querry.executeUpdate();
            log.info("Motherboard deleted from database");
            return true;
        } catch (SQLException e) {
            log.error("Error: ", e);
            return false;
        }
    }

    @Override
    public Motherboard read(Long id) {
        try (Connection connection = connectionPool.getDataSource().getConnection()) {
            PreparedStatement querry = connection.prepareStatement("select * from motherboard where id = ?");
            querry.setLong(1, id);
            ResultSet set = querry.executeQuery();
            if (set.next()) {
                Motherboard motherboard = createEntityFromResult(set);
                motherboard.setId(set.getLong(1));
                log.info("Motherboard readed by ID from database");
                return motherboard;
            }
        } catch (SQLException e) {
            log.error("Error: ", e);
        }
        return null;
    }

    @Override
    public void update(Motherboard entity, Long id) {
        try (Connection connection = connectionPool.getDataSource().getConnection()) {
            PreparedStatement querry = connection.prepareStatement("update motherboard set name = ?, "
                    + "price = ?, fsb = ?, bios = ?, memory = ?, cpuid = cpuid where id = ?");
            querry.setString(1, entity.getName());
            querry.setDouble(2, entity.getPrice());
            querry.setInt(3, entity.getFsb());
            querry.setString(4, entity.getBios());
            querry.setInt(5, entity.getMemory());
            querry.setLong(6, entity.getCpuId());
            querry.setLong(7, entity.getId());
            querry.executeUpdate();
            log.info("Motherboard updated in database");
        } catch (SQLException e) {
            log.error("Error: ", e);
        }
    }

    @Override
    public Collection<Motherboard> readAll() {
        Collection<Motherboard> motherboards = new ArrayList<>();
        try (Connection connection = connectionPool.getDataSource().getConnection()) {
            PreparedStatement querry = connection.prepareStatement("select * from motherboard");
            ResultSet set = querry.executeQuery();
            while (set.next()) {
                Motherboard motherboard = createEntityFromResult(set);
                motherboard.setId(set.getLong(1));
                motherboards.add(motherboard);
            }
        } catch (SQLException e) {
            log.error("Error: ", e);
        }
        log.info("All motherboards readed from database");
        return motherboards;
    }

    @Override
    public Collection<Motherboard> readByMinMemory(Integer memory) {
        Collection<Motherboard> motherboards = new ArrayList<>();
        try (Connection connection = connectionPool.getDataSource().getConnection()) {
            PreparedStatement querry = connection.prepareStatement("select * from motherboard where memory >= ?");
            querry.setInt(1, memory);
            ResultSet set = querry.executeQuery();
            if (set.next()) {
                Motherboard motherboard = createEntityFromResult(set);
                motherboard.setId(set.getLong(1));
                motherboards.add(motherboard);
            }
        } catch (SQLException e) {
            log.error("Error: ", e);
        }
        log.info("Motherboards with memory more than " + memory + " $ readed!");
        return motherboards;
    }

}
