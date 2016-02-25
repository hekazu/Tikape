package tkpe.foorumi;

import java.sql.*;

public class Viesti {
    private int id;
    private String sisalto;
    private Date aikaleima;
    private String kirjoittaja;
    private Lanka lanka;

    public Viesti(int id, String sisalto, Date aikaleima, String kirjoittaja, Lanka lanka) {
        this.id = id;
        this.sisalto = sisalto;
        this.aikaleima = aikaleima;
        this.kirjoittaja = kirjoittaja;
        this.lanka = lanka;
    }

    public int getId() {
        return id;
    }

    public String getSisalto() {
        return sisalto;
    }

    public Date getAikaleima() {
        return aikaleima;
    }

    public String getKirjoittaja() {
        return kirjoittaja;
    }

    public Lanka getLanka() {
        return lanka;
    }
    
    
}

