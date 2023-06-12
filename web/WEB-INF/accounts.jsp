<%-- 
    Document   : users
    Author     : aalmasri
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Accounts</title>
    </head>
    <body>
        <h1>Accounts</h1>

        <h3>Page ${page}</h3>

        <!-- display a table of all of the accounts -->
        <table border="1">
            <tr>
                <th>Name</th>
                <th>View</th>
            </tr>
            
            <c:forEach var="account" items="${accounts}">
                <tr>
                    <td>${account.name}</td>
                    <td> <a href="account?username=${account.username}">View</a> </td>
                </tr>
            </c:forEach>
            
        </table>

        <!-- handle paging: forward/back -->
        <c:if test="${page gt 1}">
            <a href="accounts?page=${page - 1}">Prev</a>
        </c:if>
        
        <a href="accounts?page=${page + 1}">Next</a>
        

    </body>
</html>