/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package luhn;

import java.util.Random;

/**
 *
 * @author ratsi
 */
public class CreateLuhn {

    LuhnAlgorithmMain lunh = new LuhnAlgorithmMain();
    String value = null;

    public String create(String montant) {

       

        int n1 = 0 + new Random().nextInt(999);
        String ref = String.valueOf(n1) + String.valueOf(montant);
        int i = 1;
        while (i > 0) {
            lunh.isValidCreditCardNumber(ref);
            if (lunh.isValidCreditCardNumber(ref)) {
                value = ref;
                break;
            }

        }
        return value;
    }
}
