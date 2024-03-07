

public class RegDecrypt extends Decrypter
{
    private char[][] encTable;
    private int[] order;
    private String keyword1;
    private String keyword2;
    private boolean singleTrans;

    public RegDecrypt(char[][] message)
    {
        super("");
        this.encTable = message;
    }
    public RegDecrypt(char[][] message, String keyword)
    {
        super("");
        this.encTable = message;
        this.keyword1 = keyword;
        singleTrans = true;
    }

    public RegDecrypt(char[][] message, String keyword1, String keyword2)
    {
        super("");
        this.encTable = message;
        this.keyword1 = keyword1;
        this.keyword2 = keyword2;
        singleTrans = false;
    }

    public RegDecrypt(char[][] message, int[] key)
    {
        super("");
        this.encTable = message;
        this.order = key;
        singleTrans = true;
    }

    public String decrypt()
    {
        if(singleTrans)
            return decryptCipher(encTable, keyword1);
        return decryptCipher(encTable, keyword1, keyword2);
    }
    
    public String decryptCipher(char[][] encryptedMessage, int[] key)
    {
        int row = encryptedMessage.length;
        int column = encryptedMessage[0].length;
        //System.out.println(row + " , " + column);
        char[][] message = new char[row][column];
        int thisRow;
        char [][] finalMessage = new char[column][row];
        //rewrites back into order (breaks single encryption)

        for (int i = 0; i < key.length; i++)
        {
            thisRow = key[i];
            //System.out.println(thisRow);
            for (int j = 0; j < encryptedMessage[0].length; j++)
            {
                message[thisRow][j] = encryptedMessage[i][j]; 

            }
        }

        //rewrites into full order (goes from column order to row order)
        for (int j = 0; j < message[0].length; j++)
        {
            for (int i = 0; i < message.length; i++)
            {
                finalMessage[j][i] = message[i][j];
                //System.out.print(message[i][j]);
            }
            //System.out.println();
        }

        //turns into string (can keep as printed 2d char array if wanted as well)
        String messageString = ""; //can reutrn this and turn into a string function
        for (int i = 0; i < finalMessage.length; i++)
        {
            for (int j = 0; j < finalMessage[0].length; j++)
            {
                messageString += finalMessage[i][j];
                //System.out.print(finalMessage[i][j]);
            }
            //System.out.println();
        }
        return messageString;

    }

    public String decryptCipher(char[][] encryptedMessage, String keyword)
    {
        int[] orderArray = determineOrder(keyword);
        return decryptCipher(encryptedMessage, orderArray);
    }

    public String decryptCipher(char[][] encryptedMessage, String keyword, String keyword2)
    {
        int[] orderArray = determineOrder(keyword);
        int row = encryptedMessage.length;
        int column = encryptedMessage[0].length;
        char[][] message = new char[row][column]; //both same sizes
        char[][] message2 = new char[row][column]; //both same sizes
        int thisRow;
        char [][] finalMessage = new char[column][row]; //transpose back


        //rewrites back into order (needs to break second keyword first in double transpos)
        int []orderArray2 = determineOrder(keyword2);
        for (int i = 0; i < orderArray2.length; i++)
        {
            thisRow = orderArray2[i];
            //System.out.println(thisRow);
            for (int j = 0; j < encryptedMessage[0].length; j++) 
            {
                message2[thisRow][j] = encryptedMessage[i][j]; 

            }
        }

        //rewrite into original order (breaks first keyword second in double transpos)
        for (int i = 0; i < orderArray.length; i++)
        {
            thisRow = orderArray[i];
            //System.out.println(thisRow);
            for (int j = 0; j < encryptedMessage[0].length; j++) 
            {
                message[thisRow][j] = message2[i][j]; 

            }
        }


        //rewrites into full order (goes from column order to row order)
        for (int j = 0; j < message[0].length; j++) //encrypts message
        {
            for (int i = 0; i < message.length; i++)
            {
                finalMessage[j][i] = message[i][j];
                //System.out.print(message[i][j]);
            }
            //System.out.println();
        }

        //turns into string (can keep as printed 2d char array if wanted as well)
        String messageString = ""; //can reutrn this and turn into a string function
        for (int i = 0; i < finalMessage.length; i++)
        {
            for (int j = 0; j < finalMessage[0].length; j++)
            {
                messageString += finalMessage[i][j];
                //System.out.print(finalMessage[i][j]);
            }
            //System.out.println();
        }
        // System.out.println();
        // System.out.println(messageString);
        // System.out.println();
        return messageString;
    }

    public String getKeyword1()
    {
        return keyword1;
    }

    public String getKeyword2()
    {
        return keyword2;
    }

    public void setKeyword1(String keyword)
    {
        this.keyword1 = keyword;
    }

    public void setKeyword2(String keyword)
    {
        this.keyword2 = keyword;
    }


}