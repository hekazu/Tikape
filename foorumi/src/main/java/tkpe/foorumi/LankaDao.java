package tkpe.foorumi;

import java.sql.*;
import java.util.*;

public class LankaDao implements Dao<Lanka, Integer> {

    private Database database;
    private Dao<Alue, Integer> alueDao;

    public LankaDao(Database database, Dao<Alue, Integer> alueDao) {
        this.database = database;
        this.alueDao = alueDao;
    }

    @Override
    public Lanka findOne(Integer key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Lanka WHERE id = ?");
        stmt.setObject(1, key);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }

        Integer id = rs.getInt("id");
        String otsikko = rs.getString("otsikko");
        Integer alue = rs.getInt("alue");

        Lanka l = new Lanka(otsikko);

        rs.close();
        stmt.close();
        connection.close();

        l.setId(id);
        l.setAlue(this.alueDao.findOne(alue));

        return l;
    }

    @Override
    public List<Lanka> findAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Lanka> findAllIn(Collection<Integer> keys) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
