<%@ page import="app.entities.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="app.entities.Good" %>
<%--
  Created by IntelliJ IDEA.
  User: ALIENWARE
  Date: 20.12.2017
  Time: 16:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>watchAll</title>
</head>
<body>
    <div>
        <%ArrayList<User> users = (ArrayList<User>) request.getAttribute("users");%>
        <%
            out.println("<table style='width:100%' border='1'>");
            out.println("<tr> " +
                            "<th>ID</th> " +
                            "<th>Email</th> " +
                            "<th>Password</th> " +
                            "<th>Wallet</th> " +
                        "</tr>");
            for(User u : users) {

                out.println("<tr> " +
                                "<td>" +u.getUserId()+"</td> " +
                                "<td>"+u.getEmail()+"</td> " +
                                "<td>"+u.getPass()+"</td> " +
                                "<td>"+u.getWallet()+"</td> " +
                            "</tr>");
            }
            out.println("</table>");
        %>

        <c:set value="${request.getAttribute('users')}" var="users" />
        <c:forEach items="${users}" var="u">
            <c:set value="${u.getUserId}" var="userId"></c:set>
            <c:set value="${u.getEmail}" var="email"></c:set>
            <c:set value="${u.getPass}" var="pass"></c:set>
            <c:set value="${u.getWallet}" var="wallet"></c:set>
            <tr>
                <td><c:out value="${u.getUserId}"       />  </td>
                <td><c:out value="${u.getEmail}"    />  </td>
                <td><c:out value="${u.getPass}"     />  </td>
                <td><c:out value="${u.getWallet}"   />  </td>


                    <td><c:out value="${userId}"       />  </td>
                    <td><c:out value="${email}"    />  </td>
                    <td><c:out value="${pass}"     />  </td>
                    <td><c:out value="${wallet}"   />  </td>
            </tr>
        </c:forEach>
    </div>

    <div>
            <%ArrayList<Good> goods = (ArrayList<Good>) request.getAttribute("goods");%>
            <%
            out.println("<table style='width:100%' border='1'>");
            out.println("<tr> " +
                            "<th>ID</th>"                +
                            "<th>Name</th>"              +
                            "<th>Seller id</th>"         +
                            "<th>Price</th>"             +
                            "<th>Short Description</th>" +
                            "<th>Description</th>"       +
                            "<th>Photo</th>"             +
                        "</tr>");
            for(Good g : goods) {
                out.println("<tr> " +
                                 "<td>" +g.getGoodsId()+"</td> "    +
                                 "<td>"+g.getName()+"</td> "        +
                                 "<td>"+g.getSellerId()+"</td> "    +
                                 "<td>"+g.getPrice()+"</td> "       +
                                 "<td>"+g.getShortDesc()+"</td> "   +
                                 "<td>"+g.getDescription()+"</td>"  +
                                 "<td>"+g.getPhotoAddress()+"</td>" +
                                 "<td>"+
                                 "<form action='watchGood' method=get> <input type=hidden name=\"goodsId\" value="+g.getGoodsId()+">" +
                                    "<button type=\"submit\">Watch this</button> </form>"+"</td>"+

                            "</tr>");

            }
            out.println("</table>");
        %>

    </div>
    <form method=post> <input type=text name="goodsId">
        <button type="submit">Submit</button> </form>


    <div>
        <button onclick="location.href='/'">Back to main</button>
    </div>

</body>
</html>
