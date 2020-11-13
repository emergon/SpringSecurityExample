<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Welcome home!!!</h1>
        <hr>
        User: <security:authentication property="principal.username"/>
        <br/>
        Roles: <security:authentication property="principal.authorities" />
        <hr>
        
        <p>
            <a href="${pageContext.request.contextPath}/admin">Admin</a>
        </p>
        <p>
            <a href="${pageContext.request.contextPath}/user">User</a>
        </p>
        
        <form:form action="${pageContext.request.contextPath}/logout" method="post">
            <input type="submit" value="Logout"/>
        </form:form>
    </body>
</html>
