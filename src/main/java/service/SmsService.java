package service;

import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

@Service
public class SmsService {

    public void sendOtp(String mobile, String otp) {
        String apiUrl = "https://api.sms.gateway.me/send";
        String apiKey = "YOUR_API_KEY"; // Replace with your actual API key
        
        String message = "Your OTP is: " + otp;
        
        // Set up headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/x-www-form-urlencoded");

        // Set up request body
        String requestBody = "apiKey=" + apiKey + "&to=" + mobile + "&message=" + message;

        // Create HttpEntity
        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        // Create RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();

        // Send POST request to SMS Gateway API
        ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.POST, entity, String.class);

        // Check response
        if (response.getStatusCode() == HttpStatus.OK) {
            System.out.println("OTP Sent Successfully: " + otp);
        } else {
            System.err.println("Failed to send OTP: " + response.getStatusCode());
        }
    }
}
