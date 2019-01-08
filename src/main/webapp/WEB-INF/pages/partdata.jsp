<%--
  Created by IntelliJ IDEA.
  User: Tolik&Marina
  Date: 04.01.2019
  Time: 19:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>

<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/main.css" type="text/css">

    <title>${part.name} info</title>

</head>
<body>
<div style="position: absolute; margin-left: 50%">
<h2 style="margin-left: 50%">Part Details</h2>

<table class="tg">
    <tr>
        <th width="80">ID</th>
                <th width="120">Name</th>
        <th width="120">Necessary</th>
        <th width="120">Quantity</th>
    </tr>
    <tr>
        <td>${part.id}</td>
        <td>${part.name}</td>
        <td>${part.necessary}</td>
        <td>${part.number}</td>
    </tr>
</table>

<br/>
<br/>

<div>
    <c:url var="returnAction" value="/parts"/>
    <a href="${returnAction}"><input class="mainButton" type="button" value="Back"></a>
</div>
</div>
</body>
</html>

