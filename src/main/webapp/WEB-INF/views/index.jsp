
<%@ page import="org.hibernate.SessionFactory" %>
<%@ page import="com.jiat.crud_application.util.HibernateUtil" %>
<%@ page import="org.hibernate.Session" %>
<%@ page import="com.jiat.crud_application.entity.Employee" %>
<%@ page import="java.util.List" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
    <style>
        table, td, th {
            border: 1px solid;
            padding:10px;
        }


        table {
            width: 100%;
            border-collapse: collapse;
        }
        .pagination {
            display: inline-block;
            margin-top: 15px;
        }

        .pagination a {
            color: black;
            float: left;
            padding: 8px 16px;
            text-decoration: none;
        }

        .pagination a.active {
            background-color: #4CAF50;
            color: white;
            border-radius: 5px;
        }

        .pagination a:hover:not(.active) {
            background-color: #ddd;
            border-radius: 5px;
        }
    </style>
</head>
<body>
<h1>Employee List</h1>
<div>
    <form method="post" action="search_employee">
        <span>Search Employee :</span>
        <input type="text" placeholder="By name, position, department, hire date" name="search"/>
        <input type="submit" value="Search"/>
    </form>

</div>
<table>
    <tr>
        <td><b>ID</b></td>
        <td><b>Name</b></td>
        <td><b>Position</b></td>
        <td><b>Department</b></td>
        <td><b>Hire Date</b></td>
        <td><b>Salary/mo</b></td>
    </tr>
    <c:forEach items="${it}" var="items">
    <tr>
        <td><c:out value="${items.id}"></c:out></td>
        <td><c:out value="${items.name}"></c:out></td>
        <td><c:out value="${items.position}"></c:out></td>
        <td><c:out value="${items.department}"></c:out></td>
        <td><c:out value="${items.hire_date}"></c:out></td>
        <td><c:out value="${items.salary}"></c:out></td>
    </tr>
    </c:forEach>
</table>

<div class="pagination">
    <a href="#">&laquo;</a>
    <a href="#">1</a>
    <a href="#" class="active">2</a>
    <a href="#">3</a>
    <a href="#">4</a>
    <a href="#">5</a>
    <a href="#">6</a>
    <a href="#">&raquo;</a>
</div>


</body>
</html>
