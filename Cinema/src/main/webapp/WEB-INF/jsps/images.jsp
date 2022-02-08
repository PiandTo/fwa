<%--
  Created by IntelliJ IDEA.
  User: dasha
  Date: 30.01.2022
  Time: 20:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
  <title>Upload File Response</title>
</head>
<body>
<%-- Using JSP EL to get message attribute value from request scope --%>
<h2>${requestScope.message}</h2>
<a href="${pageContext.request.contextPath}/profilePage">User Profile</a>
</body>
</html>
