package tkpe.foorumi;

public class Maini {

    public static void main(String[] args) throws Exception {
        Database database = new Database("jdbc:sqlite:foorumi.db");
        AlueDao alueDao = new AlueDao(database);
        LankaDao lankaDao = new LankaDao(database, alueDao);
        ViestiDao viestiDao = new ViestiDao(database, lankaDao);

        Viesti viesti = viestiDao.findOne(4);
        System.out.println("ID: " + viesti.getId() + ". Kirjoittaja: " + viesti.getKirjoittaja() + " " + viesti.getAika() + ", Viesti: " + viesti.getSisalto());
        // toimii muilla luokilla vastaava paitsi täällä ViestiDaolla. Ongelma on ViestiDao:n findOne-metodin TimeStamp-oliossa jollain tasolla.
    }
}
