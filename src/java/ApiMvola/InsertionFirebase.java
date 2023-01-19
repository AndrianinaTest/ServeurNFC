/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ApiMvola;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import firebase.Connexion;
import firebase.CrudFirebase1;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ratsi
 */
public class InsertionFirebase {
     Map<String, String> docData = new HashMap<>();

    private  SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private static Firestore db = null;
    private static Firestore db1 = null;
    String Resultat;
    
    
    Connexion connex = new Connexion();
    public InsertionFirebase() {
        db = connex.initiarFirebase();
    }
     public boolean addDonnée(Map<String,Object>map , int parma) {
      boolean key = false;
      Timestamp timestamp = new Timestamp(System.currentTimeMillis());;
      String date1 = sdf2.format(timestamp);
     
      
      if (parma==1){        
            try {
                ApiFuture<WriteResult> future = db.collection("DonnéeTransaction/Telma/Buxi/"+date1+"/Type").document("Transaction").set(map);     
                System.out.println(future.get().getUpdateTime());
                
            } catch (InterruptedException ex) {
                Logger.getLogger(CrudFirebase1.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ExecutionException ex) {
                Logger.getLogger(CrudFirebase1.class.getName()).log(Level.SEVERE, null, ex);
            }
        

                 key = true;
               
}
                    
        if (parma==2){ 
            try {
             db1 = connex.initiarFirebase1();
                ApiFuture<WriteResult> future = db.collection("DonnéeTransaction/Telma/Jirama/"+date1+"/Jiro").document("Transaction").set(map);
                System.out.println(future.get().getUpdateTime());
              if (future.isDone()){
                  System.out.println("hello");
               
              }else {
              
                  System.out.println("misy diso");
              }
            } catch (InterruptedException ex) {
                Logger.getLogger(CrudFirebase1.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ExecutionException ex) {
                Logger.getLogger(CrudFirebase1.class.getName()).log(Level.SEVERE, null, ex);
            }
        
              
                    key = true;
        }
                   
        if (parma==3){ 
            try {
                db1 = connex.initiarFirebase1();
                ApiFuture<WriteResult> future = db.collection("DonnéeTransaction/Telma/Jirama/"+date1+"/Rano").document("Transaction").set(map);
                System.out.println(future.get().getUpdateTime());

            } catch (InterruptedException ex) {
                Logger.getLogger(CrudFirebase1.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ExecutionException ex) {
                Logger.getLogger(CrudFirebase1.class.getName()).log(Level.SEVERE, null, ex);
            }
        

                    key = true;
           
        }
        if (parma==4){ 
            try {
               db1 = connex.initiarFirebase1();
                 ApiFuture<WriteResult> future = db.collection("DonnéeTransaction/Telma/Jirama/"+date1+"/Borne").document("Transaction").set(map);
                System.out.println(future.get().getUpdateTime());
            } catch (InterruptedException ex) {
                Logger.getLogger(CrudFirebase1.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ExecutionException ex) {
                Logger.getLogger(CrudFirebase1.class.getName()).log(Level.SEVERE, null, ex);
            }
        

                    key = true;
                     
        }   
     
     
     
     
     return key;
     }
}
