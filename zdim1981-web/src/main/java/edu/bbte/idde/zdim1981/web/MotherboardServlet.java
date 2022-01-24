package edu.bbte.idde.zdim1981.web;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import edu.bbte.idde.zdim1981.backend.dataaccessobject.MotherboardDao;
import edu.bbte.idde.zdim1981.backend.dataaccessobject.jdbc.JdbcDaoFactory;
import edu.bbte.idde.zdim1981.backend.model.Motherboard;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/motherboard")

public class MotherboardServlet extends HttpServlet {
    JdbcDaoFactory jdbcDaoFactory = new JdbcDaoFactory();
    private final MotherboardDao mbdao = jdbcDaoFactory.getMotherboardDao();
    public static final Logger LOG = LoggerFactory.getLogger(MotherboardServlet.class);

    private Boolean passedCheck(Motherboard motherboard) {
        return motherboard.getBios() != null
                && motherboard.getName() != null && motherboard.getFsb() != null
                && motherboard.getPrice() != null && motherboard.getMemory() != null;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper objectMapper = ObjectMapperFactory.getObjectMapper();
        resp.setHeader("Content-Type", "application/json");
        String idStr = req.getParameter("id");
        if (idStr != null) {
            try {
                Long id = Long.parseLong(idStr);
                Motherboard motherboard = mbdao.read(id);

                if (motherboard == null) {
                    resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                } else {
                    objectMapper.writeValue(resp.getWriter(), motherboard);
                }
                return;
            } catch (NumberFormatException e) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }
        }
        objectMapper.writeValue(resp.getWriter(), mbdao.readAll());
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Content-Type", "application/json");
        String idStr = req.getParameter("id");
        if (idStr != null) {
            try {
                Long id = Long.parseLong(idStr);
                Motherboard motherboard = mbdao.read(id);

                if (motherboard == null) {
                    resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                } else {
                    mbdao.delete(id);
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
            Motherboard motherboard = objectMapper.readValue(reader, Motherboard.class);
            if (passedCheck(motherboard)) {
                mbdao.create(motherboard);
            } else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
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
                Motherboard checkmb = mbdao.read(id);

                if (checkmb == null) {
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    return;
                }

                ObjectMapper objectMapper = ObjectMapperFactory.getObjectMapper();
                BufferedReader reader = new BufferedReader(req.getReader());
                try {
                    Motherboard motherboard = objectMapper.readValue(reader, Motherboard.class);
                    if (passedCheck(motherboard)) {
                        motherboard.setId(id);
                        mbdao.update(motherboard, id);
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
