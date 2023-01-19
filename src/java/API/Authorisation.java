/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package API;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
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
public class Authorisation {
JSONObject data_obj;


    public String auth() throws ParseException {
        URL obj;
        try {
            obj = new URL("https://openapiuat.airtel.africa/auth/oauth2/token");
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setRequestProperty("Content-Type", "application/json");
            String data = "{\n \"client_id\" : \"21eb207f-534d-4e80-939b-43d2515a9f7f\",\n\"client_secret\" : \"20f4c576-7d11-4474-b30f-c63b371bfb85\",\n\"grant_type\" : \"client_credentials\"\n}";
            byte[] out = data.getBytes();
            OutputStream stream = con.getOutputStream();
            stream.write(out);

           int response = con.getResponseCode();
            System.out.println(con.getResponseCode() + " " + con.getResponseMessage());
            BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response1 = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response1.append(inputLine);
            }
            in.close();
           // System.out.println(response1.toString());
JSONParser parse = new JSONParser();
 data_obj = (JSONObject) parse.parse(response1.toString());
        } catch (MalformedURLException ex) {
            Logger.getLogger(Authorisation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Authorisation.class.getName()).log(Level.SEVERE, null, ex);
        }
return data_obj.get("access_token").toString();
    }
}
