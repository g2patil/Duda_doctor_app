package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.ClubUser;
import repository.ClubUserRepository;

@Service
public class OTPService {

    @Autowired
    private ClubUserRepository clubUserRepository;
    
    @Autowired
    private SmsService smsService;

    public String generateOTP(String mobile) {
        ClubUser user = clubUserRepository.findByMob(mobile)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String otp = String.valueOf((int) (Math.random() * 900000) + 100000); // Generate 6-digit OTP
        user.setOtp(otp);
        clubUserRepository.save(user);

        // Use an SMS gateway to send the OTP to the user's mobile number
        sendOtpToMobile(mobile, otp);

        return "OTP sent to " + mobile;
    }

    public boolean validateOTP(String mobile, String otp) {
        ClubUser user = clubUserRepository.findByMob(mobile)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return otp.equals(user.getOtp());
    }

    private void sendOtpToMobile(String mobile, String otp) {
        // Implement SMS gateway integration here
    	 String response = ""; // Assume you're using an HTTP client to send the request
    	    try {
    	    	/*String phoneNumber = "9960059223";
    	        String command = "adb shell am broadcast -a android.intent.action.SENDTO -d sms:" + phoneNumber + " --es \"sms_body\" \"" + otp + "\" --ez \"exit_on_sent\" true";
    	        Process process = Runtime.getRuntime().exec(command);
    	        process.waitFor();
    	        System.out.println("OTP sent successfully.");
    	    	*/
    	         smsService.sendOtp(mobile, otp); // Example SMS service call
    	        System.out.println("SMS Gateway Response: " + response);
    	    } catch (Exception e) {
    	        System.err.println("Failed to send OTP: " + e.getMessage());
    	    }
        System.out.println("Sending OTP " + otp + " to mobile " + mobile);
    }
}
