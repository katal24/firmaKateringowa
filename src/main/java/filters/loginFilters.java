package filters;


import model.DB;
import web.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dawid on 28.05.16.
 */
//@ManagedBean(name="filter")
//@SessionScoped
public class loginFilters implements Filter {
//    private boolean logout = false;
//
//    public boolean isLogout() {
//        return logout;
//    }
//
//    public String setLogout(boolean logout) {
//        System.out.println("robie wylogowanie");
//        this.logout = logout;
//        return null;
//    }

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        //   User session = (User) req.getSession().getAttribute("bean1");
        System.out.println("****************** jestem w filter");
        System.out.println("****************** jestem w filter");
//        if(session != null) {
//            System.out.println("username:" + session.getUsername());
//        }
        String url = req.getRequestURI();
        System.out.println("url: " + url);

//        if(logout==true){
//            HttpSession session = req.getSession(true);
//            session.invalidate();
//            resp.sendRedirect(req.getServletContext().getContextPath() + "/index.xhtml");
//        }

        // System.out.println("user: " + req.getUserPrincipal());
        if(DB.getStricZalogowanyUser()==null){  // uzytkownik niezalogowany
            User.action2(req.getUserPrincipal().toString());
            gotoIndex(req, resp);
        } else{     //ZALOGOWANY
            if(url.indexOf("login.xhtml")>=0 || url.indexOf("exploded/index.xhtml")>=0){ //zalogowany chce isc na strone logowania
                gotoIndex(req, resp);
            } else   //zalogowany chce sie wylogowac
            if(url.indexOf("logout.xhtml")>=0){
                DB.setZalogowanyUser(null);
                req.getSession().invalidate();
                resp.sendRedirect(req.getServletContext().getContextPath() + "/index.xhtml");
            }

            else {                     //czyli nie chce isc na strone logowania, tylko normalnie
                System.out.println("IDEEEEEEEEEEEE");
                filterChain.doFilter(servletRequest, servletResponse);
            }
        }



//        if(req.isUserInRole("Manager")) {
        //   filterChain.doFilter(servletRequest, servletResponse);
//        }

//        if(session == null || !session.isLogged){
//            if(url.indexOf("wypisz.xhtml") >=0 || url.indexOf("logout.xhtml") >=0 || url.indexOf("wypiszadmin.xhtml") >=0 || url.indexOf("index.xhtml") >=0){
//                resp.sendRedirect(req.getServletContext().getContextPath() + "/login.xhtml");
//            }
//            else{
//                filterChain.doFilter(servletRequest,servletResponse);
//            }
//        } else{
//            if(url.indexOf("login.xhtml")>=0 && session.isLogged){
//                resp.sendRedirect(req.getServletContext().getContextPath() + "/index.xhtml");
//            } else if(url.indexOf("logout.xhtml") >= 0){
//                req.getSession().removeAttribute("bean1");
//                resp.sendRedirect(req.getServletContext().getContextPath() + "/login.xhtml");
//            }
//            else{
//                filterChain.doFilter(servletRequest,servletResponse);
//            }
//        }





    }


    public void gotoIndex( HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if(req.isUserInRole("Admin") ){
            resp.sendRedirect(req.getServletContext().getContextPath() + "/admin/index.xhtml");
        } else
        if(req.isUserInRole("Manager") ){
            resp.sendRedirect(req.getServletContext().getContextPath() + "/manager/index.xhtml");
        } else
        if(req.isUserInRole("User") ){
            DB.setTopTenPotraw();
            resp.sendRedirect(req.getServletContext().getContextPath() + "/user/index.xhtml");
        } else
        if(req.isUserInRole("Dostawca") ){
            resp.sendRedirect(req.getServletContext().getContextPath() + "/dostawca/index.xhtml");
        }
    }

    public void destroy() {

    }
}
