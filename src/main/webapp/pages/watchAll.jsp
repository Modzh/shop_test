<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: ALIENWARE
  Date: 20.12.2017
  Time: 16:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>watchAll</title>
</head>
<body>
    <ul>
        <%
            List<String> listEmail = (List<String>) request.getAttribute("listEmail");

            if(!listEmail.isEmpty()) {
                out.println("<ui>");
                for (String s : listEmail) {
                    out.println("<li>" + s + "</li>");
                }
                out.println("</ui>");
            }
            else out.println("<p>There're no users.</p>");

        %>
    </ul>

    <div>
        <button onclick="location.href='/'">Back to main</button>
    </div>

</body>
</html>
