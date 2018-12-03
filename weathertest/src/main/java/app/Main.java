package app;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jsonmap.WeatherJson;

public class Main {

	static String url = "http://api.openweathermap.org/data/2.5/weather?q=leipzig,de&units=metric&APPID=b1ed965978e17dbe12f1021c7f65db57";

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String output = null;
		DownloadJson downloadJson = new DownloadJson(url);

		output = downloadJson.getUrlStringOutput();
		System.out.println(output);
		
		ObjectMapper objectMapper = new ObjectMapper();
		WeatherJson weatherJson = new WeatherJson();
		try {
			weatherJson = objectMapper.readValue(output, WeatherJson.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(weatherJson.getMain().getTemp());
		
		Database database = new Database();
		database.checkDatabaseClass();
		database.connectToDatabase();
	}

}
