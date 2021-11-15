package edu.bbte.idde.zdim1981.web;

import edu.bbte.idde.zdim1981.backend.dataaccessobject.CpuShopDao;
import edu.bbte.idde.zdim1981.backend.dataaccessobject.mem.CpuShopInMemDao;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@WebServlet("/cputemplate")
public class CpuShopTemplateServlet extends HttpServlet {
    private final CpuShopDao cpuShopDao = new CpuShopInMemDao();
    private Template template;

    @Override
    public void init() throws ServletException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
        cfg.setDefaultEncoding("UTF-8");
        cfg.setClassForTemplateLoading(CpuShopTemplateServlet.class, "/");
        try {
            template = cfg.getTemplate("template.ftl");
        } catch (IOException e) {
            throw new ServletException();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> templateData = new ConcurrentHashMap<>();
        templateData.put("CPUs", cpuShopDao.readAll());
        try {
            template.process(templateData, resp.getWriter());
        } catch (TemplateException e) {
            throw new ServletException();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> templateData = new ConcurrentHashMap<>();
        templateData.put("CPUs", cpuShopDao.readAll());
        try {
            template.process(templateData, resp.getWriter());
        } catch (TemplateException e) {
            throw new ServletException();
        }
    }
}
