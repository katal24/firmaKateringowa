package filters;


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
        User session = (User) req.getSession().getAttribute("bean1");
        System.out.println("****************** jestem w filter");
        System.out.println("****************** jestem w filter");
        if(session != null) {
            System.out.println("username:" + session.getUsername());
        }
        String url = req.getRequestURI();

        if(session == null || !session.isLogged){
            if(url.indexOf("wypisz.xhtml") >=0 || url.indexOf("logout.xhtml") >=0 || url.indexOf("wypiszadmin.xhtml") >=0 || url.indexOf("index.xhtml") >=0){
                resp.sendRedirect(req.getServletContext().getContextPath() + "/login.xhtml");
            }
            else{
                filterChain.doFilter(servletRequest,servletResponse);
            }
        } else{
            if(url.indexOf("login.xhtml")>=0 && session.isLogged){
                resp.sendRedirect(req.getServletContext().getContextPath() + "/index.xhtml");
            } else if(url.indexOf("logout.xhtml") >= 0){
                req.getSession().removeAttribute("bean1");
                resp.sendRedirect(req.getServletContext().getContextPath() + "/login.xhtml");
            }
            else{
                filterChain.doFilter(servletRequest,servletResponse);
            }
        }





    }

    public void destroy() {

    }
}
