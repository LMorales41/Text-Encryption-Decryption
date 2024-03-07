//package edu.pasadena.cs.cs03b; // removed this line from all files when moving from school repository
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.lang.Math;


public class TermProject 
{
    public static void main(String[] args) 
    {   


        System.out.println("BRUH MOMENT");
        

    }


    public static boolean test(String testString)
    {
        String key = keyGenerator(5);
        String key2 = keyGenerator(5);
        STrans enc1 = new STrans(testString, key);
        STrans enc2 = new DTrans(testString, key, key2);
        
        Decrypter dec1 = new RegDecrypt(enc1.getMsgTable(), key);
        Decrypter dec2 = new RegDecrypt(enc2.getMsgTable(), key, key2);

        if (dec1.getEncMsg().equalsIgnoreCase(enc1.getPlaintext()))
        {
            return true;
        }
        

        return false;
    }

    public static String keyGenerator (int length)
    {
        String keyword = "";
        Random random = new Random();
        boolean [] repeat = new boolean[25]; //will show when each randomint is used
        
        for (int i = 0; i < length; i++)
        {
            
            int randInt = random.nextInt(25);
            if (repeat[randInt])
            {
                while (repeat[randInt])
                {
                    randInt = random.nextInt(25); //will go until finding a new one
                }
            }
            char createString = (char) (randInt +'A');
            repeat[randInt] = true;
            keyword +=  createString;
        }
        return keyword;
    }


}