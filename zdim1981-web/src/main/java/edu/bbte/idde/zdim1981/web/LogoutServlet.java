package edu.bbte.idde.zdim1981.web;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    private Template template;

    @Override
    public void init() throws ServletException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
        cfg.setDefaultEncoding("UTF-8");
        cfg.setClassForTemplateLoading(HardwareTemplateServlet.class, "/");
        try {
            template = cfg.getTemplate("login.ftl");
        } catch (IOException e) {
            throw new ServletException();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        Map<String, Object> templateData = new ConcurrentHashMap<>();
        try {
            template.process(templateData, response.getWriter());
        } catch (TemplateException e) {
            throw new ServletException();
        }
    }
}