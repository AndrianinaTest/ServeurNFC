/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import firebase.CrudFirebase;
import firebase.GetDataFirebase;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.impl.StaticLoggerBinder;
/**
 *
 * @author ratsi
 */

@RestController
public class COntroller {
    List<String> list;
    
    
     @RequestMapping(value = "/Texte", method = RequestMethod.GET)
    public boolean Value(@RequestParam("file") List<String> list , @RequestParam("Nom" )String Nom) {
         for (int i = 0; i < list.size(); i++) {
               this.list=list;
         }
         System.out.println(this.list);
         
         CrudFirebase crud = new CrudFirebase();
         return crud.Activation(Nom);
    }
    
    
     @RequestMapping(value = "/file", method = RequestMethod.GET)
    public boolean getTxte(@RequestParam("Nom" )String Nom) {
        boolean resultat = false;
       try {
           CrudFirebase crud = new CrudFirebase();
           
           if (crud.addDonnée(list,Nom) == true){
           
           resultat = true;
           
           }else {
           
           resultat = false;
           }
           
       } catch (Exception ex) {
           Logger.getLogger(COntroller.class.getName()).log(Level.SEVERE, null, ex);
       }
        return resultat; 
    }
    @RequestMapping(value = "/Delete", method = RequestMethod.POST)
    public boolean DeleteData(@RequestParam("Nom") String Nom) {
         CrudFirebase crud = new CrudFirebase();
         return crud.deleteData(Nom);
    }

}
