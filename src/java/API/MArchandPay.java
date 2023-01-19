/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package API;



import com.google.rpc.Status;
import firebase.CrudFirebase1;
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
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
/**
 *
 * @author ratsi
 */
public class MArchandPay {
Authorisation aut = new Authorisation();
Refund fund = new Refund();
    public boolean PAy(int param , String montant,String refference) throws ParseException, InterruptedException, ExecutionException {
        URL obj;
Map< String,String> transactio =  new HashMap<>();
Map< String,String> Status =  new HashMap<>();
CrudFirebase1 crud = new CrudFirebase1();
boolean resultat = false;
String acces = aut.auth();


String ref = refference;
        System.out.println("REf" + ref);
        try {
            obj = new URL("https://openapiuat.airtel.africa/merchant/v1/payments/");
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Authorization", "Bearer " +acces);
            
            con.addRequestProperty("X-Country", "MG");
            con.addRequestProperty("X-Currency", "MGA");
            String data = "{\n \"reference\" :\"Eqima2022\","
                    + "\n\"subscriber\" : \n{\"country\" : \"MG\",\n\"currency\":\"MGA\",\n\"msisdn\" : \"330368795\"\n},"
                    + "\n\"transaction\" :\n{\"amount\" : \""+montant+"\",\n\"country\" : \"MG\",\n\"currency\" : \"MGA\",\n\"id\":\""+ref+"\"}\n}";

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
           // System.out.println(response1.toString());



                String inline = "";
            if (response != 200) {
                throw new RuntimeException("HttpResponseCode: " + response);
            } else {
String res = response1.toString();
JSONParser parse = new JSONParser();

JSONObject data_obj = (JSONObject) parse.parse(response1.toString());
  // System.out.println("Object JSON Status" + data_obj.get("status"));
JSONObject stat = (JSONObject) data_obj.get("status");





JSONObject data_obj3 = (JSONObject) parse.parse( data_obj.get("data").toString());
//System.out.println("Object JSON Transaction" + data_obj3.get("transaction"));
               
JSONObject transaction = (JSONObject) parse.parse( data_obj3.get("transaction").toString());

Status.put("response-code", stat.get("response_code").toString());
Status.put("code", stat.get("code").toString());
Status.put("success", stat.get("success").toString());
Status.put("result_code", stat.get("result_code").toString());
Status.put("message", stat.get("message").toString());

transactio.put("id", transaction.get("id").toString());
transactio.put("status", transaction.get("status").toString());
transactio.put("Montant",montant);

                //System.out.println("stat  "+ transaction.get("status").toString() +"sddsd "+ stat.get("code").toString());
if( transaction.get("status").toString().equalsIgnoreCase("Success.") || stat.get("code").toString().equalsIgnoreCase("200") ){
    
    int i = 1;
    while(i>0){
    fund.Enquiry(ref, acces);
    
    if ( fund.Enquiry(ref, acces) ==true){
        System.out.println("Rechargement succes");
    
    break;
    }
    }
}else {
    System.out.println(con.getResponseCode());

}

        crud.addDonnée(transactio, Status , param);
        resultat = true;


            }
           
            in.close();
          //  System.out.println(response1.toString());
        } catch (MalformedURLException ex) {
            Logger.getLogger(Authorisation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Authorisation.class.getName()).log(Level.SEVERE, null, ex);
        }
return resultat;
    }

}
