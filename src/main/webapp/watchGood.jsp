<%@ page import="app.entities.Good" %><%--
  Created by IntelliJ IDEA.
  User: ALIENWARE
  Date: 05.01.2018
  Time: 0:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>WatchGood</title>


</head>
<body>
<%
    Good g = (Good) request.getAttribute("good");
    out.println("<table style='width:100%' border='1'>");
    out.println("<tr> " +
            "<td>" +g.getGoodsId()+"</td> "    +
            "<td>"+g.getName()+"</td> "        +
            "<td>"+g.getSellerId()+"</td> "    +
            "<td>"+g.getPrice()+"</td> "       +
            "<td>"+g.getShortDesc()+"</td> "   +
            "<td>"+g.getDescription()+"</td>"  +
            "<td> <img width=50 height=50 src=\" "+g.getPhotoAddress()+" \"></td>" +
            "</tr>");
    out.println("</table");
%>

<div>
    <button onclick="location.href='/watchAll'">Back to all goods</button>
    <button onclick="location.href='/'">Back to main</button>
</div>

</body>
</html>
