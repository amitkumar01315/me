<%@page import="com.me.bo.utility.MeUtility"%>
<%@page import="com.me.portal.MePortalUtility"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.me.bo.dto.TransactionDto"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="/meJs/monthWise.js"></script>
<link rel="stylesheet" type="text/css" href="css/monthWise.css">
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<%
	String userId = (String) session.getAttribute("userId");

	String wallet = (String) request.getAttribute("wallet");
	String month = (String) request.getAttribute("month");
	request.setAttribute("userId", userId);

	List<TransactionDto> transactionList = MePortalUtility.requestToresponseForTransaction(request);

	if (month != null) {
	%>



	<%}
	%>

	<div class="container" style="display: inline">



		<form method="POST" action="monthWise.jsp">
			<div style="display: none">
				<input type="text" name="userId" value="<%=userId%>">
			</div>
			<label for="wallet">Wallet:</label> <select name="wallet" id="wallet">
				<option value="0">Please Select</option>
				<option value="1">Default</option>
				<option value="2">other</option>
			</select> <label for="month">Month:</label>
			<div class="month-selector">
				<select id="month" name="month">
					<option value="1">January</option>
					<option value="2">February</option>
					<option value="3">March</option>
					<option value="4">April</option>
					<option value="5">May</option>
					<option value="6">June</option>
					<option value="7">July</option>
					<option value="8">August</option>
					<option value="9">September</option>
					<option value="10">October</option>
					<option value="11">November</option>
					<option value="12">December</option>
				</select>
			</div>
			<input type="submit" value="Submit">
		</form>


	</div>



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












</body>
</html>