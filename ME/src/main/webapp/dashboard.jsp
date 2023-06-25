
<%@page import="com.me.bo.utility.MeUtility"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.me.bo.dto.TransactionDto"%>
<%@page import="com.me.bo.dto.UserDetailsDto"%>
<%@page import="com.me.portal.RestURL"%>
<%@page import="com.me.portal.MePortalUtility"%>
<%@page import="com.me.bo.dto.LoginDTO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="/css/dashboard.css" />
<script type="text/javascript" src="/meJs/dashboard.js"></script>
<title>Dashboard</title>
</head>
<body>

	<%
	Boolean isAuth = (Boolean) session.getAttribute("auth");
	String userName = (String) session.getAttribute("username");
	%>
	<%
	if (isAuth != null && isAuth) {
		LoginDTO loginDTO = new LoginDTO();
		loginDTO.setUserName(userName);
		String responseJson = MePortalUtility.getResposeFromBo(RestURL.getUserDetails,
		MePortalUtility.getJsonOfObject(loginDTO));
		UserDetailsDto userDetailsDto = MePortalUtility.ObjectfromJson(responseJson, UserDetailsDto.class);
		String firstName = userDetailsDto.getfName();
		session.setAttribute("userId", userDetailsDto.getUserId().toString());
	%>

	<div class="button-row">
		<button class="button">Add Transaction</button>
		<button class="button">Add Catagory</button>
		<button class="button">Add Wallets</button>
		<button class="button"
			style="background-color: #77fc91; width: 700px; margin-left: 170px">
			Hi,
			<%=firstName%>
			! Welcome to Manage Expense Dashboard
		</button>

	</div>


	<h3 style="background-color: #77fc91; width: auto; padding-left: 100px">Search
		Your Transaction</h3>
	<form action="dashboard.jsp" method="POST">

		<div>
			<label for="accountId">Wallet:</label> <select name="accountId"
				id="accountId" onchange="updateField()">
				<option value="0">Please Select</option>
				<option value="1">Default</option>
				<option value="2">Second</option>
			</select>
		</div>

		<div>
			<label for="type">Type:</label> <select name="type" id="type">
				<option value="0">Please Select</option>
				<option value="1">Debit</option>
				<option value="2">Credit</option>
			</select>
		</div>

		<div>
			<label for="subCategory">Subcategory:</label> <input type="text"
				name="subCategory" id="subCategory" value="">
		</div>

		<div>
			<label for="category">Category:</label> <input type="text"
				name="category" id="category" value="">
		</div>

		<div>
			<label for="tags">Tags:</label> <input type="text" name="tags"
				id="tags" value="">
		</div>

		<div>
			<label for="isActive">Active:</label> <select name="isActive"
				id="isActive">
				<option value="0">Please Select</option>
				<option value="1">Yes</option>
				<option value="2">No</option>
			</select>
		</div>

		<div>
			<label for="fromDate">From Date:</label> <input type="date"
				name="fromDate" id="fromDate" value="">
		</div>

		<div>
			<label for="toDate">To Date:</label> <input type="date" name="toDate"
				id="toDate" value="">
		</div>
		<div style="display: none;">
			<label for="toDate">To Date:</label> <input type="date" name="toDate"
				id="toDate" value="">
		</div>
		<div style="display: none;">
			<label for="userId">User ID:</label> <input type="text" name="userId"
				id="userId" value="<%=userDetailsDto.getUserId()%>">
		</div>


		<div style="align-items: initial;">
			<input id="formSubmit" type="submit" value="Search">
		</div>
	</form>



	<div class="summary" style="display: none;">



		<div class="summary-box">
			<h2>Total Income</h2>
			<p>$5000</p>
		</div>
		<div class="summary-box">
			<h2>Total Expenses</h2>
			<p>$3500</p>
		</div>
		<div class="summary-box">
			<h2>Balance</h2>
			<p>$1500</p>
		</div>
	</div>

	<%
	if (request.getParameter("userId") != null) {
	%>


	<table>
		<thead>
			<tr>
				<th>Wallet</th>
				<th>Transaction Date</th>
				<th>Amount</th>
				<th>Type</th>
				<th>Category</th>
				<th>Subcategory</th>
				<th>Description</th>
				<th>Tags</th>
			</tr>
		</thead>
		<tbody>

			<%
			List<TransactionDto> transactionList = MePortalUtility.requestToresponseForTransaction(request);

			if (transactionList != null)
				for (TransactionDto transaction : transactionList) {
			%>
			<!-- Iterate over the list of Transaction objects -->

			<tr>
				<td><%=transaction.getAccountName()%></td>
				<td><%=MeUtility.getStrDate(transaction.getDate(), MeUtility.dd_MM_yyyy)%></td>
				<td><%=transaction.getAmount()%></td>
				<td><%=transaction.getType() != null ? transaction.getType().getValue() : ""%></td>
				<td><%=transaction.getCategory() != null ? transaction.getCategory().getValue() : ""%></td>
				<td><%=transaction.getSubCategory() != null ? transaction.getSubCategory().getValue() : ""%></td>
				<td><%=transaction.getDescription()%></td>
				<td><%=transaction.getTags()%></td>
			</tr>

			<%
			}
			%>
		</tbody>
	</table>

	<%
	}
	%>


	














	<%
	} else {
	%>
	<div id="loginButtonDiv">

		<jsp:include page="login.jsp" />
	</div>
	<%
	}
	%>
</body>
</html>