package tkpe.foorumi;

import java.util.*;

public class Lanka {

    public final int id;
    private String otsikko;
    private Alue alue;
    private List<Viesti> viestit;

    public Lanka(int id, String otsikko) {
        this.id = id;
        this.otsikko = otsikko;
    }

    public Lanka(int id, String otsikko, Alue alue) {
        this.id = id;
        this.otsikko = otsikko;
        this.alue = alue;
    }

    public Lanka(int id, String otsikko, List<Viesti> viestit) {
        this.id = id;
        this.otsikko = otsikko;
        this.viestit = viestit;
    }

    public Lanka(int id, String otsikko, Alue alue, List<Viesti> viestit) {
        this.id = id;
        this.otsikko = otsikko;
        this.alue = alue;
        this.viestit = viestit;
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

    public List<Viesti> getViestit() {
        return viestit;
    }

    public void setAlue(Alue alue) {
        this.alue = alue;
    }

    public void lisaaViesti(Viesti v) {
        viestit.add(v);
    }

    public void setOtsikko(String otsikko) {
        this.otsikko = otsikko;
    }
}


