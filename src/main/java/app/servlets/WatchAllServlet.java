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
//        long size = (Long) session.createQuery("select count(*) from User").uniqueResult();
//
//        System.out.println("size = "+size);
//        for(Integer i = 0; i < size; ++i) {
//            String s = (String)session.createQuery("SELECT 'email' FROM User WHERE user_id=1").uniqueResult();
//            System.out.println("s = "+s);
            Query queryId = session.createQuery("SELECT u.userId FROM User u");
            List<Integer> idList = queryId.list();
            Query queryEmail = session.createQuery("SELECT u.email FROM User u");
            List<String> emailList = queryEmail.list();
            Query queryPass = session.createQuery("SELECT u.pass FROM User u");
            List<String> passList = queryPass.list();
            Query queryWallet = session.createQuery("SELECT u.wallet FROM User u");
            List<String> walletList = queryWallet.list();

            ArrayList<User> users = new ArrayList<>();

            for(int j=0;j<emailList.size();++j) {
                User user = new User(emailList.get(j),passList.get(j),walletList.get(j));
                user.setUserId(idList.get(j));
                users.add(user);

                System.out.println(user.getUserId() + " "+ emailList.get(j) +" "+ passList.get(j)+" "+ walletList.get(j));
            }
//            ResultSet rs = (ResultSet)session.get(User.class,i);
//            System.out.println("ResultSet = "+rs);
//            User user = session.get(User.class,i);
//                                  session.get("pass",i).toString(),
//                                  session.get("wallet",i).toString());
//            user.setUserId((Integer)session.get("user_id",i));
//            System.out.println(user.getEmail());
//            users.add(user);
//        }



        Query queryGoodId = session.createQuery("SELECT g.goodsId FROM Good g");
        List<Integer> goodIdList = queryGoodId.list();
        Query queryName = session.createQuery("SELECT g.name FROM Good g");
        List<String> nameList = queryName.list();
        Query querySellerId = session.createQuery("SELECT g.sellerId FROM Good g");
        List<Integer> sellerIdList = querySellerId.list();
        Query queryBuyerId = session.createQuery("SELECT g.buyerId FROM Good g");
        List<Integer> buyerIdList = queryBuyerId.list();
        Query queryDesc = session.createQuery("SELECT g.description FROM Good g");
        List<String> descList = queryDesc.list();
        Query queryShortDesc = session.createQuery("SELECT g.shortDesc FROM Good g");
        List<String> shortDescList = queryShortDesc.list();
        Query queryPrice = session.createQuery("SELECT g.price FROM Good g");
        List<Double> priceList = queryPrice.list();
        Query queryPhoto = session.createQuery("SELECT g.photoAddress FROM Good g");
        List<String> photoList = queryPhoto.list();

        ArrayList<Good> goods = new ArrayList<>();

        for(int j=0;j<goodIdList.size();++j) {
            Good good = new Good(sellerIdList.get(j),nameList.get(j),shortDescList.get(j),descList.get(j),priceList.get(j),photoList.get(j));
            good.setGoodsId(goodIdList.get(j));
            good.setBuyerId(buyerIdList.get(j));
            goods.add(good);
        }

        req.setAttribute("users",users);
        req.setAttribute("goods",goods);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("watchAll.jsp");
        requestDispatcher.forward(req,resp);

    }

}
