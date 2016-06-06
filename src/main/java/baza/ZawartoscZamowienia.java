package baza;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by dawid on 30.05.16.
 */
@Entity(name="zawartoscZamowienia")
public class ZawartoscZamowienia {
    private int idZamowienia;
    private long idPotrawy;
    private long id;

    @Basic
    @Column(name = "idZamowienia")
    public int getIdZamowienia() {
        return idZamowienia;
    }

    public void setIdZamowienia(int idZamowienia) {
        this.idZamowienia = idZamowienia;
    }

    @Basic
    @Column(name = "idPotrawy")
    public long getIdPotrawy() {
        return idPotrawy;
    }

    public void setIdPotrawy(long idPotrawy) {
        this.idPotrawy = idPotrawy;
    }

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ZawartoscZamowienia that = (ZawartoscZamowienia) o;

        if (idZamowienia != that.idZamowienia) return false;
        if (idPotrawy != that.idPotrawy) return false;
        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idZamowienia;
        result = 31 * result + (int) (idPotrawy ^ (idPotrawy >>> 32));
        result = 31 * result + (int) (id ^ (id >>> 32));
        return result;
    }
}
