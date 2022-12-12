package com.ffhs.carsharing_v2.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;


/**
 * Java Filter to prevent access to files of folder sub-folder "admin"
 * containing the admin panel
 *
 * @author Aris M. Accola and Andreas Schwyter
 */
@WebFilter(filterName = "adminfilter", urlPatterns = {"/admin/*"})
public class AdminFilter implements Filter {

    /**
     * Constructor of class Admin Filer
     */
    public AdminFilter() {
        super();
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        try {
            HttpServletRequest reqt = (HttpServletRequest) request;
            HttpServletResponse resp = (HttpServletResponse) response;
            HttpSession ses = reqt.getSession(false);

            String user = ses.getAttribute("username").toString();

            if (user.equals("admin")) {
                chain.doFilter(request, response);
            } else {
                resp.sendRedirect(reqt.getContextPath() + "/invalid.xhtml");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void destroy() {

    }


}

