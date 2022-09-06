package msd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class GenshinAPI {

    private static HttpURLConnection conn;
	private final ObjectMapper objectMapper = new ObjectMapper();

    public String fetchTalentBook() {
		
		BufferedReader reader;
		String line;
		StringBuilder responseContent = new StringBuilder();
		try{
			URL url = new URL("https://api.genshin.dev/materials/talent-book");
			conn = (HttpURLConnection) url.openConnection();
			
			// Request setup
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(5000);// 5000 milliseconds = 5 seconds
			conn.setReadTimeout(5000);
			
			// Test if the response from the server is successful
			int status = conn.getResponseCode();
			
			if (status >= 300) {
				reader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
				while ((line = reader.readLine()) != null) {
					responseContent.append(line);
				}
				reader.close();
			}
			else {
				reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				while ((line = reader.readLine()) != null) {
					responseContent.append(line);
				}
				reader.close();
			}
			String fetched_content = responseContent.toString();
			return fetched_content;

		}
		catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			conn.disconnect();
		}

		return new String();
			
	}

	public String fetchWeaponAscension() {
		
		BufferedReader reader;
		String line;
		StringBuilder responseContent = new StringBuilder();

		try{

			URL url = new URL("https://api.genshin.dev/materials/weapon-ascension");
			conn = (HttpURLConnection) url.openConnection();
			
			// Request setup
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(5000);// 5000 milliseconds = 5 seconds
			conn.setReadTimeout(5000);
			
			// Test if the response from the server is successful
			int status = conn.getResponseCode();
			
			if (status >= 300) {
				reader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
				while ((line = reader.readLine()) != null) {
					responseContent.append(line);
				}
				reader.close();
			}
			else {
				reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				while ((line = reader.readLine()) != null) {
					responseContent.append(line);
				}
				reader.close();
			}
			String fetched_content = responseContent.toString();
			
			return fetched_content;

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			conn.disconnect();
		}

		return new String();
			
	}

	public String getCharactersList(){
	
		BufferedReader reader;
		String line;
		StringBuilder responseContent = new StringBuilder();
		try{
			URL url = new URL("https://api.genshin.dev/characters");
			conn = (HttpURLConnection) url.openConnection();
			
			// Request setup
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(5000);// 5000 milliseconds = 5 seconds
			conn.setReadTimeout(5000);
			
			// Test if the response from the server is successful
			int status = conn.getResponseCode();
			
			if (status >= 300) {
				reader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
				while ((line = reader.readLine()) != null) {
					responseContent.append(line);
				}
				reader.close();
			}
			else {
				reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				while ((line = reader.readLine()) != null) {
					responseContent.append(line);
				}
				reader.close();
			}
			String fetched_content = responseContent.toString();

			return fetched_content;

		}
		catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			conn.disconnect();
		}

		return new String();
	}

}
