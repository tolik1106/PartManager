<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page session="false" %>
<html>
<head>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/main.css" type="text/css">
    <title>PartData</title>

</head>
<body>
<a href="../../index.jsp">Return to main menu</a>

<br/>

<div id="searchField">
    <form:form action="/parts/find" method="post" modelAttribute="part">
        <label for="find">Part name:</label>
        <form:input id="find" class="basic-slide" path="name" placeholder="Name to find"/>
        <input class="buttonFind" name="find" type="submit" value="Find"/>
    </form:form>
</div>
<br/>

<c:if test="${!empty parts}">

<h1>Part List</h1>
<br/>
<div id="sortButtons">
    <div style="display: inline-block">
        <c:url var="sortName" value="/parts?page=${page}&sort=0"/>
        <a href="${sortName}"><input class="sortButton" type="button" value="Sort by Name"></a>

    </div>
    <div style="display: inline-block">
        <c:url var="sortDesc" value="/parts?page=${page}&sort=1"/>
        <a href="${sortDesc}"><input class="sortButton" type="button" value="Oprional first"></a>

    </div>
    <div style="display: inline-block">
        <c:url var="sortAsc" value="/parts?page=${page}&sort=2"/>
        <a href="${sortAsc}"><input class="sortButton" type="button" value="Necessary first"></a>
    </div>
</div>

    <div id="partTable">
        <table class="tg">
            <tr>
                <th width="100" style="display: none">Id</th>
                <th width="100">Name</th>
                <th width="120">Necessary</th>
                <th width="120">Quantity</th>
                <th width="60">Edit</th>
                <th width="60">Delete</th>
            </tr>
            <c:forEach items="${parts}" var="part">
                <tr>
                    <td style="display: none">${part.id}</td>
                    <td><a href="/partdata/${part.id}">${part.name}</a></td>
                    <td align="center">
                        <c:if test="${part.necessary}">Yes</c:if>
                        <c:if test="${!part.necessary}">No</c:if>
                    </td>
                    <td align="center">${part.number}</td>
                    <td align="center"><a href="<c:url value="/edit/${part.id}"/>"><img src="../../resources/edit.png"/></a></td>
                    <td align="center"><a href="<c:url value="/remove/${part.id}"/>"><img src="../../resources/delete.png"></a></td>
                </tr>
            </c:forEach>

        </table>
    </div>

<div id="partTable">
    <table class="tg">
        <tr>
            <th width="100">Can collect</th>
            <th width="100">${minPart.number}</th>
            <th width="100">computers</th>
        </tr>
    </table>
</div>

    </c:if>

    <br/>
    <div id="partTable">
        <table>
            <tr>
                <c:forEach begin="0" end="${allParts.size() - 1}" var="num">
                    <c:if test="${num mod 10 eq 0}">
                        <td width="20">
                            <fmt:parseNumber var="i" integerOnly="true" type="number" value="${num / 10 + 1}"/>
                            <a class="button27" href="<c:url value="/parts?page=${i - 1}"/>"><c:out value="${i}"/></a>
                        </td>
                    </c:if>
                </c:forEach>
            </tr>
        </table>
    </div>
    <br/>

    <div id="partTable">

        <h2>Add a Part</h2>

        <c:url var="addAction" value="/parts/new"/>
    </div>

    <div id="partTable">
        <form:form action="${addAction}" modelAttribute="part" method="post">
            <table>
                <c:if test="${!empty part.name}">
                    <tr>
                        <td>
                            <form:label path="id">
                                <spring:message text="ID"/>
                            </form:label>
                        </td>
                        <td>
                            <form:input path="id" readonly="true" size="8" disabled="true"/>
                            <form:hidden path="id"/>
                        </td>
                    </tr>
                </c:if>

                <tr>
                    <td>
                        <label for="name" path="name">
                            <spring:message text="Name"/>
                        </label>
                    </td>
                    <td>
                        <form:input id="name" path="name"/>
                        <form:errors path="name"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form:label for="nec" path="necessary">
                            <spring:message text="Necessary"/>
                        </form:label>
                    </td>
                    <td>
                        <form:checkbox id="nec" path="necessary"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form:label path="number">
                            <spring:message text="Number"/>
                        </form:label>
                    </td>
                    <td>
                        <form:input path="number"/>
                        <form:errors path="number"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <c:if test="${!empty part.name}">
                            <input class="mainButton" type="submit"
                                   value="<spring:message text="Edit part"/>"/>
                        </c:if>
                        <c:if test="${empty part.name}">
                            <input class="mainButton" type="submit"
                                   value="<spring:message text="Add part"/>"/>
                        </c:if>
                        <input class="mainButton" type="reset" value="Reset"/>
                    </td>
                </tr>
            </table>
        </form:form>
    </div>
</body>
</html>