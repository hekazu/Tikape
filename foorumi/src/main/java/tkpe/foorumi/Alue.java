package tkpe.foorumi;

import java.util.*;

public class Alue {

    private int id;
    private String nimi;
    private List<Lanka> langat;

    public Alue(int id, String nimi) {
        this.id = id;
        this.nimi = nimi;
    }

    public Alue(String nimi, List<Lanka> langat) {
        this.nimi = nimi;
        this.langat = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Lanka> getLangat() {
        return langat;
    }

    public void setLangat(List<Lanka> langat) {
        this.langat = langat;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }
}
