package tkpe.foorumi;

import java.util.*;

public class Alue {

    private final int id;
    private String nimi;
    private List<Lanka> langat;

    public Alue(int id, String nimi) {
        this.id = id;
        this.nimi = nimi;
    }

    public Alue(int id, String nimi, List<Lanka> langat) {
        this.id = id;
        this.nimi = nimi;
        this.langat = langat;
    }

    public int getId() {
        return id;
    }

    public String getNimi() {
        return nimi;
    }

    public List<Lanka> getLangat() {
        return langat;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }    
}
