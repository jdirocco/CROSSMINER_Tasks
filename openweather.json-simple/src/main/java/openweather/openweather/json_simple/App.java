package openweather.openweather.json_simple;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Parse open wather API
 *
 */
public class App {
	public static void main(String[] args) throws IOException, ParseException {
		URL url = new URL(
				"http://api.openweathermap.org/data/2.5/weather?q=Paris" + "&appid=abbcea2020f75409af198b98de40e3a6");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		con.setConnectTimeout(5000);
		con.setReadTimeout(5000);
		int status = con.getResponseCode();
		if (status == HttpURLConnection.HTTP_OK)
			try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
				JSONParser parser = new JSONParser();
				JSONObject jsonObject = (JSONObject) parser.parse(in);
				String cityName = (String) jsonObject.get("name");
				System.out.print(cityName + ": ");
				// loop array
				JSONArray msg = (JSONArray) jsonObject.get("weather");
				Iterator<JSONObject> iterator = msg.iterator();
				while (iterator.hasNext()) {
					System.out.println(((JSONObject) iterator.next()).get("description"));
				}
			}
	}
}
