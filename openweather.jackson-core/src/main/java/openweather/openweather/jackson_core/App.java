package openweather.openweather.jackson_core;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Hello world!
 *
 */
public class App 
{
	private static Logger logger = Logger.getLogger(App.class);
    public static void main(String[] args) throws IOException {
    	URL url = new URL(
				"http://api.openweathermap.org/data/2.5/weather?q=Paris&appid=abbcea2020f75409af198b98de40e3a6");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		con.setConnectTimeout(5000);
		con.setReadTimeout(5000);
		int status = con.getResponseCode();
		if (status == HttpURLConnection.HTTP_OK)
			try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
				ObjectMapper objectMapper = new ObjectMapper();
				JsonNode rootNode = objectMapper.readTree(in);
				JsonNode cityName = rootNode.path("name");
				logger.info(cityName.asText() + ": ");
				JsonNode cityWeather = rootNode.path("weather");
				for (JsonNode jsonNode : cityWeather) 
					logger.info(jsonNode.path("description").toString());
			}
		
	}
}
