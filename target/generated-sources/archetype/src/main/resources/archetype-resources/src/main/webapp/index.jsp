#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
<%--shiro:principal 代表用户名--%>
<h1>hello <shiro:principal></shiro:principal></h1>

<%--还没登录--%>
<shiro:guest>
    <a href="/shiro/login">用户登录</a>
</shiro:guest>

<%--登录成功--%>
<shiro:user>
    <a href="/shiro/user/list.jsp">用户列表</a>
    <%--是否有权限，有则显示--%>
    <shiro:hasPermission name="user:add">
        <a href="/shiro/user/add.jsp">用户添加</a>
    </shiro:hasPermission>

    <%--是否有角色--%>
    <shiro:hasRole name="admin">
        <a href="/shiro/admin">管理界面</a>
    </shiro:hasRole>

    <a href="/shiro/logout">退出系统</a>
</shiro:user>

</body>
</html>