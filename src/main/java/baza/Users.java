package baza;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by dawid on 06.06.16.
 */
@ManagedBean(name="user")
@SessionScoped
@Entity(name="users")
public class Users {
    private int id;
    private String username;
    private String password;
    private String imie;
    private String firma;
    private double doZaplaty;
    private String platnosc;
    private String rola;
    private String mail;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
    @Column(name = "firma")
    public String getFirma() {
        return firma;
    }

    public void setFirma(String firma) {
        this.firma = firma;
    }

    @Basic
    @Column(name = "doZaplaty")
    public double getDoZaplaty() {
        return doZaplaty;
    }

    public void setDoZaplaty(double doZaplaty) {
        this.doZaplaty = doZaplaty;
    }

    @Basic
    @Column(name = "platnosc")
    public String getPlatnosc() {
        return platnosc;
    }

    public void setPlatnosc(String platnosc) {
        this.platnosc = platnosc;
    }

    @Basic
    @Column(name = "rola")
    public String getRola() {
        return rola;
    }

    public void setRola(String rola) {
        this.rola = rola;
    }

    @Basic
    @Column(name = "mail")
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Users users = (Users) o;

        if (id != users.id) return false;
        if (Double.compare(users.doZaplaty, doZaplaty) != 0) return false;
        if (username != null ? !username.equals(users.username) : users.username != null) return false;
        if (password != null ? !password.equals(users.password) : users.password != null) return false;
        if (imie != null ? !imie.equals(users.imie) : users.imie != null) return false;
        if (firma != null ? !firma.equals(users.firma) : users.firma != null) return false;
        if (platnosc != null ? !platnosc.equals(users.platnosc) : users.platnosc != null) return false;
        if (rola != null ? !rola.equals(users.rola) : users.rola != null) return false;
        if (mail != null ? !mail.equals(users.mail) : users.mail != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (imie != null ? imie.hashCode() : 0);
        result = 31 * result + (firma != null ? firma.hashCode() : 0);
        temp = Double.doubleToLongBits(doZaplaty);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (platnosc != null ? platnosc.hashCode() : 0);
        result = 31 * result + (rola != null ? rola.hashCode() : 0);
        result = 31 * result + (mail != null ? mail.hashCode() : 0);
        return result;
    }
}
