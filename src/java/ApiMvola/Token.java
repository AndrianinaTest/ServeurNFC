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
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.codec.binary.Base64;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author ratsi
 */
public class Token {
    String data = "grant_type=client_credentials";
    String scope = "scope=EXT_INT_MVOLA_SCOPE";
    
    public String getToken() {
        String token = null;
    URL url;
        try {
            url = new URL("https://devapi.mvola.mg/token?"+data+"&"+scope);
            HttpURLConnection http = (HttpURLConnection)url.openConnection();
            String auth = "Hf2jD5OZDHH2J628kyCxRtC5uJoa" + ":" + "ofxE9kJw3MXn5JwhKmQtmH3xyy0a";
byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(StandardCharsets.UTF_8));
String authHeaderValue = "Basic " + new String(encodedAuth);
http.setRequestMethod("POST");
http.setDoOutput(true);
http.setRequestProperty("Authorization",authHeaderValue);
http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
http.setRequestProperty("Cache-Control", "no-cache");
 BufferedReader in = new BufferedReader(
                new InputStreamReader(http.getInputStream()));
            String inputLine;
            StringBuffer response1 = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response1.append(inputLine);
            }

String res = response1.toString();
JSONParser parse = new JSONParser();

JSONObject data_obj = (JSONObject) parse.parse(response1.toString());
        token = data_obj.get("access_token").toString();
System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
http.disconnect();
        } catch (MalformedURLException ex) {
            Logger.getLogger(Token.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Token.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Token.class.getName()).log(Level.SEVERE, null, ex);
        }
        return token;

    }
}
