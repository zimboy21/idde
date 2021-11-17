package edu.bbte.idde.zdim1981.backend.dataaccessobject.jdbc;

import edu.bbte.idde.zdim1981.backend.RepositoryException;
import edu.bbte.idde.zdim1981.backend.dataaccessobject.MotherboardDao;
import edu.bbte.idde.zdim1981.backend.model.Motherboard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class MotherboardJdbcDao implements MotherboardDao {
    private final Connection connection;
    public static final Logger LOG = LoggerFactory.getLogger(MotherboardJdbcDao.class);

    public MotherboardJdbcDao() throws RepositoryException {
        try {
            connection = JdbcPool.getConnection();
            LOG.info("connection created");
        } catch (SQLException e) {
            throw new RepositoryException();
        }
    }

    @Override
    public void create(Motherboard entity) {
        try {
            PreparedStatement querry = connection.prepareStatement("insert into motherboard(name, "
                    + "price, fsb, bios, memory, cpuid) values(?, ?, ?, ?, ?, ?)");
            querry.setString(1, entity.getName());
            querry.setDouble(2, entity.getPrice());
            querry.setInt(3, entity.getFsb());
            querry.setString(4, entity.getBios());
            querry.setInt(5, entity.getMemory());
            querry.setLong(6, entity.getCpuId());
            querry.executeUpdate();
            LOG.info("New motherboard created in database");
        } catch (SQLException e) {
            LOG.error("Error: ", e);
        }
    }

    @Override
    public void delete(Long id) {
        try {
            PreparedStatement querry = connection.prepareStatement("delete from motherboard where id = ?");
            querry.setLong(1, id);
            querry.executeUpdate();
            LOG.info("Motherboard deleted from database");
        } catch (SQLException e) {
            LOG.error("Error: ", e);
        }
    }

    @Override
    public Motherboard read(Long id) {
        try {
            PreparedStatement querry = connection.prepareStatement("select * from motherboard where id = ?");
            querry.setLong(1, id);
            ResultSet set = querry.executeQuery();
            if (set.next()) {
                Motherboard motherboard = new Motherboard(set.getString(2),
                        set.getDouble(3), set.getInt(4), set.getString(5),
                        set.getInt(6), set.getLong(7));
                motherboard.setId(set.getLong(1));
                LOG.info("Motherboard readed by ID from database");
                return motherboard;
            }
        } catch (SQLException e) {
            LOG.error("Error: ", e);
        }
        return null;
    }

    @Override
    public void update(Motherboard entity, Long id) {
        try {
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
            LOG.info("Motherboard updated in database");
        } catch (SQLException e) {
            LOG.error("Error: ", e);
        }
    }

    @Override
    public Collection<Motherboard> readAll() {
        Collection<Motherboard> motherboards = new ArrayList<>();
        try {
            PreparedStatement querry = connection.prepareStatement("select * from motherboard");
            ResultSet set = querry.executeQuery();
            while (set.next()) {
                Motherboard motherboard = new Motherboard(set.getString(2),
                        set.getDouble(3), set.getInt(4),
                        set.getString(5), set.getInt(6), set.getLong(7));
                motherboard.setId(set.getLong(1));
                motherboards.add(motherboard);
            }
        } catch (SQLException e) {
            LOG.error("Error: ", e);
        }
        LOG.info("All motherboards readed from database");
        return motherboards;
    }

    @Override
    public Collection<Motherboard> getByMaxPrice(Integer price) {
        Collection<Motherboard> motherboards = new ArrayList<>();
        try {
            PreparedStatement querry = connection.prepareStatement("select * from motherboard where price <= ?");
            querry.setInt(1, price);
            ResultSet set = querry.executeQuery();
            if (set.next()) {
                Motherboard motherboard = new Motherboard(set.getString(2),
                        set.getDouble(3), set.getInt(4), set.getString(5),
                        set.getInt(6), set.getLong(7));
                motherboard.setId(set.getLong(1));
                motherboards.add(motherboard);
            }
        } catch (SQLException e) {
            LOG.error("Error: ", e);
        }
        LOG.info("Motherboards with price under " + price + " $ readed!");
        return motherboards;
    }

}
