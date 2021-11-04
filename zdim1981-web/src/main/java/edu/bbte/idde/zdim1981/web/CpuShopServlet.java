package edu.bbte.idde.zdim1981.web;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import edu.bbte.idde.zdim1981.backend.dataaccessobject.CpuShopDao;
import edu.bbte.idde.zdim1981.backend.dataaccessobject.mem.CpuShopInMemDao;
import edu.bbte.idde.zdim1981.backend.model.CpuShop;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/cpu")

public class CpuShopServlet extends HttpServlet {
    private final CpuShopDao cpuShopDao = new CpuShopInMemDao();
    public static final Logger LOG = LoggerFactory.getLogger(CpuShopServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper objectMapper = ObjectMapperFactory.getObjectMapper();
        resp.setHeader("Content-Type", "application/json");
        String idStr = req.getParameter("id");
        if (idStr != null) {
            try {
                Long id = Long.parseLong(idStr);
                CpuShop cpuShop = cpuShopDao.read(id);

                if (cpuShop == null) {
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                } else {
                    objectMapper.writeValue(resp.getWriter(), cpuShop);
                }
                return;
            } catch (NumberFormatException e) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }
        }
        objectMapper.writeValue(resp.getWriter(), cpuShopDao.readAll());
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Content-Type", "application/json");
        String idStr = req.getParameter("id");
        if (idStr != null) {
            try {
                Long id = Long.parseLong(idStr);
                CpuShop cpuShop = cpuShopDao.read(id);

                if (cpuShop == null) {
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                } else {
                    cpuShopDao.delete(id);
                }
                return;
            } catch (NumberFormatException e) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Content-Type", "application/json");
        ObjectMapper objectMapper = ObjectMapperFactory.getObjectMapper();
        BufferedReader reader = new BufferedReader(req.getReader());
        try {
            CpuShop cpuShop = objectMapper.readValue(reader, CpuShop.class);
            if (cpuShop.getClockSpeed() == null || cpuShop.getCoreCount() == null
                    || cpuShop.getName() == null || cpuShop.getOverClocking() == null || cpuShop.getPrice() == null) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return;
            } else {
                cpuShopDao.create(cpuShop);
                return;
            }
        } catch (InvalidFormatException | JsonParseException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Content-Type", "application/json");
        String idStr = req.getParameter("id");
        if (idStr != null) {
            try {
                Long id = Long.parseLong(idStr);
                ObjectMapper objectMapper = ObjectMapperFactory.getObjectMapper();
                BufferedReader reader = new BufferedReader(req.getReader());
                try {
                    CpuShop cpuShop = objectMapper.readValue(reader, CpuShop.class);
                    if (cpuShop.getClockSpeed() == null || cpuShop.getCoreCount() == null
                            || cpuShop.getName() == null || cpuShop.getOverClocking() == null
                            || cpuShop.getPrice() == null) {
                        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                        return;
                    } else {
                        cpuShop.setId(id);
                        cpuShopDao.update(cpuShop, id);
                        return;
                    }
                } catch (InvalidFormatException | JsonParseException e) {
                    resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    return;
                }
            } catch (NumberFormatException e) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }
        }
    }
}