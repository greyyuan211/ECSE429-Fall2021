package org.springframework.samples.petclinic.performance;

import io.micrometer.core.instrument.util.IOUtils;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.PetType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.time.LocalDate;

public class Utils {

	static String returnCode = "200";

	private static String generate_random_string() {

		String characters = "abcdefghijklmnopqrstuvwxyz";
		int length = 7;
		SecureRandom random = new SecureRandom();
		StringBuilder sb = new StringBuilder(length);

		for (int i = 0; i < length; i++) {
			int rndCharAt = random.nextInt(characters.length());
			char randomChar = characters.charAt(rndCharAt);
			sb.append(randomChar);
		}
		return sb.toString();
	}

	public static Pet createPet() {
		Pet pet = new Pet();
		pet.setName(generate_random_string());
		pet.setBirthDate(LocalDate.parse("2020-06-01"));
		PetType type = new PetType();
		type.setName("cat");
		pet.setType(type);
		return pet;
	}

	public static Owner createOwner() {
		Owner owner = new Owner();
		owner.setTelephone("82647363");
		owner.setLastName(generate_random_string());
		owner.setFirstName(generate_random_string());
		owner.setCity("city");
		owner.setAddress(generate_random_string());
		return owner;
	}

	public static boolean testConnection(String baseUrl) {
		try {
			URL url = new URL(baseUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.connect();
			connection.disconnect();
			return true;
		}
		catch (IOException e) {
			return false;
		}
	}

	public static void sendRequest(String request, String baseUrl, String path, byte[] postDataBytes) {
		try {
			URL url = new URL(baseUrl + path);
			// System.out.println("Sending: " + " " + url.toString() + " with body " + new
			// String(postDataBytes));
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setInstanceFollowRedirects(true);
			connection.setDoInput(true);
			connection.setDoOutput(true);
			// OutputStreamWriter writer = new
			// OutputStreamWriter(connection.getOutputStream());
			connection.setRequestMethod(request);
			connection.setRequestProperty("Accept", "text/html");
			connection.setRequestProperty("charset", "UTF-8");
			if (request != "GET") {
				connection.getOutputStream().write(postDataBytes);
			}

			returnCode = connection.getResponseCode() + "";
			// System.out.println("error message: " +
			// IOUtils.toString(connection.getErrorStream(), StandardCharsets.UTF_8));
			// System.out.println("Response Code: "+ connection.getResponseCode() + " " +
			// connection.getResponseMessage());

			BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));
			String response = br.readLine();
			// System.out.println("response: " + response);
			if (response != null) {

				connection.disconnect();
				return;
			}
		}
		catch (IOException e) {
			// e.printStackTrace();
		}
		return;
	}

	public static void sendRequest2(String baseUrl, String path, String urlParameters) {
		try {

			URL url = new URL(baseUrl + path);
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);

			OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
			writer.write(urlParameters);
			writer.flush();

			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			writer.close();
			reader.close();
		}
		catch (IOException e) {
			// e.printStackTrace();
		}
	}

	public static int getResponseCode(String requestType, String baseUrl, String path, String body) {
		try {
			URL url = new URL(baseUrl + path);
			// System.out.println("Sending: " + requestType + " " + url.toString());
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();

			connection.setDoInput(true);
			connection.setDoOutput(true);

			connection.setRequestMethod(requestType);
			// connection.setRequestProperty("Accept", "application/json");
			// connection.setRequestProperty("Content-Type", "application/json;
			// charset=UTF-8");

			if (body != "") {
				OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
				writer.write(body);
				writer.close();
			}

			connection.disconnect();
			return connection.getResponseCode();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
