/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package API;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author ratsi
 */
public class Refund {
    
    
    public boolean Enquiry(String Ref, String acces) throws ProtocolException, IOException, ParseException{
        boolean resu = false;
        try {
            URL obj = new URL("https://openapiuat.airtel.africa/standard/v1/payments/"+Ref);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.setDoOutput(true);
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Authorization", "Bearer " +acces);
            con.addRequestProperty("X-Country", "MG");
            con.addRequestProperty("X-Currency", "MGA");
             int response = con.getResponseCode();
           System.out.println(con.getResponseCode() + " " + con.getResponseMessage());
             BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response1 = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response1.append(inputLine);
            }
           // System.out.println(response1.toString());



                String inline = "";
            if (response != 200) {
                throw new RuntimeException("HttpResponseCode: " + response);
            } else {
String res = response1.toString();
JSONParser parse = new JSONParser();
                System.out.println("REsponse" +response1.toString() );
JSONObject data_obj = (JSONObject) parse.parse(response1.toString());
   System.out.println("Object JSON Status" + data_obj.get("status"));




JSONObject data_obj3 = (JSONObject) parse.parse(data_obj.get("data").toString());
System.out.println("Object JSON Transaction" + data_obj3.get("transaction"));
               
JSONObject transaction = (JSONObject) parse.parse(data_obj3.get("transaction").toString());

                System.out.println("transaction" + data_obj3.get("transaction").toString());

                System.out.println("stat  "+ transaction.get("status").toString());
             
if( transaction.get("status").toString().equalsIgnoreCase("TS")){
resu = true;
}else {
    resu = false;
    System.out.println(con.getResponseCode());
}

            }
           
            in.close();
        } catch (MalformedURLException ex) {
            Logger.getLogger(Refund.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return resu;
    
    
    }
}
