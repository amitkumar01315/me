<%@page import="com.me.portal.RestURL"%>
<%@page import="com.me.portal.MePortalUtility"%>
<%@page import="com.me.bo.dto.LoginDTO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="meJs/home.js"></script>
<link rel="stylesheet"  type="text/css"  href="css/style.css" >


</head>
<body id="loginPage">

	<div id="loginButtonDiv">
		<form method="POST" action="login.jsp">
			<input style="width: 200px; height: 30px" type="text" name="username" placeholder="Username"> 
			<input style="width: 200px; height: 30px" type="password" name="password" placeholder="Password"> <input
				type="submit" value="Submit">
		</form>

</div>
		<%
		if (request.getParameter("username") != null ) {

			LoginDTO loginDTO = new LoginDTO();
			loginDTO.setUserName(request.getParameter("username"));
			loginDTO.setUserPassword(request.getParameter("password"));
			String flag="0";
			flag= MePortalUtility.getResposeFromBo(RestURL.login, MePortalUtility.getJsonOfObject(loginDTO));

			if ( flag != null && flag.equalsIgnoreCase("1")) {
				session.setAttribute("auth", true);
				session.setAttribute("username", loginDTO.getUserName());
		%>

		<div id="loginMsgDiv">
		<input id="auth" type="checkbox" checked="checked">Login
		Success.........
	</div>



	<%
	} else {
	%>
	<div id="loginMsg2Div">
		<p>Username and Password is invalid!!!!!!!!!!!!!!!!!</p>
	</div>
	
	
	<%
	}
		}
	%>

	<div style="display: none;">
		<jsp:include page="home.jsp" />
	</div>


</body>
</html>