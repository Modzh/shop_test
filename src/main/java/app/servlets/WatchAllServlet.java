package app.servlets;

import app.model.ModelUsers;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class WatchAllServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ModelUsers modelUsers = ModelUsers.getInstanse();
        List<String> listEmail = modelUsers.list();
        req.setAttribute("listEmail", listEmail);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("pages/watchAll.jsp");
        requestDispatcher.forward(req,resp);

    }
}
