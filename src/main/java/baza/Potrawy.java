package baza;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by dawid on 06.06.16.
 */
@XmlRootElement
@Entity(name="potrawy")
public class Potrawy {
   @XmlElement private long id;
    @XmlElement private long idkategoria;
    @XmlElement private String nazwaPotrawy;
    @XmlElement  private double cena;
    @XmlElement  private int ileZamowien;
    @XmlElement  private boolean editable;
    @XmlElement  private String isInMenu;
    @XmlElement  private String zamawiajacy;


    public Potrawy(long idkategoria, String nazwaPotrawy, double cena) {
        this.idkategoria = idkategoria;
        this.nazwaPotrawy = nazwaPotrawy;
        this.cena = cena;
        this.isInMenu = "0";
    }

    public Potrawy() {
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
    @Column(name = "idkategoria")
    public long getIdkategoria() {
        return idkategoria;
    }

    public void setIdkategoria(long idkategoria) {
        this.idkategoria = idkategoria;
    }

    @Basic
    @Column(name = "nazwaPotrawy")
    public String getNazwaPotrawy() {
        return nazwaPotrawy;
    }

    public void setNazwaPotrawy(String nazwaPotrawy) {
        this.nazwaPotrawy = nazwaPotrawy;
    }

    @Basic
    @Column(name = "cena")
    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    @Basic
    @Column(name = "ileZamowien")
    public int getIleZamowien() {
        return ileZamowien;
    }

    public void setIleZamowien(int ileZamowien) {
        this.ileZamowien = ileZamowien;
    }

    @Basic
    @Column(name = "editable")
    public boolean getEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    @Basic
    @Column(name = "isInMenu")
    public String getIsInMenu() {
        return isInMenu;
    }

    public void setIsInMenu(String isInMenu) {
        this.isInMenu = isInMenu;
    }

    @Basic
    @Column(name = "zamawiajacy")
    public String getZamawiajacy() {
        return zamawiajacy;
    }

    public void setZamawiajacy(String zamawiajacy) {
        this.zamawiajacy = zamawiajacy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Potrawy potrawy = (Potrawy) o;

        if (id != potrawy.id) return false;
        if (idkategoria != potrawy.idkategoria) return false;
        if (Double.compare(potrawy.cena, cena) != 0) return false;
        if (ileZamowien != potrawy.ileZamowien) return false;
        if (editable != potrawy.editable) return false;
        if (nazwaPotrawy != null ? !nazwaPotrawy.equals(potrawy.nazwaPotrawy) : potrawy.nazwaPotrawy != null)
            return false;
        if (isInMenu != null ? !isInMenu.equals(potrawy.isInMenu) : potrawy.isInMenu != null) return false;
        return !(zamawiajacy != null ? !zamawiajacy.equals(potrawy.zamawiajacy) : potrawy.zamawiajacy != null);

    }

    @Override
    public String toString() {
        return "Potrawy{" +
                "id=" + id +
                ", idkategoria=" + idkategoria +
                ", nazwaPotrawy='" + nazwaPotrawy + '\'' +
                ", cena=" + cena +
                ", ileZamowien=" + ileZamowien +
                ", editable=" + editable +
                ", isInMenu='" + isInMenu + '\'' +
                ", zamawiajacy='" + zamawiajacy + '\'' +
                '}';
    }
}
