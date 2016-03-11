package tkpe.foorumi;

import java.util.*;

public class Lanka {

    public int id;
    private String otsikko;
    private Alue alue;
    private List<Viesti> viestit;

    public Lanka(String otsikko) {
        this.otsikko = otsikko;
        this.viestit = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getOtsikko() {
        return otsikko;
    }
    
    public void setOtsikko(String otsikko) {
        this.otsikko = otsikko;
    }

    public List<Viesti> getViestit() {
        return viestit;
    }

    public void setViestit(List<Viesti> viestit) {
        this.viestit = viestit;
    }
    
    public Alue getAlue() {
        return alue;
    }

    public void setAlue(Alue alue) {
        this.alue = alue;
    }
}
