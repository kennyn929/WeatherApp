import java.io.IOException;
import java.text.*;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.net.*;

public class WeatherApp 
{
	private int zipCode;
	private String inLine;
	
	private JSONArray jsonArr;
	private JSONObject jsonObj_1;
	
	
	
	
	public WeatherApp() throws IOException, ParseException
	{
		
		this.zipCode = 28216;
		inLine = new String();//Store data from API as String
		
		//Using postal code
		//URL url = new URL("https://api.weatherbit.io/v2.0/current?postal_code=28216,NC&key=479e40d0601a4cf58b8203b49a7cd445&units=I");
		URL url = new URL("https://api.weatherbit.io/v2.0/current?postal_code=" + zipCode + "&key=479e40d0601a4cf58b8203b49a7cd445&units=I");
		
		//Establish connection to URL
		HttpURLConnection urlCon = (HttpURLConnection) url.openConnection();
		urlCon.setRequestMethod("GET");
		urlCon.connect();
		
		//Check for error
		int responseCode = urlCon.getResponseCode();
		if (responseCode != 200)
		{
			throw new RuntimeException("HttpResponseCode: " + responseCode);
		}
		//Gets data from URL
		else
		{
			Scanner sc = new Scanner(url.openStream());
			
			while (sc.hasNext())
			{
				inLine += sc.nextLine();
			}
			
			//Print data
			//System.out.println("JSON data in String format:");
			//System.out.println(inLine);
			sc.close();
		}
		
		JSONParser parse = new JSONParser();
		JSONObject jobj = (JSONObject)parse.parse(inLine);
		
		//Convert JSON object into JSONArray object
		jsonArr = (JSONArray) jobj.get("data");
		
		//Get data from array
		jsonObj_1 = (JSONObject)jsonArr.get(0);
	}
	
	public int refreshData() throws IOException, ParseException
	{
		inLine = new String();//Store data from API as String
		
		//Using postal code
		//URL url = new URL("https://api.weatherbit.io/v2.0/current?postal_code=28216,NC&key=479e40d0601a4cf58b8203b49a7cd445&units=I");
		URL url = new URL("https://api.weatherbit.io/v2.0/current?postal_code=" + zipCode + "&key=479e40d0601a4cf58b8203b49a7cd445&units=I");
				
		
		//Establish connection to URL
		HttpURLConnection urlCon = (HttpURLConnection) url.openConnection();
		urlCon.setRequestMethod("GET");
		urlCon.connect();
		
		//Check for error
		int responseCode = urlCon.getResponseCode();
		if (responseCode == 204) // Common response code for syntax errors
		{
			//throw new RuntimeException("Syntax Error: You are inputting things wrong!");
			throw new RuntimeException("Syntax Error: You are inputting things wrong!");
		}
		else if (responseCode != 200) // Anything but ResponseCode 200 is bad
		{
			throw new RuntimeException("HttpResponseCode: " + responseCode);
		}
		//Gets data from URL
		else
		{
			Scanner sc = new Scanner(url.openStream());
			
			while (sc.hasNext())
			{
				inLine += sc.nextLine();
			}
			
			//Print data
			//System.out.println("JSON data in String format:");
			//System.out.println(inLine);
			sc.close();
		}
		
		
		JSONParser parse = new JSONParser();
		JSONObject jobj = (JSONObject)parse.parse(inLine);
		
		//Convert JSON object into JSONArray object
		jsonArr = (JSONArray) jobj.get("data");
		
		//Get data from array
		jsonObj_1 = (JSONObject)jsonArr.get(0);
		
		return responseCode;
		
	}
	
	public void setZipCode(String code)
	{
		int tempCode = -1;
		try
		{
			tempCode = Integer.parseInt(code);
		}
		catch (NumberFormatException e)
		{
			System.out.println("Error: Input cannot be converted to an integer.");
		}
		if (tempCode > 9999 && tempCode < 100000)
		{
			this.zipCode = tempCode;
		}
		else
		{
			System.out.println("Error: You didn't input a 5 digit number.");
		}
	}
	
	public int getZipCode()
	{
		return this.zipCode;
	}
	
	
	public String getTemperature() throws ParseException
	{
		Object temperature = jsonObj_1.get("temp");
		return "" + temperature + " F";
	}
	
	
	
}
