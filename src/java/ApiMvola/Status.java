                                                              /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ApiMvola;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author ratsi
 */
public class Status {
 Token token = new Token();

 Map<String,Object> map = new HashMap<>();
 
    public boolean Status(String server,String amount , int param , String ref) throws ParseException{
        Boolean key = null;
       InsertionFirebase insert = new InsertionFirebase();
         try {
             URL url;
             url = new URL("https://devapi.mvola.mg/mvola/mm/transactions/type/merchantpay/1.0.0/status/"+server);
             HttpURLConnection http = (HttpURLConnection)url.openConnection();
             
             http.setRequestMethod("GET");
             http.setDoOutput(true);
             http.setRequestProperty("Authorization","Bearer "+ token.getToken());
             http.setRequestProperty("Version","1.0");
             http.setRequestProperty("X-CorrelationID","123e4567-e89b-12d3-a456-426614174000");
             http.setRequestProperty("UserAccountIdentifier","msisdn;0343500004");
             http.setRequestProperty("partnerName", "TestMVola");
             http.setRequestProperty("Content-Type", "application/json");
             http.setRequestProperty("Cache-Control", "no-cache");
             
             int response = http.getResponseCode();
            System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
             BufferedReader in = new BufferedReader(
                new InputStreamReader(http.getInputStream()));
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
                System.out.println("Application JSonS" + res);
JSONObject data_obj = (JSONObject) parse.parse(response1.toString());
   System.out.println("Object JSON Status" + data_obj.get("status").toString());
   
   map.put("Server",data_obj.get("serverCorrelationId"));
   map.put("Status",data_obj.get("status"));
   map.put("Montant",amount);
   map.put("refference", ref);
   
   if (data_obj.get("status").toString().equalsIgnoreCase("completed")){
       insert.addDonnée(map, param);
       key = true;
   
   }else{
                    key = false;
                    }
            }
             
         } catch (MalformedURLException ex) {
             Logger.getLogger(Status.class.getName()).log(Level.SEVERE, null, ex);
         } catch (ProtocolException ex) {
             Logger.getLogger(Status.class.getName()).log(Level.SEVERE, null, ex);
         } catch (IOException ex) {
             Logger.getLogger(Status.class.getName()).log(Level.SEVERE, null, ex);
         }
         return key;
    
    
    
    }
         }

