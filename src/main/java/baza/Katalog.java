package baza;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by dawid on 30.05.16.
 */
@Entity(name="katalog")
public class Katalog {
    private int idZamowienia;
    private int idKlienta;
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
    @Column(name = "idKlienta")
    public int getIdKlienta() {
        return idKlienta;
    }

    public void setIdKlienta(int idKlienta) {
        this.idKlienta = idKlienta;
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

        Katalog katalog = (Katalog) o;

        if (idZamowienia != katalog.idZamowienia) return false;
        if (idKlienta != katalog.idKlienta) return false;
        if (id != katalog.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idZamowienia;
        result = 31 * result + idKlienta;
        result = 31 * result + (int) (id ^ (id >>> 32));
        return result;
    }
}
