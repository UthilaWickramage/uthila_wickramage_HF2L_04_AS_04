<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 8/17/2023
  Time: 8:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Add Employee</h1>
<form action="add_employee" method="post">
  <table>
    <tr>
      <td>Employee Name :</td>
      <td><input type="text" name="name"/></td>
    </tr>
    <tr>
      <td>Job Position :</td>
      <td><input type="text" name="position"/></td>
    </tr>
    <tr>
      <td>Department :</td>
      <td><input type="text" name="department"/></td>
    </tr>
    <tr>
      <td>Salary/Mo :</td>
      <td><input type="text" name="salary"/></td>
    </tr>
    <tr>
      <td></td>
      <td><input type="submit"/></td>
    </tr>
  </table>
</form>
</body>
</html>
