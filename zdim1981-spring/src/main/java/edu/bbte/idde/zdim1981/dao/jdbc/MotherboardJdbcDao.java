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
@Profile({"jdbc", "jpa"})
@Slf4j
public class MotherboardJdbcDao implements MotherboardDao {
    @Autowired
    private ConnectionPool connectionPool;

    private Motherboard createEntityFromResult(ResultSet set) throws SQLException {
        Motherboard motherboard = new Motherboard();
        motherboard.setName(set.getString(2));
        motherboard.setPrice(set.getDouble(3));
        motherboard.setFsb(set.getInt(4));
        motherboard.setBios(set.getString(5));
        motherboard.setMemory(set.getInt(6));
        return motherboard;
    }

    @Override
    public Motherboard saveAndFlush(Motherboard entity) {
        try (Connection connection = connectionPool.getDataSource().getConnection()) {
            PreparedStatement querry = connection.prepareStatement("insert into motherboard(name, "
                    + "price, fsb, bios, memory, cpuid) values(?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            querry.setString(1, entity.getName());
            querry.setDouble(2, entity.getPrice());
            querry.setInt(3, entity.getFsb());
            querry.setString(4, entity.getBios());
            querry.setInt(5, entity.getMemory());
            querry.executeUpdate();
            log.info("New motherboard created in database");
            ResultSet ids = querry.getGeneratedKeys();
            if (ids.next()) {
                entity.setId(ids.getLong(1));
            }
            return entity;
        } catch (SQLException e) {
            log.error("Error: ", e);
        }
        return null;
    }

    @Override
    public void deleteById(Long id) {
        try (Connection connection = connectionPool.getDataSource().getConnection()) {
            PreparedStatement querry = connection.prepareStatement("delete from motherboard where id = ?");
            querry.setLong(1, id);
            querry.executeUpdate();
            log.info("Motherboard deleted from database");
        } catch (SQLException e) {
            log.error("Error: ", e);
        }
    }

    @Override
    public Motherboard getById(Long id) {
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
    public Motherboard save(Motherboard entity) {
        try (Connection connection = connectionPool.getDataSource().getConnection()) {
            PreparedStatement querry = connection.prepareStatement("update motherboard set name = ?, "
                    + "price = ?, fsb = ?, bios = ?, memory = ?, cpuid = cpuid where id = ?");
            querry.setString(1, entity.getName());
            querry.setDouble(2, entity.getPrice());
            querry.setInt(3, entity.getFsb());
            querry.setString(4, entity.getBios());
            querry.setInt(5, entity.getMemory());
            querry.setLong(7, entity.getId());
            querry.executeUpdate();
            log.info("Motherboard updated in database");
        } catch (SQLException e) {
            log.error("Error: ", e);
        }
        return null;
    }

    @Override
    public Collection<Motherboard> findAll() {
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
    public Collection<Motherboard> readByMemory(Integer memory) {
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
