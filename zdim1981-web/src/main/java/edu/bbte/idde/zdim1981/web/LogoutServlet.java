package edu.bbte.idde.zdim1981.web;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();

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