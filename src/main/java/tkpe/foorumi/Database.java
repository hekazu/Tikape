package tkpe.foorumi;

import java.sql.*;
import java.util.*;
import java.net.*;

public class Database<T> {

    private String databaseAddress;
	private Connection connection;
	private boolean debug;

    public Database(String databaseAddress) throws ClassNotFoundException {
        this.databaseAddress = databaseAddress;
        
        init();
    }

	public void setDebugMode(boolean d) {
        debug = d;
    }

	private void debug(ResultSet rs) throws SQLException {
        int columns = rs.getMetaData().getColumnCount();
        for (int i = 0; i < columns; i++) {
            System.out.print(
                    rs.getObject(i + 1) + ":"
                    + rs.getMetaData().getColumnName(i + 1) + "  ");
        }

        System.out.println();
    }

	public List<T> queryAndCollect(String query, Collector<T> col) throws SQLException {
        List<T> rows = new ArrayList<>();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            if (debug) {
                System.out.println("---");
                System.out.println(query);
                debug(rs);
                System.out.println("---");
            }

            rows.add(col.collect(rs));
        }

        rs.close();
        stmt.close();
        return rows;
    }
    
    private void init() {
        List<String> lauseet = null;
        if (this.databaseAddress.contains("postgres")) {
            lauseet = postgresLauseet();
        } else {
            lauseet = sqliteLauseet();
        }
        
        try (Connection conn = getConnection()) {
            Statement st = conn.createStatement();

            for (String lause : lauseet) {
                System.out.println("Running command >> " + lause);
                st.executeUpdate(lause);
            }

        } catch (Throwable t) {
            System.out.println("Error >> " + t.getMessage());
        }
    }

    public Connection getConnection() throws SQLException {
        if (this.databaseAddress.contains("postgres")) {
            try {
                URI dbUri = new URI(databaseAddress);

                String username = dbUri.getUserInfo().split(":")[0];
                String password = dbUri.getUserInfo().split(":")[1];
                String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

                return DriverManager.getConnection(dbUrl, username, password);
            } catch (Throwable t) {
                System.out.println("Error: " + t.getMessage());
                t.printStackTrace();
            }
        }

        return DriverManager.getConnection(databaseAddress);
    }

    private List<String> postgresLauseet() {
        ArrayList<String> lista = new ArrayList<>();
        
        lista.add("CREATE TABLE Alue (id SERIAL PRIMARY KEY, nimi varchar(50);");
        lista.add("CREATE TABLE Lanka (id SERIAL PRIMARY KEY, otsikko varchar(50) NOT NULL, alue integer NOT NULL, FOREIGN KEY(alue) REFERENCES Alue(id));");
        lista.add("CREATE TABLE Viesti (id SERIAL PRIMARY KEY, sisalto varchar(1000) NOT NULL, aikaleima timestamp DEFAULT CURRENT_TIMESTAMP, kirjoittaja varchar(30) NOT NULL, lanka integer NOT NULL, FOREIGN KEY(lanka) REFERENCES Lanka(id));");
        
        lista.add("INSERT INTO Alue (nimi) VALUES (\"Hevoset\");");
        lista.add("INSERT INTO Alue (nimi) VALUES (\"Kissat\");");
        lista.add("INSERT INTO Alue (nimi) VALUES (\"Koirat\");");
        lista.add("INSERT INTO Alue (nimi) VALUES (\"Kuhat\");");
        lista.add("INSERT INTO Alue (nimi) VALUES (\"Särjet\");");
        lista.add("INSERT INTO Alue (nimi) VALUES (\"Puput\");");
        
        lista.add("INSERT INTO Lanka (otsikko, alue) VALUES (\"Hevoset on niiiiiin ihaniii!!11\", 1);");
        lista.add("INSERT INTO Lanka (otsikko, alue) VALUES (\"Miksie farmig simulatorissa ol hevozia??\", 1);");
        lista.add("INSERT INTO Lanka (otsikko, alue) VALUES (\"KISSSOJA!\", 2);");
        lista.add("INSERT INTO Lanka (otsikko, alue) VALUES (\"Vaan kuhajutut\", 5);");
        lista.add("INSERT INTO Lanka (otsikko, alue) VALUES (\"En tajuu oikeesti auttakaa\", 6);");
        
        lista.add("INSERT INTO Viesti (sisalto, lanka, kirjoittaja) VALUES (\"Siis oikeesti mä oon aina tykänny pupuista jo ennenku ne oli pop\", 5, \"puputtaja\");");
        lista.add("INSERT INTO Viesti (sisalto, lanka, kirjoittaja) VALUES (\"Mulla on oma hevonen lälläslää\", 1, \"Heppatytt05\");");
        lista.add("INSERT INTO Viesti (sisalto, lanka, kirjoittaja) VALUES (\"kuha on särki\", 4, \"tekispä\");");
        lista.add("INSERT INTO Viesti (sisalto, lanka, kirjoittaja) VALUES (\"Minkä takia farming simulaattorissa ei ole hevosia? Se peli olis just paras ja pelaisin ihan kokoajan siis ainaki 48/7 hei pliis voix joku hoitaa????!!\", 2, \"f4rmvi11e4eva\");");
        lista.add("INSERT INTO Viesti (sisalto, lanka, kirjoittaja) VALUES (\"myydään kolme kissanpentua soita numeroon 0405556666\", 3, \"anonymousäläsoita\");");
        lista.add("INSERT INTO Viesti (sisalto, lanka, kirjoittaja) VALUES (\"mee pois\", 1, \"kartunviskoja\");");
        
        return lista;
    }

    private List<String> sqliteLauseet() {
        ArrayList<String> lista = new ArrayList<>();
        
        lista.add("CREATE TABLE Alue (id integer PRIMARY KEY, nimi varchar(50));");
        lista.add("CREATE TABLE Lanka (id integer PRIMARY KEY, otsikko varchar(50) NOT NULL, alue integer NOT NULL, FOREIGN KEY(alue) REFERENCES Alue(id));");
        lista.add("CREATE TABLE Viesti (id integer PRIMARY KEY, sisalto varchar(1000) NOT NULL, aikaleima timestamp DEFAULT CURRENT_TIMESTAMP, kirjoittaja varchar(30) NOT NULL, lanka integer NOT NULL, FOREIGN KEY(lanka) REFERENCES Lanka(id));");
        
        lista.add("INSERT INTO Alue (nimi) VALUES (\"Hevoset\");");
        lista.add("INSERT INTO Alue (nimi) VALUES (\"Kissat\");");
        lista.add("INSERT INTO Alue (nimi) VALUES (\"Koirat\");");
        lista.add("INSERT INTO Alue (nimi) VALUES (\"Kuhat\");");
        lista.add("INSERT INTO Alue (nimi) VALUES (\"Särjet\");");
        lista.add("INSERT INTO Alue (nimi) VALUES (\"Puput\");");
        
        lista.add("INSERT INTO Lanka (otsikko, alue) VALUES (\"Hevoset on niiiiiin ihaniii!!11\", 1);");
        lista.add("INSERT INTO Lanka (otsikko, alue) VALUES (\"Miksie farmig simulatorissa ol hevozia??\", 1);");
        lista.add("INSERT INTO Lanka (otsikko, alue) VALUES (\"KISSSOJA!\", 2);");
        lista.add("INSERT INTO Lanka (otsikko, alue) VALUES (\"Vaan kuhajutut\", 5);");
        lista.add("INSERT INTO Lanka (otsikko, alue) VALUES (\"En tajuu oikeesti auttakaa\", 6);");
        
        lista.add("INSERT INTO Viesti (sisalto, lanka, kirjoittaja) VALUES (\"Siis oikeesti mä oon aina tykänny pupuista jo ennenku ne oli pop\", 5, \"puputtaja\");");
        lista.add("INSERT INTO Viesti (sisalto, lanka, kirjoittaja) VALUES (\"Mulla on oma hevonen lälläslää\", 1, \"Heppatytt05\");");
        lista.add("INSERT INTO Viesti (sisalto, lanka, kirjoittaja) VALUES (\"kuha on särki\", 4, \"tekispä\");");
        lista.add("INSERT INTO Viesti (sisalto, lanka, kirjoittaja) VALUES (\"Minkä takia farming simulaattorissa ei ole hevosia? Se peli olis just paras ja pelaisin ihan kokoajan siis ainaki 48/7 hei pliis voix joku hoitaa????!!\", 2, \"f4rmvi11e4eva\");");
        lista.add("INSERT INTO Viesti (sisalto, lanka, kirjoittaja) VALUES (\"myydään kolme kissanpentua soita numeroon 0405556666\", 3, \"anonymousäläsoita\");");
        lista.add("INSERT INTO Viesti (sisalto, lanka, kirjoittaja) VALUES (\"mee pois\", 1, \"kartunviskoja\");");
        
        return lista;
    }
}