package web;

import model.DB;
import org.hibernate.Query;
import org.hibernate.Session;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;

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

        Session session = DB.getSession();
        Query query = session.createQuery("select id from users u where u.username = :name and u.password = :pass").setString("name", this.getUsername()).setString("pass", this.getPassword());

        isLogged = (query.uniqueResult() != null);
        System.out.println("ISLOGGED======" + isLogged);

        if(isLogged) {

//            Query query = session.createSQLQuery("SELECT nazwa from kategorie");
//            ArrayList<String> namesList = (ArrayList<String>) query.list();

            Query roles =  session.createQuery("select role from user_roles u where u.username = :name").setString("name", this.getUsername());
            ArrayList<String> rolesList = (ArrayList<String>) roles.list();
            System.out.println(rolesList.get(0) + " to jest ROLA");
            DB.getUserFromBase((Integer) query.uniqueResult());
            System.out.println("PObrałem całego usera z bazy");

            session.close();
            return "admin/index.xhtml?faces-redirect=true";

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


//
//if(session == null && !(url.indexOf("login.xhtml")>=0)){
//        System.out.println("Sesja jest nullem lub ide z loginu");
////            if(url.indexOf("wypisz.xhtml") >=0 || url.indexOf("logout.xhtml") >=0 || url.indexOf("wypiszadmin.xhtml") >=0 || url.indexOf("index.xhtml") >=0){
//        resp.sendRedirect(req.getServletContext().getContextPath() + "/login.xhtml");
//        }
//
//        if(req.isUserInRole("Admin")){
//        System.out.println("Wiem, ze jestem admin");
//
//        resp.sendRedirect(req.getServletContext().getContextPath() + "/admin/index.xhtml");
//        } else
//        if(req.isUserInRole("Manager")){
//        resp.sendRedirect(req.getServletContext().getContextPath() + "/manager/index.xhtml");
//        } else
//        if(req.isUserInRole("User")){
//        resp.sendRedirect(req.getServletContext().getContextPath() + "/user/index.xhtml");
//        } else
//        if(req.isUserInRole("Dostawca")){
//        resp.sendRedirect(req.getServletContext().getContextPath() + "/dostawca/index.xhtml");
//        } else{
//        filterChain.doFilter(servletRequest, servletResponse);
//        }

