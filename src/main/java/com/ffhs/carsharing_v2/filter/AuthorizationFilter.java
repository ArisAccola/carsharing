package com.ffhs.carsharing_v2.filter;

import jakarta.faces.application.ResourceHandler;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Implementation of Authorization Filter to prevent not logged-in user to access
 * webpage by putting in the link and further preventing the caching resources to prevent dataleak
 * after user has logged of.
 *
 * @author Aris M. Accola and Andreas Schwyter
 */
@WebFilter(filterName = "AuthorizationFilter", urlPatterns = {"*.xhtml"})
public class AuthorizationFilter implements Filter {

    /**
     * Constructor of class Authorization Filter without parameters
     */
    public AuthorizationFilter() {
        super();
    }

    /**
     *
     * @param filterConfig a <code>FilterConfig</code> object containing the filter's configuration and initialization
     * parameters
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("AuthorizationFilter has been initialized");
    }


    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain chain) throws IOException, ServletException {

        try {
            HttpServletRequest request = (HttpServletRequest) req;
            HttpServletResponse response = (HttpServletResponse) res;
            HttpSession session = request.getSession(false);
            String loginURL = request.getContextPath() + "/index.xhtml";

            boolean loggedIn = (session != null) && (session.getAttribute("username") != null);
            boolean loginRequest = request.getRequestURI().equals(loginURL);
            boolean resourceRequest = request.getRequestURI().startsWith(request.getContextPath() + ResourceHandler.RESOURCE_IDENTIFIER + "/");

            if (loginRequest || loggedIn || resourceRequest) {
                /**
                 *  Prevent browser from caching restricted resources
                 *  (!resourceRequest) -> Skip JSF resources (CSS/JS/Images/etc)
                 */
                 if (!resourceRequest) {
                    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
                    response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
                    response.setDateHeader("Expires", 0); // Proxies.
                }
                chain.doFilter(request, response);
            } else {
                response.sendRedirect(loginURL);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void destroy() {
        System.out.println("AuthorizationFilter has been destroyed");
    }
}
