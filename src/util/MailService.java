/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import Constants.GlobalConstants;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author vishal
 */
public class MailService {

    private String message1;
    private String message2;
    private String buttonValue;
    private String buttonUrl;
    private Map<String, String> map;
    private String htmlText;

    public MailService(String message1, String message2, String buttonValue, String buttonUrl) {
        map = new HashMap<>();
        map.put("message1", message1);
        map.put("message2", message2);
        map.put("buttonValue", buttonValue);
        map.put("buttonUrl", buttonUrl);

        this.htmlText = readEmailFromHtml(GlobalConstants.SERVER_PATH, map);

    }

    //Method to replace the values for keys
    private String readEmailFromHtml(String filePath, Map<String, String> input) {
        String msg = readContentFromFile(filePath);
        try {
            Set<Map.Entry<String, String>> entries = input.entrySet();
            for (Map.Entry<String, String> entry : entries) {
                msg = msg.replace(entry.getKey().trim(), entry.getValue().trim());
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return msg;
    }

    //Method to read HTML file as a String 
    private String readContentFromFile(String fileName) {
        StringBuilder contents = new StringBuilder();

        try {
            //use buffering, reading one line at a time
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            try {
                String line = null;
                while ((line = reader.readLine()) != null) {
                    contents.append(line);
                    contents.append(System.getProperty("line.separator"));
                }
            } finally {
                reader.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return contents.toString();
    }

    public boolean sendMail(String to, String subject) {
        return Mail.send(to, subject, this.htmlText);
    }
    
    public static void main(String[] args) {
        MailService ms = new MailService("first","last","click","#");
        ms.sendMail("vishal.6794@gmail.com", "test");
    }
}
