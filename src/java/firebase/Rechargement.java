/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package firebase;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ratsi
 */
public class Rechargement {
private static Firestore db = null;
    private static final SimpleDateFormat sdf3 = new SimpleDateFormat("MM");
    private static final SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy");
  Map<String, String> docData = new HashMap<>();
    public Rechargement() {
        Connexion connex = new Connexion();
        db = connex.initiarFirebase();
    }
    
    public void GetRechargement(String matri , String droit , String name , String Nom){
        

         
        docData.put("Matricule", matri);
        docData.put("Droit", droit);
        docData.put("Type", name);
    try {
         ApiFuture<WriteResult> future = db.collection("TestRechargement").document(UUID.randomUUID().toString()).set(docData);
        System.out.println("Resultat=====>>>>>>>>>>>" +future.get().getUpdateTime());
        if (future.isDone()){
                    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String date = sdf3.format(timestamp);
        String date1 = sdf2.format(timestamp);
            ApiFuture<QuerySnapshot> future1 = db.collection("Societe/" + date1 +"-"+ Nom + "/" + date).whereEqualTo("MATRICULE", matri).get();

            List<QueryDocumentSnapshot> documents = future1.get().getDocuments();
            for (DocumentSnapshot document : documents) {
                if (document.exists()){
                DocumentReference docRef = db.collection("Societe/" + date1 +"-"+ Nom + "/" + date).document(document.getId());
                ApiFuture<WriteResult> future3 = docRef.update(name, "0");
                WriteResult result = future3.get();
                System.out.println("Write result: " + result);
                }
            }

        }else {
            System.out.println("tsy mande");
        
        }
        
        
    } catch (InterruptedException ex) {
        Logger.getLogger(Rechargement.class.getName()).log(Level.SEVERE, null, ex);
    } catch (ExecutionException ex) {
        Logger.getLogger(Rechargement.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    
    }
    
    
}
