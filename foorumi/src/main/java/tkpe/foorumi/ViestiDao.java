package tkpe.foorumi;

import java.sql.*;
import java.util.*;

public class ViestiDao implements Dao<Viesti, Integer> {

    private Database database;
    private Dao<Lanka, Integer> lankaDao;

    public ViestiDao(Database database, Dao<Lanka, Integer> lankaDao) {
        this.database = database;
        this.lankaDao = lankaDao;
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

        Viesti v = new Viesti(sisalto, kirjoittaja);

        v.setId(id);
        v.setAika(aikaleima);

        int fk = rs.getInt("lanka");

        rs.close();
        stmt.close();
        connection.close();

        v.setLanka(this.lankaDao.findOne(fk));

        return v;
    }

    @Override
    public List<Viesti> findAll() throws SQLException {
        Connection con = database.getConnection();
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM Viesti");
        ResultSet rs = stmt.executeQuery();

        Map<Integer, List<Viesti>> viestienLangat = new HashMap<>();

        List<Viesti> viestit = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt("id");
            String sisalto = rs.getString("sisalto");
            Timestamp aikaleima = rs.getTimestamp("aikaleima");
            String kirjoittaja = rs.getString("kirjoittaja");

            Viesti v = new Viesti(sisalto, kirjoittaja);
            v.setId(id);
            v.setAika(aikaleima);

            viestit.add(v);

            int fk = rs.getInt("lanka");

            if (!viestienLangat.containsKey(fk)) {
                viestienLangat.put(fk, new ArrayList<>());
            }
            viestienLangat.get(fk).add(v);
        }

        rs.close();
        stmt.close();
        con.close();

        for (Lanka lanka : this.lankaDao.findAll()) {
            if (!viestienLangat.containsKey(lanka.getId())) {
                continue;
            }

            for (Viesti viesti : viestienLangat.get(lanka.getId())) {
                viesti.setLanka(lanka);
            }
        }

        return viestit;
    }

    @Override
    public void delete(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Viesti> findAllIn(Collection<Integer> keys) throws SQLException {
        if (keys.isEmpty()) {
            return new ArrayList<>();
        }

        StringBuilder muuttujat = new StringBuilder("?");
        for (int i = 1; i < keys.size(); i++) {
            muuttujat.append(", ?");
        }

        Connection con = database.getConnection();
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM Viesti WHERE id IN (" + muuttujat + ")");
        int laskuri = 1;
        for (Integer key : keys) {
            stmt.setObject(laskuri, key);
            laskuri++;
        }
        ResultSet rs = stmt.executeQuery();

        Map<Integer, List<Viesti>> viestienLangat = new HashMap<>();

        List<Viesti> viestit = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt("id");
            String sisalto = rs.getString("sisalto");
            Timestamp aikaleima = rs.getTimestamp("aikaleima");
            String kirjoittaja = rs.getString("kirjoittaja");

            Viesti v = new Viesti(sisalto, kirjoittaja);
            v.setId(id);
            v.setAika(aikaleima);

            viestit.add(v);

            int fk = rs.getInt("lanka");

            if (!viestienLangat.containsKey(fk)) {
                viestienLangat.put(fk, new ArrayList<>());
            }
            viestienLangat.get(fk).add(v);
        }

        rs.close();
        stmt.close();
        con.close();

        for (Lanka lanka : this.lankaDao.findAll()) {
            if (!viestienLangat.containsKey(lanka.getId())) {
                continue;
            }

            for (Viesti viesti : viestienLangat.get(lanka.getId())) {
                viesti.setLanka(lanka);
            }
        }

        return viestit;
    }
}
