package com.div.shadi_bazar.Utils;

public class StringUtils {
    public static String getUserNameAndPasswordFromBase64(String userNameAndPassword) {
        String base64Credentials = userNameAndPassword.substring("Basic ".length()).trim();
        // Decode the base64 encoded
        byte[] decodedBytes = java.util.Base64.getDecoder().decode(base64Credentials);
        String credentials = new String(decodedBytes);
        
        return credentials; // Return both username and password
    }
}
