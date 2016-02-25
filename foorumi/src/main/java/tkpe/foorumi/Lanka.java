package tkpe.foorumi;

public class Lanka {
    public final int id;
    private String otsikko;
    private Alue alue;

    public Lanka(int id, String otsikko, Alue alue) {
        this.id = id;
        this.otsikko = otsikko;
        this.alue = alue;
    }

    public int getId() {
        return id;
    }

    public String getOtsikko() {
        return otsikko;
    }

    public Alue getAlue() {
        return alue;
    }   
}


