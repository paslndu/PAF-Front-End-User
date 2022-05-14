<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/main.js"></script>

<title>Users</title>
</head>
<body>


	<div class="container">
		 <div class="row">
			 <div class="col-8">
			 
				 <h1 class="m-3">Users details</h1>
				 <form id="formUsers">
				 
				 	<!-- UserName -->
					<div class="input-group input-group-sm mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text" id="lblUserName">User Name: </span>
						</div>
						
						<input type="text" id="txtUserName" name="txtUserName">
					</div>
					
					<!-- User NIC -->
					<div class="input-group input-group-sm mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text" id="lblUserNIC">User NIC: </span>
						</div>
						
						<input type="text" id="txtUserNIC" name="txtUserNIC">
					</div>
					
					<!-- User Phone Number -->
					<div class="input-group input-group-sm mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text" id="lblUserPhone"> User Phone Number: </span>
						</div>
						
						<input type="text" id="txtUserPhone" name="txtUserPhone" placeholder="Add User Phone Number">
					</div>
					
					<!-- User Email -->
					<div class="input-group input-group-sm mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text" id="lblUserEmail">User Email: </span>
						</div>
						
						<input type="text" id="txtUserEmail" name="txtUserEmail" placeholder="account@example.com">
					</div>
					
					
					<div id="alertSuccess" class="alert alert-success"></div>
 					<div id="alertError" class="alert alert-danger"></div>
					
					
					<input type="button" id="btnSave" value="Save" class="btn btn-primary">
					<input type="hidden" id="hidUserIDSave" name="hidUserIDSave" value="">
				 
				 </form>
			 </div>
		 </div>
		
		 <br>
		 <div class="row">
			 <div class="col-12" id="colUser">
				<%
				User userObj = new User();
				out.print(userObj.readUser());
				%>
			 </div>
		 </div>
	</div>

</body>
</html>