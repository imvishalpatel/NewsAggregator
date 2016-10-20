/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 *
 * @author vishal
 */
public class Confirmation {
    
    public static void generateConfrimKey(String username,String email){
        String key = encode(email);
        
      //  MongoClient mongo = (MongoClient) request.getServletContext().getAttribute("MONGO_CLIENT");
     //   ConfirmationDAO confrimDao = new ConfirmationDAO(mongo);
    }
    public static String encode(String encodeString) {
        if (encodeString == null || encodeString.equals("")) {
            throw new NullPointerException();
        }
        String returnString = Base64.getEncoder().withoutPadding().encodeToString(encodeString.getBytes());

        return returnString;
    }

    public static String decode(String decodedString) {
        if (decodedString == null || decodedString.equals("")) {
            throw new NullPointerException();
        }
        return new String(Base64.getDecoder().decode(decodedString));
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        System.out.println(encode("vishal6794"));
        System.out.println(decode("dmlzaGFsNjc5NA"));
    }

 
}
