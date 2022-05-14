 $(document).ready(function()
{
	if ($("#alertSuccess").text().trim() == "")
	 {
	 	$("#alertSuccess").hide();
	 }
	 $("#alertError").hide(); 

}); 


$(document).on("click", "#btnSave", function(event)
{
	//Clear status msges---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	
	//Form validation-------------------
	var status = validateUserForm();
	
	//If not valid
	if (status != true)
	{
		 $("#alertError").text(status);
		 $("#alertError").show();
		return;
	}
	
	//If valid
	$("#formUsers").submit();
	
	var type = ($("#hiduserIDSave").val() == "") ? "POST" : "PUT";

	$.ajax(
 	{
 		url : "UserAPI",
 		type : type,
 		data : $("#formUsers").serialize(),
 		dataType : "text",
 		complete : function(response, status)
 		{
 			onUserSaveComplete(response.responseText, status);
 		}
 	}); 
});



function onUserSaveComplete(response, status)
	{
		if (status == "success")
		{
			 var resultSet = JSON.parse(response);
 			 if (resultSet.status.trim() == "success")
			 {
 				$("#alertSuccess").text("Successfully saved.");
 				$("#alertSuccess").show();
 				$("#divMeterGrid").html(resultSet.data);
 			 } 
 			 else if (resultSet.status.trim() == "error")
			 {
 				$("#alertError").text(resultSet.data);
 				$("#alertError").show();
 			 }
 		} 
 		else if (status == "error")
 		{
 			$("#alertError").text("Error while saving.");
 			$("#alertError").show();
 		} 
 		else
 		{
 			$("#alertError").text("Unknown error while saving..");
 			$("#alertError").show();
 		}
		$("#hidMeterIDSave").val("");
 		$("#formMeter")[0].reset();
}


// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
	$("#hiduserIDSave").val($(this).data("userID"));
	$("#txtUserName").val($(this).closest("tr").find('td:eq(0)').text());
	$("#txtUserNIC").val($(this).closest("tr").find('td:eq(1)').text());
	$("#txtUserPhone").val($(this).closest("tr").find('td:eq(2)').text());
	$("#txtUserEmail").val($(this).closest("tr").find('td:eq(3)').text());
}); 


// REMOVE==========================================
$(document).on("click", ".btnRemove", function(event)
{
	 $.ajax(
 		{
 			url : "UserAPI",
 			type : "DELETE",
 			data : "userID=" + $(this).data("userID"),
 			dataType : "text",
 			complete : function(response, status)
 			{
 				onMeterDeleteComplete(response.responseText, status);
 			}
 		});
}); 

function onUserDeleteComplete(response, status)
{
		if (status == "success")
 		{
 			var resultSet = JSON.parse(response);
 			if (resultSet.status.trim() == "success")
 			{
 				$("#alertSuccess").text("Successfully deleted.");
 				$("#alertSuccess").show();
 				$("#divMeterGrid").html(resultSet.data);
 			} 
 			else if (resultSet.status.trim() == "error")
 			{
 				$("#alertError").text(resultSet.data);
 				$("#alertError").show();
 			}
 		} 
 		else if (status == "error")
 		{
 				$("#alertError").text("Error while deleting.");
 				$("#alertError").show();
 		} 
 		else
 		{
 				$("#alertError").text("Unknown error while deleting..");
 				$("#alertError").show();
 		}
}


function validateUserForm()
{
	//UserName
	if ($("#txtUserName").val().trim() == "")
	{
		return "Insert Username";
	}
	
	//UserNIC
	if ($("#txtUserNIC").val().trim() == "")
	{
		return "Insert User NIC";
	}
	
	//UserPhoneNumber
	if ($("#txtUserPhone").val() == "")
	{
		return "Insert User Phone Number";
	}
	
	//UserEmail
	if ($("#txtUserEmail").val() == "")
	{
		return "Insert User Email";
	}
	
	return true;
}


// function getUserCard(userName, userNIC, UserPhoneNo, payDate)
// {
	
// 	var payment = "";
// 	payment += "<div class=\"student card bg-light m-2\" style=\"max-width: 10rem; float: left;\">";
// 	payment += "<div class=\"card-body\">";
// 	payment += "accountID: " + accountID + ",";
// 	payment += "<br>";
// 	payment += "Amount: " + amount + ",";
// 	payment += "<br>";
// 	payment += "Pay Method: " + payMethod + ",";
// 	payment += "<br>";
// 	payment += "Date: " + payDate;
// 	payment += "</div>";
// 	payment += "<input type=\"button\" value=\"Remove\" class=\"btn btn-danger remove\">";
// 	payment += "</div>";
	
// 	return payment;
// }

