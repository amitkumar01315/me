package com.me.portal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.me.bo.dto.TransactionDto;

import jakarta.servlet.http.HttpServletRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MePortalUtility {

	private static final ObjectMapper objectMapper = new ObjectMapper();

	

	public static <T> T ObjectfromJson(String json, Class<T> valueType) {
		try {
			//Register the JavaTimeModule to enable JSR 310 types support
			objectMapper.registerModule(new JavaTimeModule());
			return objectMapper.readValue(json, valueType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getJsonOfObject(Object object) throws JsonProcessingException {

		ObjectMapper objectMapper = new ObjectMapper();

		// Register the JavaTimeModule to enable JSR 310 types support
		objectMapper.registerModule(new JavaTimeModule());

		return objectMapper.writeValueAsString(object);

	}

	public static String getResposeFromBo(String api, String requestBody) {
		System.out.println(api);
		System.out.println(requestBody);
		String finalResponse = null;
		try {

			// Create the URL of the API endpoint
			URL url = new URL(api);

			// Create the HttpURLConnection object
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setDoOutput(true);

			// Create the JSON request payload
//            String requestBody = "{\"key\":\"value\"}";

			// Write the JSON request to the connection's OutputStream
			OutputStream outputStream = connection.getOutputStream();
			outputStream.write(requestBody.getBytes());
			outputStream.flush();
			outputStream.close();

			// Get the response code
			int responseCode = connection.getResponseCode();

			if (responseCode == HttpURLConnection.HTTP_OK) {
				// Read the response from the connection's InputStream
				BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				StringBuilder response = new StringBuilder();
				String line;
				while ((line = reader.readLine()) != null) {
					response.append(line);
				}
				reader.close();

				// Print the JSON response
				// System.out.println("Response: " + response.toString());
				finalResponse = response.toString();
			} else {
				System.out.println("Error: " + responseCode);

			}

			// Disconnect the connection
			connection.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return finalResponse;
	}

	public static List<TransactionDto> requestToresponseForTransaction(HttpServletRequest request) throws JsonProcessingException {
		List<TransactionDto> transactionDtoLst=null;
		TransactionDto transactionDto = new TransactionDto();

		transactionDto.setUserId(request.getParameter("userId"));
		
		if(request.getParameter("month")!= null) {
			
			int month = Integer.parseInt(request.getParameter("month")); // Example: June (1-based index)
	        int year = 2023; // Example: 2023

	        LocalDate fromDate = getFromDateForMonth(month, year);
	        LocalDate toDate = getToDateForMonth(month, year);
	        transactionDto.setFromDate(fromDate.atStartOfDay());
	        transactionDto.setToDate(toDate.atTime(LocalTime.MAX));

	        
		}
		
		
		transactionDtoLst=Arrays.asList(ObjectfromJson(getResposeFromBo(RestURL.getTransaction, getJsonOfObject(transactionDto)),TransactionDto[].class));
		
		transactionDtoLst.stream().forEach(e -> System.out.println(e));
		
		return	transactionDtoLst;
	}
	
	 
	    public static LocalDate getFromDateForMonth(int month, int year) {
	        return LocalDate.of(year, month, 1);
	    }

	    public static LocalDate getToDateForMonth(int month, int year) {
	        LocalDate lastDayOfMonth = LocalDate.of(year, month, 1).withDayOfMonth(
	                LocalDate.of(year, month, 1).lengthOfMonth());
	        return lastDayOfMonth;
	    }
	    
	    
}
