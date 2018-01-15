package app.servlets;

import app.entities.Good;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Сервлет, обрабатывающий добавление нового товара в базу, страница sellGoods.jsp
 */

public class sellGoodsServlet extends HttpServlet {
private SessionFactory factory;

    /**
     * при инициализации создаётся sessionFactory через {@link SpringContextProvider}
     */
    @Override
    public void init() throws ServletException {
        super.init();
        factory = (SessionFactory) SpringContextProvider.getContext().getBean("sessionFactory");
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("sellGoods.jsp");
        requestDispatcher.forward(req,resp);
    }

    /**
     * doPost осуществляет добавление товара в базу
     * получает все необходимые параметры для описания сущности "Good"
     * получает id продавца из session.getParameter (TODO)
     * создаёт новый объект сущности Good и вбивает его в базу
     * перекидывает на doGet
     */

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nameGoods = req.getParameter("nameGoods");
        String short_desc = req.getParameter("short_desc");
        String description = req.getParameter("description");
        double price = Double.parseDouble(req.getParameter("price"));
        String photo_address = "images/test_photo.jpg"; //TODO: getting photo address from req
        int seller_id=0; //TODO: getting id of current user

        Good good = new Good(seller_id, nameGoods, short_desc, description, price, photo_address);


        Session session = factory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(good);
        session.getTransaction().commit();

        doGet(req,resp);
    }
}
