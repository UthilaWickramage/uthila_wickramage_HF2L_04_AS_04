<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 8/17/2023
  Time: 9:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Search Employee</h1>
<form action="select_employee" method="put">
  <span>Employee ID :</span>
  <input name="search" type="text"/>
  <input type="submit" value="Search"/>
</form>
<h1>Edit Employee</h1>
<form action="update_employee" method="post">
  <table>
    <tr>
      <td>Employee ID :</td>
      <td><input type="text" disabled value="${it.id}" name="id"/></td>
    </tr>
    <tr>
      <td>Employee Name :</td>
      <td><input type="text" value="${it.name}" name="name"/></td>
    </tr>
    <tr>
      <td>Job Position :</td>
      <td><input type="text" value="${it.position}" name="position"/></td>
    </tr>
    <tr>
      <td>Department :</td>
      <td><input type="text" value="${it.department}" name="department"/></td>
    </tr>
    <tr>
      <td>Hired Date :</td>
      <td><input type="text" disabled value="${it.hire_date}"/></td>
    </tr>
    <tr>
      <td>Salary/Mo :</td>
      <td><input type="text" value="${it.salary}" name="salary"/></td>
    </tr>
    <tr>
      <td></td>
      <td><input type="submit" value="Save"/></td>
    </tr>
  </table>
</form>
</body>
</html>
