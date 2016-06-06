package baza;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by dawid on 29.05.16.
 */
@Entity(name="kategorie")
public class Kategorie {
    private long id;
    private String nazwa;
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
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "nazwa")
    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Kategorie kategorie = (Kategorie) o;

        if (id != kategorie.id) return false;
        if (nazwa != null ? !nazwa.equals(kategorie.nazwa) : kategorie.nazwa != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (nazwa != null ? nazwa.hashCode() : 0);
        return result;
    }
}
