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
public class CrudFirebase {

    Map<String, String> docData = new HashMap<>();
    List<String> Entete = new ArrayList<>();
    List<String> corp = new ArrayList<>();

    private  SimpleDateFormat sdf3 = new SimpleDateFormat("MM");
    private  SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy");
    private static Firestore db = null;
    String Resultat;

    public CrudFirebase() {
        Connexion connex = new Connexion();
        db = connex.initiarFirebase();
    }

    public boolean addDonnée(List<String> list, String Nom) throws InterruptedException, ExecutionException {
        boolean key = false;
        List<String> liste = list;
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String date = sdf3.format(timestamp);
        String date1 = sdf2.format(timestamp);
        String head = null;
        List<String> liste1 = new ArrayList<>();

        head = liste.get(0);
        String[] a = head.split(";");
        String[] corp;
        for (int i = 1; i < liste.size(); i++) {
            
            corp = liste.get(i).split(";");
            int indice = 0;
            

            System.out.println(" uguijg "+i +"ygh "+ corp[0]);
            for (String tsiky1 : corp) {
                docData.put(a[indice], tsiky1);
                indice++;
                if (indice == 6) {
                    ApiFuture<WriteResult> future = db.collection("Societe/" + date1 +"-"+ Nom + "/" + date).document(UUID.randomUUID().toString()).set(docData);
                    System.out.println(future.get().getUpdateTime());
                    if (future.isDone()) {
                        key = true;
                    } else {
                        key = false;
                        break;
                    }
                    indice = 0;
                }
            }
        }
        return key;
    }

    public boolean deleteData(String Doc) {
        // asynchronously delete a document
        boolean resultat = false;
        try {
            // ...
            ApiFuture<WriteResult> writeResult = db.collection("Societe").document(Doc).delete();
            if (writeResult.isDone()) {
                resultat = true;
                System.out.println("Update time : " + writeResult.get().getUpdateTime());
            } else {
                resultat = false;
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(CrudFirebase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(CrudFirebase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultat;
    }
    
    
    
    public boolean Activation(String Doc) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String date = sdf3.format(timestamp);
        String date1 = sdf2.format(timestamp);
        boolean key = false;
        Iterable<CollectionReference> collections = db.collection("Societe").document(date1 + "-" + Doc).listCollections();
        ArrayList<String> list = new ArrayList<String>();
        for (CollectionReference collRef : collections) {
            list.add(collRef.getId());
          //  System.out.println(list);q
        }
        int lastIdx = list.size() - 1;
        String lastElement = list.get(lastIdx);
        if (Integer.valueOf(date) > Integer.valueOf(lastElement)) {
            key = true;
        } else {
            key = false;
        }
        return key;
    }

    
    
    
}
