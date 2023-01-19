/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ApiMvola;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author ratsi
 */
public class ApiConnection {
    private static final String POST_URL = "https://devapi.mvola.mg/mvola/mm/transactions/type/merchantpay/1.0.0/";
Token token = new Token();
String resultat;
Status stat = new Status();
private  SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

    public  boolean sendPOST(String amount, int param , String refference)  {
        String key = token.getToken();
        boolean resu = false;
        
        String ref =refference;
       
         Timestamp timestamp = new Timestamp(System.currentTimeMillis());;
        String date1 = sdf2.format(timestamp);
		URL obj;
        try {
            obj = new URL(POST_URL);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("POST");
                con.setRequestProperty("Authorization", "Bearer "+ key);
                con.setRequestProperty("Version", "1.0");
                con.setRequestProperty("X-CorrelationID", "123e4567-e89b-12d3-a456-426614174000");
             
                con.setRequestProperty("Content-Type", "application/json");
                 con.setRequestProperty("Cache-Control", "no-cache");
		// For POST only - START
                System.out.println(date1);
                String data = "{\n \"amount\" : \""+amount+"\","
                        + "\n \"currency\" : \"Ar\","
                        + "\n \"descriptionText\" : \"EqimaTEst3\","
                        + "\n \"requestingOrganisationTransactionReference\" : \""+ref+"\","
                        + "\n \"requestDate\" : \""+date1+"\","
                        + "\n \"originalTransactionReference\" : \""+ref+"\","
                        + "\n \"debitParty\" : [{\"key\": \"msisdn\", \"value\": \"0343500003\"}],"
                        + "\n \"creditParty\" : [{\"key\": \"msisdn\", \"value\": \"0343500004\"}],"
                        + "\n \"metadata\" : [{\"key\": \"partnerName\", \"value\": \"TestMVola\"}]"
                        + "\n}";
               
                byte[] out = data.getBytes();
                
		con.setDoOutput(true);
		OutputStream os = con.getOutputStream();
		os.write(out);

		// For POST only - END
int response = con.getResponseCode();
            System.out.println(con.getResponseCode() + " " + con.getResponseMessage());
             BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response1 = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response1.append(inputLine);
            }




                String inline = "";
            if (response != 202) {
                 System.out.println(con.getResponseMessage());
                throw new RuntimeException("HttpResponseCode: " + response);
               
            } else {
String res = response1.toString();
JSONParser parse = new JSONParser();

JSONObject data_obj = (JSONObject) parse.parse(response1.toString());
   System.out.println("Object JSON Status" + data_obj.get("serverCorrelationId").toString());
   
   resultat = data_obj.get("serverCorrelationId").toString();
		int responseCode = con.getResponseCode();
		System.out.println("POST Response Code :: " + responseCode);
                System.out.println("POST Response Code :: " + con.getResponseMessage());
      int i =1;
        while(i>0){
            stat.Status(resultat,amount,param,ref);
            if (stat.Status(resultat,amount,param ,ref) == true){
            resu = true;
            break;
            }
        }
   
        } 
		

        } catch (IOException ex) {
            Logger.getLogger(ApiConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ApiConnection.class.getName()).log(Level.SEVERE, null, ex);

        }return resu;
}
}