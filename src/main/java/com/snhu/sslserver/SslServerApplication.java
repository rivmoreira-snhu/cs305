package com.snhu.sslserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@SpringBootApplication
@RestController
public class SslServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SslServerApplication.class, args);
	}
	
    // A simple GET call that returns the checksum of static data using the SHA-256 hash algorithm
    @GetMapping("/checksum")
    public String getChecksum() {
        // the data string to generate the checksum for
        String data = "Hello World Check Sum!";
        // returning the checksum of the data in SHA-256 format
        return "Checksum (SHA-256): " + getSHA256Checksum(data);
    }

    // utility function that generates the SHA-256 checksum of a string
    private String getSHA256Checksum(String data) {
        try {
            // using MessageDigest class to get an instance of the SHA-256 algorithm
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            // converting the data string into bytes and calculating the hash
            byte[] hash = digest.digest(data.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();

            // for each byte, convert to hexadecimal string and append to the result
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            // returning the final checksum in hexadecimal format
            return hexString.toString();
        } catch (Exception e) {
            // catching any exceptions and converting to runtime exceptions
            throw new RuntimeException(e);
        }
    }
}
//FIXME: Add route to enable check sum return of static data example:  String data = "Hello World Check Sum!";

