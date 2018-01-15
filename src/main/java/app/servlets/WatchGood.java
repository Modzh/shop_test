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
        System.out.println(req.getParameter("goodsId"));
        int goodsId = Integer.parseInt(req.getParameter("goodsId"));

        
        //Query queryGoodId = session.createQuery("SELECT g.goodsId FROM Good g WHERE g.goodsId="+goodsId);
        Query queryName = session.createQuery("SELECT g.name FROM Good g WHERE g.goodsId="+goodsId);
        String name = (String) queryName.uniqueResult();
        Query querySellerId = session.createQuery("SELECT g.sellerId FROM Good g WHERE g.goodsId="+goodsId);
        int sellerId = (Integer) querySellerId.uniqueResult();
        Query queryBuyerId = session.createQuery("SELECT g.buyerId FROM Good g WHERE g.goodsId="+goodsId);
        int buyerId=0; if(queryBuyerId.uniqueResult() != null) buyerId = (Integer) queryBuyerId.uniqueResult();
        Query queryDesc = session.createQuery("SELECT g.description FROM Good g WHERE g.goodsId="+goodsId);
        String description = (String) queryDesc.uniqueResult();
        Query queryShortDesc = session.createQuery("SELECT g.shortDesc FROM Good g WHERE g.goodsId="+goodsId);
        String shortDesc = (String) queryShortDesc.uniqueResult();
        Query queryPrice = session.createQuery("SELECT g.price FROM Good g WHERE g.goodsId="+goodsId);
        double price = (Double) queryPrice.uniqueResult();
        Query queryPhoto = session.createQuery("SELECT g.photoAddress FROM Good g WHERE g.goodsId="+goodsId);
        String photoAddress = (String) queryPhoto.uniqueResult();

        Good good = new Good(sellerId,name,shortDesc,description,price,photoAddress);
        good.setGoodsId(goodsId);
        good.setBuyerId(buyerId);
        req.setAttribute("good",good);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("watchGood.jsp");
        requestDispatcher.forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

}
