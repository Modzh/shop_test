<%--
  Created by IntelliJ IDEA.
  User: ALIENWARE
  Date: 20.12.2017
  Time: 16:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<html>
<head>
    <title>registrationSubmitAction</title>
</head>
<body>
<div>
    <%--<%--%>
        <%--if(request.getAttribute("email") != null ) out.println("<p>User with email "+ request.getAttribute("email") + " successfully added!</p>");--%>
    <%--%>--%>


    <div>
        <form method="post">
            <label> Email:
                <input type="email" name="email">
                <br/>
            </label>

            <label>Password:
                <input type="text" name="pass">
                <br/>
            </label>

            <label>Wallet:
                <input type="text" name="wallet">
                <br/>
            </label>
            <button type="submit">Submit</button>

        </form>
    </div>

    <div>
        <button onclick="location.href='/'">Back to main</button>
    </div>

</div>
</body>
</html>
