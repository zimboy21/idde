package edu.bbte.idde.zdim1981.web;

import edu.bbte.idde.zdim1981.backend.dataaccessobject.mem.CpuShopInMemDao;
import edu.bbte.idde.zdim1981.backend.dataaccessobject.CpuShopDao;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;
import jakarta.servlet.FilterChain;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected Cookie cookie;
    protected HttpSession session;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Configuration cfg = new Configuration(new Version("2.3.31"));

        cfg.setClassForTemplateLoading(LoginServlet.class, "/");
        cfg.setDefaultEncoding("UTF-8");
        Template template = cfg.getTemplate("login.ftl");
        Map<String, Object> templateData = new ConcurrentHashMap<>();
        try {
            template.process(templateData, resp.getWriter());
        } catch (TemplateException e) {
            throw new ServletException();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user = request.getParameter("user");
        String pwd = request.getParameter("pwd");

        final String username = "admin";
        final String password = "password";

        if (username.equals(user) && password.equals(pwd)) {
            session = request.getSession();
            session.setAttribute("user", "Zimi");
            session.setMaxInactiveInterval(30 * 60);
            cookie = new Cookie("user", user);
            cookie.setMaxAge(30 * 60);
            response.addCookie(cookie);
            RequestDispatcher rd = request.getRequestDispatcher("/cputemplate");
            rd.forward(request, response);
        } else {
            Configuration cfg = new Configuration(new Version("2.3.31"));

            cfg.setClassForTemplateLoading(LoginServlet.class, "/");
            cfg.setDefaultEncoding("UTF-8");
            Template template = cfg.getTemplate("login.ftl");
            Map<String, Object> templateData = new ConcurrentHashMap<>();
            try {
                template.process(templateData, response.getWriter());
            } catch (TemplateException e) {
                throw new ServletException();
            }
        }
    }
}