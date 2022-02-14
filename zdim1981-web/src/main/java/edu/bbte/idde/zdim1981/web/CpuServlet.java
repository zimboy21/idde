package edu.bbte.idde.zdim1981.web;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import edu.bbte.idde.zdim1981.backend.dataaccessobject.CpuDao;
import edu.bbte.idde.zdim1981.backend.dataaccessobject.jdbc.JdbcDaoFactory;
import edu.bbte.idde.zdim1981.backend.model.Cpu;
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

public class CpuServlet extends HttpServlet {
    JdbcDaoFactory jdbcDaoFactory = new JdbcDaoFactory();
    private final CpuDao cpuDao = jdbcDaoFactory.getCpuDao();
    public static final Logger LOG = LoggerFactory.getLogger(CpuServlet.class);

    private Boolean passedCheck(Cpu cpu) {
        return cpu.getClockSpeed() != null && cpu.getCoreCount() != null
                && cpu.getName() != null && cpu.getOverClocking() != null
                && cpu.getPrice() != null;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper objectMapper = ObjectMapperFactory.getObjectMapper();
        resp.setHeader("Content-Type", "application/json");
        String idStr = req.getParameter("id");
        String evenIfDeleted = req.getParameter("evenIfDeleted");
        if (idStr != null) {
            try {
                Long id = Long.parseLong(idStr);
                Cpu cpu;
                if (evenIfDeleted == null) {
                    cpu = cpuDao.read(id);
                } else {
                    cpu = cpuDao.readEvenIfDeleted(id);
                }
                if (cpu == null) {
                    resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                } else {
                    objectMapper.writeValue(resp.getWriter(), cpu);
                }
                return;
            } catch (NumberFormatException e) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }
        }
        objectMapper.writeValue(resp.getWriter(), cpuDao.readAll());
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Content-Type", "application/json");
        String idStr = req.getParameter("id");
        if (idStr != null) {
            try {
                Long id = Long.parseLong(idStr);
                Cpu cpu = cpuDao.read(id);

                if (cpu == null) {
                    resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                } else {
                    cpuDao.delete(id);
                }
            } catch (NumberFormatException e) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Content-Type", "application/json");
        ObjectMapper objectMapper = ObjectMapperFactory.getObjectMapper();
        BufferedReader reader = new BufferedReader(req.getReader());
        try {
            Cpu cpu = objectMapper.readValue(reader, Cpu.class);
            if (cpu.getClockSpeed() == null || cpu.getCoreCount() == null
                    || cpu.getName() == null || cpu.getOverClocking() == null || cpu.getPrice() == null) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            } else {
                cpuDao.create(cpu);
            }
        } catch (MismatchedInputException | JsonParseException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Content-Type", "application/json");
        String idStr = req.getParameter("id");
        if (idStr == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } else {
            try {
                Long id = Long.parseLong(idStr);
                Cpu checkCpu = cpuDao.read(id);

                if (checkCpu == null) {
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    return;
                }

                ObjectMapper objectMapper = ObjectMapperFactory.getObjectMapper();
                BufferedReader reader = new BufferedReader(req.getReader());
                try {
                    Cpu cpu = objectMapper.readValue(reader, Cpu.class);
                    if (passedCheck(cpu)) {
                        cpu.setId(id);
                        cpuDao.update(cpu, id);
                    } else {
                        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    }
                } catch (MismatchedInputException | JsonParseException e) {
                    resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                }
            } catch (MismatchedInputException | NumberFormatException e) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        }
    }
}
