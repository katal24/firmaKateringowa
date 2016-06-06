package baza;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by dawid on 29.05.16.
 */
@Entity(name="skladniki")
public class Skladniki {
    private long idskladnika;
    private long idpotrawy;
    private String nazwaSkladnika;
    private String ilosc;
    private boolean editable;

    @Basic
    @Column(name = "editable")
    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }




    @Id
    @Column(name = "idskladnika")
    public long getIdskladnika() {
        return idskladnika;
    }

    public void setIdskladnika(long idskladnika) {
        this.idskladnika = idskladnika;
    }

    @Basic
    @Column(name = "idpotrawy")
    public long getIdpotrawy() {
        return idpotrawy;
    }

    public void setIdpotrawy(long idpotrawy) {
        this.idpotrawy = idpotrawy;
    }

    @Basic
    @Column(name = "nazwaSkladnika")
    public String getNazwaSkladnika() {
        return nazwaSkladnika;
    }

    public void setNazwaSkladnika(String nazwaSkladnika) {
        this.nazwaSkladnika = nazwaSkladnika;
    }

    @Basic
    @Column(name = "ilosc")
    public String getIlosc() {
        return ilosc;
    }

    public void setIlosc(String ilosc) {
        this.ilosc = ilosc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Skladniki skladniki = (Skladniki) o;

        if (idskladnika != skladniki.idskladnika) return false;
        if (idpotrawy != skladniki.idpotrawy) return false;
        if (nazwaSkladnika != null ? !nazwaSkladnika.equals(skladniki.nazwaSkladnika) : skladniki.nazwaSkladnika != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (idskladnika ^ (idskladnika >>> 32));
        result = 31 * result + (int) (idpotrawy ^ (idpotrawy >>> 32));
        result = 31 * result + (nazwaSkladnika != null ? nazwaSkladnika.hashCode() : 0);
        result = 31 * result + (ilosc != null ? ilosc.hashCode() : 0);
        return result;
    }
}
