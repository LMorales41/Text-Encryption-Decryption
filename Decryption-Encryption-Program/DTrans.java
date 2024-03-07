

public class DTrans extends STrans
{
    //Luis can you please put you double encryption algorithm here
    //this is just Strans but do it twice with another key
    private String key1;
    private String key2;

    public DTrans(String message, String key, String key2)
    {
        super(message, key);
        this.key1 = key;
        this.key2 = key2;

    }

    public String encrypt()
    {
        String encMsg = new String("");
        char[][] encrypted = encryptWithOrder(getMsgTable(), getOrder());
        setKey(key2);
        encrypted = encryptWithOrder(encrypted, getOrder());//encrypt twice
        for(int i = 0; i < encrypted.length; i++)
        {
            for(int j = 0; j < encrypted[i].length; j++)
                encMsg += encrypted[i][j];//adds every element in each column row by row
        }
        return encMsg;
    }


}