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
        <table style='width:100%' border='1'>
            <tr>
                <th>UserId</th>
                <th>Email</th>
                <th>Passworc</th>
                <th>Wallet</th>
            </tr>
            <c:forEach items="${requestScope.users}" var="u">
                <tr>
                        <td><c:out value="${u.userId}"   />  </td>
                        <td><c:out value="${u.email}"    />  </td>
                        <td><c:out value="${u.pass}"     />  </td>
                        <td><c:out value="${u.wallet}"   />  </td>
                </tr>
            </c:forEach>
        </table>
    </div>

    <div>
        <table style='width:100%' border='1'>
            <tr>
                <th>GoodId</th>
                <th>Name</th>
                <th>SellerId</th>
                <th>Price</th>
                <th>Short Description</th>
                <th>Description</th>
                <th>Photo</th>
            </tr>
            <c:forEach items="${requestScope.goods}" var="g">
                <tr>
                    <td><c:out value="${g.goodsId}"      />  </td>
                    <td><c:out value="${g.name}"         />  </td>
                    <td><c:out value="${g.sellerId}"     />  </td>
                    <td><c:out value="${g.price}"        />  </td>
                    <td><c:out value="${g.shortDesc}"    />  </td>
                    <td><c:out value="${g.description}"  />  </td>
                    <td> <img width=50 height=50 src='<c:out value="${g.photoAddress}"/>' />   </td>
                    <td><button onclick=location.href='/watchGood?goodsId=<c:out value="${g.goodsId}"/>'>Watch</button> </td>
                </tr>
            </c:forEach>
        </table>
    </div>

    <div>
        <button onclick="location.href='/'">Back to main</button>
    </div>

</body>
</html>
