package tkpe.foorumi;

public class Maini {

    public static void main(String[] args) throws Exception {
        Database database = new Database("jdbc:sqlite:foorumi.db");
        AlueDao adao = new AlueDao(database);

        Alue alue = adao.findOne(1);
        System.out.println(alue.getId()
                + " " + alue.getNimi());  //ei toimi
    }
}
