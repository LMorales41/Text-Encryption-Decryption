

public class STrans extends Encrypter{
    private int[] order;
    private char[][] msgTable;

    public STrans(String message, String key)
    {
        super(message);
        order = super.determineOrder(key);
        msgTable = makeMsgTable();
    }

    public char[][] getMsgTable()
    {
        return msgTable;
    }

    public int[] getOrder()
    {
        return order;
    }

    public String encrypt()
    {
        String encMsg = new String("");
        char[][] encrypted = encryptWithOrder(msgTable, order);
        for(int i = 0; i < encrypted.length; i++)
        {
            for(int j = 0; j < encrypted[i].length; j++)
                encMsg += encrypted[i][j];//adds every element in each column row by row
        }
        return encMsg;
    }

    public void setKey(String key)
    {
        order = super.determineOrder(key);
    }
    

    private char[][] makeMsgTable()//makes string message into char table (for encryption)
    {
        String message = getPlaintext();
        int numColumns = min(order.length, message.length());//gets the number of columns
        int numRows = roundQuotientUp(message.length(), numColumns);
        char[][] table = new char[numRows][numColumns];
        for(int i = 0; i < message.length(); i++)
        {
            int row = i / numColumns;
            int column = i % numColumns;
            table[row][column] = message.charAt(i);
        }
        return table;
    }
    
    public static char[][] encryptWithOrder(char[][] message, int[] intOrder)
    {
        int row = message.length;
        int column = message[0].length;
        char[][] encrypted = new char[row][column];
        int thisRow;

        for (int i = 0; i < intOrder.length; i++)
        {
            thisRow = intOrder[i];
            //System.out.println(thisRow);
            for (int j = 0; j < message[0].length; j++) //encrypts message
            {
                encrypted[i][j] = message[thisRow][j]; //uses order
            }
        }
        return encrypted;
    }

    private static int min(int a, int b)
    {
        if (a < b)
            return a;
        return b;
    }

    private static int roundQuotientUp(int a, int b)
    {
        if(a % b > 0)
            return (a / b) + 1;
        return a / b;
    }
}