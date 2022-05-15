package model;

import java.sql.*;

public class Customer {
	// A common method to connect to the DB
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/customerdetails", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
//insert
	public String insertcustomerdetails(String name, String customerPhone, String email, String password) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into customerdetails (`id`,`name`,`address`,`nic`,`email`)  values (?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, name);
			preparedStmt.setString(3, customerPhone);
			preparedStmt.setString(4, email);
			preparedStmt.setString(5, password);
			// execute the statement

			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}
//get
	public String readItems() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Customer Name</th>" + "<th>Permanent Address</th>" + "<th>NIC</th>"
					+ "<th>Customer Email</th>" + "<th>Update</th><th>Remove</th></tr>";

			String query = "select * from customerdetails";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String customerID = Integer.toString(rs.getInt("id"));
				String customerName = rs.getString("name");
				String customerPhone = rs.getString("address");
				String customerEmail = rs.getString("nic");
				String customerPassword = rs.getString("email");

				output += "<tr><td>" + customerName + "</td>";
				output += "<td>" + customerPhone + "</td>";
				output += "<td>" + customerEmail + "</td>";
				output += "<td>" + customerPassword + "</td>";
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
						+ "<td><form method='post' action='customer.jsp'>"
						+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
						+ "<input name='customerID' type='hidden' value='" + customerID + "'>" + "</form></td></tr>";
			}
			con.close();

			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the items.";
			System.err.println(e.getMessage());
		}
		return output;
	}
//
	public String updatecustomerdetails(String ID, String name, String address, String nic, String email)

	{
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement	                1        2         3         4                5
			String query = "UPDATE customerdetails SET name=?,address=?,nic=?,email=? where id=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, name);//1
			preparedStmt.setString(2, address); // 2
			preparedStmt.setString(3, nic);//3
			preparedStmt.setString(4, email);//4
			preparedStmt.setInt(5, Integer.parseInt(ID));//5
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}
//
	public String deleteItem(String customerID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from customerdetails where id=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(customerID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}
}
