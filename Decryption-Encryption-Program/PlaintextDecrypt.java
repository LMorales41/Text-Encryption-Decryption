

public class PlaintextDecrypt extends Decrypter {
    private String plainText;
    private char[][] encTable;
    private char[][] testTable;//stores message in different table
    private RegDecrypt regDecrypt;
    private int key[];
    public PlaintextDecrypt(String encrypted, String plainText)
    {
        super(encrypted);
        this.plainText = plainText;
        
    }

    public String decrypt()
    {
        this.key = getKey();
        //need to use this to get the message
        regDecrypt = new RegDecrypt(encTable, key);
        return regDecrypt.decryptCipher(encTable, key);
    }

    public int[] getKey()
    {
        int[] key = {-1};//in case original key length is 0
        int keyLength = getEncMsg().length();
        while(keyLength > 0)
        {
            if(testKeyLength(key, keyLength))//if we get a valid key of the key length, this is the key
                return key;
            keyLength = gcf(getEncMsg().length(), keyLength);
        }
        return key;
    }

    private boolean testKeyLength(int[] key, int keyLength)
    {
        
        makeTables(keyLength);
        if(isTranspose(keyLength))
        {
            key = calcKey();
            return true;
        }
        return false;
    }

    private int gcf(int num, int oldGcf)
    {
        for(int i = oldGcf - 1; i > 0; i--)
        {
            if(num % i == 0)
                return i;   
        }
        return -1;//should never reach this case
    }

    private void makeTables(int keyLength)
    {
        String encMsg = getEncMsg();
        int numColumns = keyLength;
        int numRows = encMsg.length() / keyLength;
        testTable = new char[numRows][numColumns];
        encTable = new char[numColumns][numRows];
        //filling testTable
        for(int i = 0; i < numRows * numColumns; i++)
        {
            int row = i / keyLength;
            int column = i % keyLength;
            if(i < plainText.length())
                testTable[row][column] = plainText.charAt(i);
            else//filling out rest of unfilled array
                testTable[row][column] = '*';
        }
        //filling ecryption table
        for(int i = 0; i < encMsg.length(); i++)
        {
            int row = i / numRows;//since encrypted table transposes row to column
            int column = i % numRows;
            testTable[row][column] = encMsg.charAt(i);
        }
    }

    private boolean isTranspose(int keyLength)
    {
        for(int i = 0; i < encTable.length; i++)//compare rows enc to columns of plain
        {
            for(int j = 0; j < testTable[i].length; j++)
            if(!equals(i, j))
                return false;
        }
        return true;
    }

    private boolean equals(int rowEnc, int colPlain)
    {
        for(int i = 0; i < encTable[rowEnc].length; i++)
        {
            if(encTable[rowEnc][i] != testTable[i][rowEnc])
                return false;
        }
        return true;
    }

    private int[] calcKey()
    {
        int[] key = new int[encTable.length]; 
        char c;
        int walker = 0;
        for(int i = 0; i < encTable.length; i++)
        {
            c = encTable[i][0];//find which column in testTable the first of row of encTable is in
            key[walker] = searchChar(c);
            walker++;
        }
        return key;
    }

    private int searchChar(char c)
    {
        for(int i = 0; i < testTable.length; i++)
        {
            if(testTable[0][i] == c)
                return i;
        }
        return -1;//should never get called
    }
}