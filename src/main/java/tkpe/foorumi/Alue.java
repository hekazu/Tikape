package tkpe.foorumi;

import java.util.*;

public class Alue {

    private int id;
    private String nimi;
    private List<Lanka> langat;

    public Alue(String nimi) {
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
    
    public List<Lanka> getLangatSorted(){
        List<Lanka> l = getLangat();
        Collections.sort(l, 
                (l1, l2) -> l1.getTuoreinViesti().getAika()
                        .compareTo(
                                l2.getTuoreinViesti().getAika())
        );
        return l;
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
