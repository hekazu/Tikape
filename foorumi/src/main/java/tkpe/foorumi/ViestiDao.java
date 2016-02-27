package tkpe.foorumi;

import java.sql.*;
import java.util.*;
import tkpe.foorumi.*;

public class ViestiDao implements Dao<Viesti, Integer>{
    private Database database;

    public ViestiDao(Database database) {
        this.database = database;
    }

    @Override
    public Viesti findOne(Integer key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Viesti WHERE id = ?");
        stmt.setObject(1, key);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }

        int id = rs.getInt("id");
        String sisalto = rs.getString("sisalto");
        Timestamp aikaleima = rs.getTimestamp("aikaleima");
        String kirjoittaja = rs.getString("kirjoittaja");
        Lanka lanka = rs.getObject("lanka", Lanka);

       Viesti v = new Viesti(id, sisalto, aikaleima, kirjoittaja, lanka);

        rs.close();
        stmt.close();
        connection.close();

        return v;
    }
    
    @Override
    public List<Viesti> findAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Viesti> findAllIn(Collection<Integer> keys) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
