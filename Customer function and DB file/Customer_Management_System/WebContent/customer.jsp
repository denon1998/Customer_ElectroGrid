<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@page import="model.Customer" %>
<%

if (request.getParameter("customerName") != null)
{
	if (request.getParameter("customerName") != null)
	 {
	 Customer itemObj = new Customer();
	 String stsMsg = itemObj.insertcustomerdetails(request.getParameter("customerName"),
	 request.getParameter("customerPhone"),
	 request.getParameter("customerEmail"),
	 request.getParameter("customerPassword"));
	 session.setAttribute("statusMsg", stsMsg);
	 } 
}

//Delete item----------------------------------
if (request.getParameter("customerID") != null)
{
	Customer itemObj = new Customer();
String stsMsg = itemObj.deleteItem(request.getParameter("customerID"));
session.setAttribute("statusMsg", stsMsg);
} 

%>

<html>
<head>
<link rel="stylesheet" href="views/bootstrap.min.css">
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="View/bootstrap.min.css">

<title>customer Management</title>
</head>

<body>

<h1> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;  &nbsp; &nbsp;customer Management</h1>
<form method="post" action="customer.jsp">
<div class="container">
 <div class="row">
 <div class="col">


 Customer Name: <input name="customerName" type="text"  class="form-control"><br>
 Permanent Address: <input name="customerPhone" type="text"  class="form-control"><br>
 NIC: <input name="customerEmail" type="text"  class="form-control"><br>
 Customer Email: <input name="customerPassword" type="text"  class="form-control"><br>
 <input name="btnSubmit" type="submit" value="Save"class="btn btn-primary"><br>

 </div>
 </div>
</div>
</form>
<div class="alert alert-success">
 <% out.print(session.getAttribute("statusMsg"));%>
</div>
<br>
<%
Customer itemObj = new Customer();
 out.print(itemObj.readItems());
%>

</body>
</html>