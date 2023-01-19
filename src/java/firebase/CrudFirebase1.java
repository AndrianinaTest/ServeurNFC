/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package firebase;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.FieldValue;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.database.Query;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import org.slf4j.impl.StaticLoggerBinder;

/**
 *
 * @author ratsi
 */
public class CrudFirebase1 {

    Map<String, String> docData = new HashMap<>();

    private  SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private static Firestore db = null;
    private static Firestore db1 = null;
    String Resultat;
    Connexion connex = new Connexion();
    public CrudFirebase1() {

        db = connex.initiarFirebase();
    
    }

    public boolean addDonnée(Map<String , String>map ,Map<String , String> map2 , int parma) {
        boolean key = false;
 Timestamp timestamp = new Timestamp(System.currentTimeMillis());;
        String date1 = sdf2.format(timestamp);
        


                  
if (parma==1){        
            try {
                  ApiFuture<WriteResult> future = db.collection("DonnéeTransaction/Airtel/Buxi/"+date1+"/Type").document("Transaction").set(map);
                    ApiFuture<WriteResult> future1 = db.collection("DonnéeTransaction/Airtel/Buxi/"+date1+"/Type").document("Status").set(map2);
                System.out.println(future.get().getUpdateTime());
                System.out.println(future1.get().getUpdateTime());
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
                ApiFuture<WriteResult> future = db.collection("DonnéeTransaction/Airtel/Jirama/"+date1+"/Jiro").document("Transaction").set(map);
                ApiFuture<WriteResult> future2 = db.collection("DonnéeTransaction/Airtel/Jirama/"+date1+"/Jiro").document("Status").set(map2);
                System.out.println(future2.get().getUpdateTime());
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
                ApiFuture<WriteResult> future = db.collection("DonnéeTransaction/Airtel/Jirama/"+date1+"/Rano").document("Transaction").set(map);
                    ApiFuture<WriteResult> future1 = db.collection("DonnéeTransaction/Airtel/Jirama/"+date1+"/Rano").document("Status").set(map2);
                    System.out.println(future.get().getUpdateTime());
                    
                System.out.println(future1.get().getUpdateTime());
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
                 ApiFuture<WriteResult> future = db.collection("DonnéeTransaction/Airtel/Jirama/"+date1+"/Borne").document("Transaction").set(map);
                    ApiFuture<WriteResult> future1 = db.collection("DonnéeTransaction/Airtel/Jirama/"+date1+"/Borne").document("Status").set(map2);
                System.out.println(future.get().getUpdateTime());
                System.out.println(future1.get().getUpdateTime());
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
