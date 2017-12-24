package app.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import app.entities.User;

public class RegisterServlet extends HttpServlet {

    private SessionFactory factory;

    @Override
    public void init() throws ServletException {
        super.init();
        factory = (SessionFactory) SpringContextProvider.getContext().getBean("sessionFactory");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("register.jsp");
        requestDispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String pass = req.getParameter("pass");
        String wallet = req.getParameter("wallet");
        User user = new User();
        user.setEmail(email);
        user.setPass(pass);
        user.setWallet(wallet);

        Session session = factory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(user);

        doGet(req,resp);
    }
}
