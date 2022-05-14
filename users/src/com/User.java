package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class User {
	
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/electrogriddb", "root", "root");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	//read
	public String readUser()  
	{   
		String output = ""; 
	
		try   
		{    
			Connection con = connect(); 
		
			if (con == null)    
			{
				return "Error while connecting to the database for reading."; 
			} 
	 
			// Prepare the html table to be displayed    
			output = "<table border=\"1\">"
					+ "		<tr>"
					+ "			<th>User ID</th>"
					+ "			<th>Username</th>"
					+ "			<th>User NIC</th>"
					+ "			<th>User Phone Number</th>"
					+ "			<th>User Email</th>"
					+ "		</tr>";
	 
			String query = "SELECT * FROM users";
			Statement stat = con.createStatement();
			ResultSet rSet = stat.executeQuery(query);
	 
			// iterate through the rows in the result set    
			while(rSet.next()) {
				String userID = Integer.toString(rSet.getInt("userID"));
				String userName = Integer.toString(rSet.getInt("userName"));
				String userNIC = Double.toString(rSet.getDouble("userNIC"));
				String userPhoneNo = rSet.getString("userPhoneNo");
				String userEmail = rSet.getString("userEmail");

				// Add into the HTML table 
				output += "<tr><td><input id='hiduserIDUpdate' name='hiduserIDUpdate' type='hidden' value='" + userID + "'>" + userID + "</td>";
				output += 	"<td>" + userName +  "</td>";
				output += 	"<td>" + userNIC +  "</td>";
				output += 	"<td>" + userPhoneNo +  "</td>";
				output += 	"<td>" + userEmail +  "</td>";
				output += 	"</tr>";

				// buttons     
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary' data-userid='" + userID + "'></td>"       
						+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-userid='" + userID + "'>" + "</td></tr>"; 
		
			}
			con.close(); 
	 
			// Complete the HTML table    
			output += "</table>";   
		}   
		catch (Exception e)   
		{    
			output = "Error while reading the User.";    
			System.err.println(e.getMessage());   
		} 
	 
		return output;  
	}
	
	//insert user data
	public String insertUser(String userName, String userNIC, String userPhoneNo, String userEmail)  
	{   
		String output = ""; 
	 
		try
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{
				return "Error while connecting to database";
			} 
	 
			// create a prepared statement 
			String query = "INSERT INTO users (`userID`, `userName`,`userNIC`, `userPhoneNo`, `userEmail` )\"\r\n"
					+ "							+ \"VALUES (?,?,?,?,?)"; 
	 
	 
			PreparedStatement prepStat = con.prepareStatement(query);
	 
			// binding values    
			prepStat.setInt(1, 0);
			prepStat.setString(2, userName);
			prepStat.setString(3, userNIC);
			prepStat.setString(4, userPhoneNo);
			prepStat.setString(5, userEmail);
			
			// execute the statement    
			prepStat.execute();    
			con.close(); 
	   
			String newUser = readUser(); 
//			output =  "{\"status\":\"success\", \"data\": \"" + newUser + "\"}";   
			output = "User Inserted Successfully!";

		}   
		catch (Exception e)   
		{    
			output =  "{\"status\":\"error\", \"data\": \"Error while inserting New User.\"}";  
			System.err.println(e.getMessage());   
		} 
		
	  return output;  
	}
	
	//update
	
	public String updateUser(String userID, String userName, String userNIC, String userPhoneNo, String userEmail)    
	{   
		String output = ""; 
	 
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{
				return "Error while connecting to database.";
			} 
	 
			// create a prepared statement    
			String query = "UPDATE users SET userName=?, userNIC=?, userPhoneNo=?, userEmail=? where userID=?"; 
	 
			PreparedStatement prepStat = con.prepareStatement(query);
			
			//binding values
			prepStat.setInt(1, 0);
			prepStat.setString(2, userName);
			prepStat.setString(3, userNIC);
			prepStat.setString(4, userPhoneNo);
			prepStat.setString(5, userEmail);
			
			 // execute the statement
			prepStat.execute();    
			con.close(); 
	 
			String newUser = readUser();    
			output = "{\"status\":\"success\", \"data\": \"" + newUser + "\"}";    
		}   
		catch (Exception e)   
		{    
			output =  "{\"status\":\"error\", \"data\": \"Error while updating the User.\"}";   
			System.err.println(e.getMessage());   
		} 
	 
	  return output;  
	} 
	
	//delete
	public String deleteUser(String userID)   
	{   
		String output = ""; 
	 
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{
				return "Error while connecting to the database for deleting."; 
			} 
	 
			// create a prepared statement    
			String query = "DELETE FROM users WHERE userID=?";  
	 
			PreparedStatement prepStat = con.prepareStatement(query); 
	 
			// binding values    
			prepStat.setInt(1, Integer.parseInt(userID)); 
	 
			// execute the statement    
			prepStat.execute();    
			con.close(); 
	 
			String newUser = readUser();  
			    
			output = "{\"status\":\"success\", \"data\": \"" +  newUser + "\"}";    
		}   
		catch (Exception e)   
		{    
			output = "{\"status\":\"error\", \"data\":\"Error while deleting the User.\"}";    
			System.err.println(e.getMessage());   
		} 
	 
		return output;  
	}
}
