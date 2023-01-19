/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author ratsi
 */
public class Connexion {
     private Firestore db = null;
    
    public Firestore initiarFirebase()
    {
        FirebaseOptions options = null;

        try {
           
            options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(getClass().getResourceAsStream("biosy-1a05a-firebase-adminsdk-moa59-2893f4a531.json")))
                    .build();
            
            if(FirebaseApp.getApps().isEmpty()) { 
          FirebaseApp.initializeApp(options);
            }
            
        } 
        catch (IOException ex)
        {
            Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, null, ex);
        }
     
        return FirestoreClient.getFirestore();
    }
    
    public Firestore initiarFirebase1()
    {
        FirebaseOptions options = null;

        try {
           
            options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(getClass().getResourceAsStream("csvinventaire-firebase-adminsdk-yec31-062bcfe694.json")))
                    .build();
            
            if(FirebaseApp.getApps().isEmpty()) { 
          FirebaseApp.initializeApp(options);
            }
            
        } 
        catch (IOException ex)
        {
            Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, null, ex);
        }
     
        return FirestoreClient.getFirestore();
    }
}
