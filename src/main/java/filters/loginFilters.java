package filters;


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


        if(req.isUserInRole("Admin") && !(url.indexOf("admin")>=0)){
            System.out.println("Wiem, ze jestem admin");
            resp.sendRedirect(req.getServletContext().getContextPath() + "/admin/index.xhtml");
        } else
        if(req.isUserInRole("Manager") && !(url.indexOf("manager")>=0)){
            resp.sendRedirect(req.getServletContext().getContextPath() + "/manager/index.xhtml");
        } else
        if(req.isUserInRole("User") && !(url.indexOf("user")>=0)){
            resp.sendRedirect(req.getServletContext().getContextPath() + "/user/index.xhtml");
        } else
        if(req.isUserInRole("Dostawca") && !(url.indexOf("dostawca")>=0)){
            resp.sendRedirect(req.getServletContext().getContextPath() + "/dostawca/index.xhtml");
        } else{
            filterChain.doFilter(servletRequest, servletResponse);
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

    public void destroy() {

    }
}
