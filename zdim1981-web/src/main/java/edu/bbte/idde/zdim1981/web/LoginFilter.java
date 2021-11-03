package edu.bbte.idde.zdim1981.web;

import jakarta.servlet.FilterChain;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@WebFilter("/*")
public class LoginFilter extends HttpFilter {
    private static final Logger LOG = LoggerFactory.getLogger(LoginFilter.class);
    private LoginServlet loginservlet = new LoginServlet();
    private HttpSession session;

    protected void doFilter(HttpServletRequest request,
                            HttpServletResponse response, FilterChain chain) throws  ServletException, IOException {
        session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            RequestDispatcher rd = request.getRequestDispatcher("/login");
            rd.forward(request, response);
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("/cputemplate");
            rd.forward(request, response);
        }
    }
}
