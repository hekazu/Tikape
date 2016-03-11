package tkpe.foorumi;

import java.sql.*;
import java.time.LocalDateTime;

public class Viesti {
    private int id;
    private String sisalto;
    private Timestamp aika;
    private String kirjoittaja;
    private Lanka lanka;

    public Viesti(String sisalto, String kirjoittaja) {
        this.sisalto = sisalto;
        this.aika = Timestamp.valueOf(LocalDateTime.now());
        this.kirjoittaja = kirjoittaja;
    }

    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public String getSisalto() {
        return sisalto;
    }
    
    public void setSisalto(String sisalto) {
        this.sisalto = sisalto;
    }

    public Timestamp getAika() {
        return aika;
    }
    
    public void setAika(Timestamp aika) {
        this.aika = aika;
    }

    public String getKirjoittaja() {
        return kirjoittaja;
    }
    
    public void setKirjoittaja(String kirjoittaja) {
        this.kirjoittaja = kirjoittaja;
    }

    public Lanka getLanka() {
        return lanka;
    }
    
    public void setLanka(Lanka lanka) {
        this.lanka = lanka;
    }
}
