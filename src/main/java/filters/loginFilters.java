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
public class loginFilters implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        String url = req.getRequestURI();

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
                filterChain.doFilter(servletRequest, servletResponse);
            }
        }

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
