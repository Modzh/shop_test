<%--
  Created by IntelliJ IDEA.
  User: ALIENWARE
  Date: 20.12.2017
  Time: 22:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Authorization</title>
</head>
<body>
    <div>
        <%
            if(request.getAttribute("flag") != null) {
                switch(request.getAttribute("flag").toString() ) {
                    case "success" : {out.println("<p>You have successfully authorized, "+ request.getAttribute("email")+"</p>"); break;}
                    case "fail" : {out.println("<p>Email or password is invalid.</p>"); break;}
                }
            }
        %>
    </div>


    <div>
        <form method="post">
            <Label>Input email</Label>
            <input type="email" name="email">
            <br>
            <Label>Input password</Label>
            <input type="password" name="pass">
            <button type="submit">Submit</button>
        </form>
    </div>

    <div>
        <button onclick="location.href='/'">Back to main</button>
    </div>


</body>
</html>
