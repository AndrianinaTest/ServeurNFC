/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package firebase;

import Model.Personne;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ratsi
 */
public class GetDataFirebase {
     private static Firestore db = null;
   private SimpleDateFormat sdf = new SimpleDateFormat("MM");
    private SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy");
   
    public GetDataFirebase() {
        Connexion connex = new Connexion(); 
        db = connex.initiarFirebase(); 
    }
     public  List<Personne> GetData(String Matricule , String Nom){
              List<Personne> list = new ArrayList<>();
  
               Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String date = sdf.format(timestamp);
        String date1 = sdf2.format(timestamp);
    
    
         ApiFuture<QuerySnapshot> future = db.collection("Societe/"+date1+"-"+Nom+"/"+date).whereEqualTo("MATRICULE", Matricule).get();
         List<QueryDocumentSnapshot> documents;
         try {
             documents = future.get().getDocuments();
             for (DocumentSnapshot document : documents) {
          list.add(new Personne(document.getData().get("MATRICULE").toString()
                  , document.getData().get("Nom").toString()
                  , document.getData().get("Prenom").toString()
                  , document.getData().get("Montant").toString()
                  ,document.getData().get("Option1").toString(),
                  document.getData().get("Option2").toString()));
         }
             System.out.println(list);
             for (int i = 0; i < list.size(); i++) {
                 System.out.println(list.get(i));
             }  
             
         } catch (InterruptedException ex) {
             Logger.getLogger(GetDataFirebase.class.getName()).log(Level.SEVERE, null, ex);
         } catch (ExecutionException ex) {
             Logger.getLogger(GetDataFirebase.class.getName()).log(Level.SEVERE, null, ex);
         }
         return list;
     }
     
     
}
