package app.servlets;

import app.entities.Good;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WatchGood extends HttpServlet {
    private SessionFactory factory;

    @Override
    public void init() throws ServletException {
        super.init();
        factory = (SessionFactory) SpringContextProvider.getContext().getBean("sessionFactory");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Session session = factory.openSession();

        int goodsId = Integer.parseInt(req.getParameter("goodsId"));
        Good good = session.createQuery("SELECT g FROM Good g WHERE g.goodsId=:goodsId", Good.class)
                            .setParameter("goodsId",goodsId).uniqueResult();


        req.setAttribute("good",good);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("watchGood.jsp");
        requestDispatcher.forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

}
