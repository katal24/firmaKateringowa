package web;

import model.DB;
import org.hibernate.Query;
import org.hibernate.Session;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

/**
 * Created by dawid on 28.05.16.
 */
@ManagedBean(name="bean1")
@SessionScoped
public class User implements Serializable {
    private String username;
    private String password;
    private String role;
    public boolean isLogged = false;


    public String action(){

        //sprawdzic czy poprawne dane
        //todo patrz wyzej

        Session session = DB.getSession();
        Query query = session.createQuery("select id from users u where u.username = :name and u.password = :pass").setString("name", this.getUsername()).setString("pass", this.getPassword());

        isLogged = (query.uniqueResult() != null);
        System.out.println("ISLOGGED======" + isLogged);

        if(isLogged) {
            DB.getUserFromBase((Integer) query.uniqueResult());
            System.out.println("PObrałem całego usera z bazy");

            session.close();
            return "index.xhtml?faces-redirect=true";

        }
        else {
            session.close();
            return null;
        }
    }

    public User() {
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public boolean isLogged() {
        return isLogged;
    }

    public void setLogged(boolean logged) {
        isLogged = logged;
    }
}
