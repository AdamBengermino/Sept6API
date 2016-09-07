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

public class Delete 
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
			//userID of user you want to delete
			String userID = "id=14"; 
			
			//creates a new URL out of the endpoint and userID
			URL employee = new URL(endpoint + "?" + userID); 
			HttpURLConnection connection = (HttpURLConnection) employee.openConnection();
			connection.setRequestMethod("DELETE");
			
			//if we did not get a 200 (success) throw an exception
			if(connection.getResponseCode() != 200)
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