package localhost.javaSailRest.employee;

import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.InputStreamReader; 
import java.net.HttpURLConnection;
import java.net.MalformedURLException; 
import java.net.URL; 
import java.net.URLEncoder; 


/**
 * Class practice with sails/java
 * @author Adam Bengermino
 * @since 2016-09-07
 *
 */

public class Create 
{
/**
 * The URL of the API we want to connect to
 */
	
	protected static String endpoint = "http://localhost:1337/employee";
	
/**
 * The character set to use when encoding URL parameters
 */
	protected static String charset = "UTF-8"; 

/**
 * API key used for making requests to API
 */
	
	public static void main(String[] args) 
	{
		try
		{
			//The employee first name
			String firstName = "Adam"; 
			
			//The employee last name
			String lastName = "Bengermino"; 
			
			//employee email address
			String email = "adamadam@adam.com"; 
			
			//employee home phone number
			String homePhone = "342-888-6543"; 
			
			//employee cell phone number
			String cellPhone = "908-555-9090"; 
			
			//employee password
			String password = "Supersu9";
			
			//is this an active employee?
			String active = "1"; 
			
			//creates the URL parameters as a string encoding them with the defined charset
			String queryString = String.format("firstName=%s&lastName=%s&email=%s&homePhone=%s&cellPhone=%s&password=%s&active=%s", 
					URLEncoder.encode(firstName, charset),
					URLEncoder.encode(lastName, charset),
					URLEncoder.encode(email, charset),
					URLEncoder.encode(homePhone, charset), 
					URLEncoder.encode(cellPhone, charset), 
					URLEncoder.encode(password, charset),
					URLEncoder.encode(active, charset));
			
			//creates a new URL out of the endpoint, returnType and queryString
			URL employee = new URL(endpoint + "?" + queryString); 
			HttpURLConnection connection = (HttpURLConnection) employee.openConnection();
			connection.setRequestMethod("POST");
			
			//if we did not get a 200 (success) throw an exception
			if(connection.getResponseCode() != 201)
			{
				throw new RuntimeException("Failed : HTTP error code: " + connection.getResponseCode());
			}
			
			//read response into buffer
			BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));
			
			//loop of buffer reader line by line until it returns null meaning there are no more lines
			while (br.readLine() != null)
			{
				//print out each line to the screen
				System.out.println(br.readLine());
			}
			
			//close connection to API
			
			connection.disconnect(); 
		}//try
		
		catch (MalformedURLException e)
		{
			e.printStackTrace();
		}
		
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}//main
	
}//class