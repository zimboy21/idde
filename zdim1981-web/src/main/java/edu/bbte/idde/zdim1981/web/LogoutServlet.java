package edu.bbte.idde.zdim1981.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    private HttpSession session;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        session = request.getSession();
        session.invalidate();
        response.getOutputStream().print("Session deleted!");
    }
}