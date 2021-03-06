package app.servlets;


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
/**
 *Сервлет, реализующий работу страницы авторизации - authorize.jsp
*/

public class AuthorizeServlet extends HttpServlet {
    private SessionFactory factory;

    /**
     * при инициализации создаёт sessionFactory через SpringContextProvider
     */
    @Override
    public void init() throws ServletException {
        super.init();
        factory = (SessionFactory) SpringContextProvider.getContext().getBean("sessionFactory");
    }

    /**
     * doGet просто перенаправляет на authorize.jsp с полученными параметрами
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("authorize.jsp");
        requestDispatcher.forward(req,resp);
    }

    /**
     *получает из запроса параметры email и pass
     * Создаёт строковую flag - результат выполнения запроса, который будет передан на следующую страницу
     * Проверяет, совпадает ли email с каким-либо емейлом в базе и совпадают ли пароли
     *  *в случае успеха flag = success, иначе - fail
     *  в запрос добавляется параметр flag, после чего отправляется на метод doGet
     */
    //TODO: возможно, имеет смысл не перекидывать на doGet, а форвардить сразу на authorize.jsp с флагом;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Session session = factory.openSession();
        String email = req.getParameter("email");
        String pass = req.getParameter("pass");
        String flag = null;


        User user = session.createQuery("SELECT u FROM User u WHERE u.email= :email and u.pass = :pass", User.class)
                .setParameter("email", email)
                .setParameter("pass", pass)
                .uniqueResult();
        if(  null != user  ) {
            flag = "success";
            req.setAttribute("email", user.getEmail());
            req.getSession().setAttribute("userId", user.getUserId());
//            session.find(User.class, req.getSession().getAttribute("userId"));
        }
        else flag = "fail";

        req.setAttribute("flag", flag);

        doGet(req,resp);
    }
}
