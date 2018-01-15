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

/**
 * сервлет, обрабатывающий регистрацию юзеров на register.jsp
 */
public class RegisterServlet extends HttpServlet {

    private SessionFactory factory;

    /**
     * при инициализации создаётся sessionFactory через функцию от {@link SpringContextProvider}
     */
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

    /**
     * @param req
     * @param resp
     * doPost осуществляет сам процесс добавления юзера в базу
     * Получает из запроса email, пароль и кошелёк; создаёт на их основе новый объект сущности юзер (id autoincrement)
     * открывает сессию через уже готовый sessionFactory и закидывает туда юзера
     * перекидывает управление снова на doGet
     */
    //TODO: сделать проверку на уникальность email и реакцию на свежезарегистрированного
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
        session.getTransaction().commit();

        doGet(req,resp);
    }
}
