<%--
  Created by IntelliJ IDEA.
  User: ALIENWARE
  Date: 22.12.2017
  Time: 18:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>sellGoods</title>
</head>
<body>
    <div>
        <form method="post">
            <label>Input name</label>
            <input type ="text" name = "goodsName">
            <label>Input short description</label>
            <input type="text" name="short_desc">
            <label>Input price</label>
            <input type="number" name="price">
            <label>Input full description</label>
            <input type="text" name="description">
            <button type="submit">Submit</button>
        </form>
    </div>

    <div>
        <button onclick="location.href='/'">Back to main</button>
    </div>

</body>
</html>
