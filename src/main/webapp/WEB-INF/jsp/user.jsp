<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%-- <%@ taglib prefix="dfmt" uri="http://java.sun.com/jsp/jstl/dfmt" %> --%>
<%-- <%
 	String path = request.getContextPath();    
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	//String dm = com.istudy.sns.manager.server.helper.ManagerConstants.ENVIRONMENT.DOMAIN_NAME;
	//if(dm!=null&&dm.trim().length()>0){
	//	basePath = dm;
	//}
%> --%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="renderer" content="webkit">
<html>
<head>
<title>Insert title here</title>
</head>
<body>
user page
${users }
${user }${user}${test }
<c:if test="${user.id == 1 }">
${user.name } + ${user.password }
</c:if>
</body>
</html>