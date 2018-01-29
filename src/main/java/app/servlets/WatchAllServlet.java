package app.servlets;

import app.entities.Good;
import app.entities.User;
import app.model.ModelUsers;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class WatchAllServlet extends HttpServlet {
private SessionFactory factory;

    @Override
    public void init() throws ServletException {
        super.init();
        factory = (SessionFactory) SpringContextProvider.getContext().getBean("sessionFactory");
    }



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Session session = factory.openSession();

        List<User> users = session.createQuery("SELECT u FROM User u", User.class).list();
        List<Good> goods = session.createQuery("SELECT g FROM Good g", Good.class).list();

        req.setAttribute("users",users);
        req.setAttribute("goods",goods);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("watchAll.jsp");
        requestDispatcher.forward(req,resp);

    }

}
