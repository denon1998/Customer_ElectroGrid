package com;
import model.Customer;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;
@Path("/Customer")

public class CustomerService {
	Customer Obj = new Customer();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems()
	 {
	 return Obj.readItems();
	 }
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertItem(@FormParam("name") String customerName,@FormParam("address") String customerPhone,@FormParam("nic") String customerEmail,@FormParam("email") String customerPassword)
	{
	 String output = Obj.insertcustomerdetails(customerName, customerPhone, customerEmail,customerPassword);
	return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateItem(String itemData)
	{
	//Convert the input string to a JSON object
	 JsonObject Object = new JsonParser().parse(itemData).getAsJsonObject();
	//Read the values from the JSON object
	 String customerID = Object.get("id").getAsString();
	 String customerName = Object.get("name").getAsString();
	 String customerPhone = Object.get("address").getAsString();
	 String customerEmail = Object.get("nic").getAsString();
	 String customerPassword = Object.get("email").getAsString();
	 String output = Obj.updatecustomerdetails(customerID, customerName, customerPhone, customerEmail, customerPassword);
	return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteItem(String itemData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(itemData, "", Parser.xmlParser());

	//Read the value from the element <itemID>
	 String customerID = doc.select("customerID").text();
	 String output = Obj.deleteItem(customerID);
	return output;
	}

	
}
