/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import API.MArchandPay;
import ApiMvola.ApiConnection;
import Model.Personne;
import firebase.CrudFirebase1;
import firebase.GetDataFirebase;
import firebase.Rechargement;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import luhn.CreateLuhn;
import luhn.LuhnAlgorithmMain;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.LoggerFactory;

/**
 *
 * @author ratsi
 */
@RestController
public class NewClass {

    /**
     *
     * @param Nom
     * @param Nom
     * @return
     */
    CreateLuhn create = new CreateLuhn();
    
    @RequestMapping(value = "/Data", method = RequestMethod.GET)
    public List<Personne> GetDonn(@RequestParam("Nom") String Nom , @RequestParam("Matricule" )String Matricule) {
        GetDataFirebase crud = new GetDataFirebase();
        List<Personne> list = crud.GetData(Matricule,Nom);
        System.out.println("hello" + list);
         return list;
    }
    @RequestMapping(value = "/Rechargement", method = RequestMethod.POST)
    public boolean GetRechargement(@RequestParam("Matricule") String matricule ,@RequestParam("Droit") String Droit,@RequestParam("Name") String Name , @RequestParam("Nom") String Nom ,@RequestParam("Param") int param , @RequestParam("Operateur") String operateur , @RequestParam("UID") String uid , @RequestParam("Refference") String Refference) throws ParseException, InterruptedException, ExecutionException {
        boolean key = false;
        Rechargement crud = new Rechargement();
        ApiConnection api = new ApiConnection();
        
        if (operateur.equalsIgnoreCase("Airtel")) {
                    MArchandPay pay = new MArchandPay();
                    if (pay.PAy(param, Droit, Refference) == true) {
                        crud.GetRechargement(matricule, Droit, Name, Nom);
                        key = true;
                    } else {

                        System.out.println("Errreur");
                    }

        }

        if (operateur.equalsIgnoreCase("Telma")) {
                     if (api.sendPOST(Droit, param,Refference) == true) {
                        crud.GetRechargement(matricule, Droit, Name, Nom);
                        key = true;
                    } else {

                        System.out.println("Errreur");
                    }
                    
                }

        return key;  
    }     
        
}
