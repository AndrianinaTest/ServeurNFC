/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package API;

import java.util.Random;

/**
 *
 * @author ratsi
 */
public class RandomCode {
 public String code(){
String generatedString;
int leftLimit = 97; // letter 'a'
        int targetStringLength = 9;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            char c =(char) (leftLimit + random.nextInt(26));
            boolean shouldUseUppercase = random.nextBoolean();
            char character = shouldUseUppercase ? Character.toUpperCase(c) : c;
            buffer.append(character);
        }
         generatedString = buffer.toString();
        System.out.println(generatedString);
return generatedString;
}
}