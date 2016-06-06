package model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by dawid on 05.06.16.
 */
@Entity(name="zamowienie")
public class Zamowienie {
    private int id;
    private String imie;
    private String data;
    private String godzina;
    private String miejsce;
    private double rachunek;
    private String status;
    private String notatka;
    private String platnosc;
    private String rodzaj;

    @Id
    @Column(name = "idZamowienia")
    public int getId() {
        return id;
    }

    public void setId(int idZamowienia) {
        this.id = idZamowienia;
    }

    @Basic
    @Column(name = "Imie")
    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    @Basic
    @Column(name = "rodzaj")
    public String getRodzaj() {
        return rodzaj;
    }

    public void setRodzaj(String rodzaj) {
        this.rodzaj = rodzaj;
    }

    @Basic
    @Column(name = "data")
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Basic
    @Column(name = "godzina")
    public String getGodzina() {
        return godzina;
    }

    public void setGodzina(String godzina) {
        this.godzina = godzina;
    }

    @Basic
    @Column(name = "miejsce")
    public String getMiejsce() {
        return miejsce;
    }

    public void setMiejsce(String miejsce) {
        this.miejsce = miejsce;
    }

    @Basic
    @Column(name = "rachunek")
    public double getRachunek() {
        return rachunek;
    }

    public void setRachunek(double rachunek) {
        this.rachunek = rachunek;
    }

    @Basic
    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "notatka")
    public String getNotatka() {
        return notatka;
    }

    public void setNotatka(String notatka) {
        this.notatka = notatka;
    }

    @Basic
    @Column(name = "platnosc")
    public String getPlatnosc() {
        return platnosc;
    }

    public void setPlatnosc(String platnosc) {
        this.platnosc = platnosc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Zamowienie that = (Zamowienie) o;

        if (id != that.id) return false;
        if (Double.compare(that.rachunek, rachunek) != 0) return false;
        if (imie != null ? !imie.equals(that.imie) : that.imie != null) return false;
        if (data != null ? !data.equals(that.data) : that.data != null) return false;
        if (godzina != null ? !godzina.equals(that.godzina) : that.godzina != null) return false;
        if (miejsce != null ? !miejsce.equals(that.miejsce) : that.miejsce != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (notatka != null ? !notatka.equals(that.notatka) : that.notatka != null) return false;
        if (platnosc != null ? !platnosc.equals(that.platnosc) : that.platnosc != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (imie != null ? imie.hashCode() : 0);
        result = 31 * result + (data != null ? data.hashCode() : 0);
        result = 31 * result + (godzina != null ? godzina.hashCode() : 0);
        result = 31 * result + (miejsce != null ? miejsce.hashCode() : 0);
        temp = Double.doubleToLongBits(rachunek);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (notatka != null ? notatka.hashCode() : 0);
        result = 31 * result + (platnosc != null ? platnosc.hashCode() : 0);
        return result;
    }
}
