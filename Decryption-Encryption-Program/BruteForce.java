

import java.util.Random;

public class BruteForce extends Decrypter 
{
    private int len;
    private char[][] encryptedMessage;

    public BruteForce(String message, int len, char[][] encryptedMessage)
    {
        super(message);
        this.len = len;
        this.encryptedMessage = encryptedMessage;
    }
    public String decrypt()
    {
        String message = getEncMsg();
        String keyword, keyword2, checkMessage;
        long count = 0;
        RegDecrypt decrypter = new RegDecrypt(encryptedMessage);
        do 
        {
            keyword = keyGenerator(len);
            keyword2 = keyGenerator(len);
            decrypter.setKeyword1(keyword);
            decrypter.setKeyword2(keyword2);
            checkMessage = decrypter.decryptCipher(encryptedMessage, keyword, keyword2);
            count++;
        }
        while (!message.equalsIgnoreCase(checkMessage));
        System.out.println("The phrase has been decrypted after " + count + " tries!");
        return checkMessage;
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