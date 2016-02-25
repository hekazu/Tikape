package tkpe.foorumi;

public class Alue {
    private final int id;
    private String nimi;

    public Alue(int id, String nimi) {
        this.id = id;
        this.nimi = nimi;
    }

    public int getId() {
        return id;
    }

    public String getNimi() {
        return nimi;
    }
      
}
