

public abstract class Encrypter {
    private String message;

    public Encrypter(String message)
    {
        this.message = message;
    }

    public abstract String encrypt();

    //getter
    public String getPlaintext()
    {
        return message;
    }
    //public helper functions
    public int[] determineOrder(String keyword)
    {
        int[] orderArray = initIntArray(keyword.length());
        char[] letters = initCharArray(keyword);
        for (int i = 0; i < keyword.length(); i++)
        {
            for (int j = 0; j < keyword.length(); j++)
            {
                if (j == i)
                {
                    
                }
                else if (letters[i] > letters[j])
                {
                    orderArray[i]++;
                }
                else 
                {

                }
            }
        }

        for (int i = 0; i < keyword.length(); i++)
        {
            orderArray[i]--;
        }
        return orderArray;
    }

    //helper functions
    private static int[] initIntArray (int length)
    {
        //starts an array with all 1s for order purposes
        int[] array = new int [length];
        for (int i = 0 ; i < length; i++)
        {
            array[i] = 1;
        }

        return array;
    }

    
    private static char[] initCharArray(String keyword)
    {       
        //counts array without whitespace first in a loop
        //then fills in whenever characters are not whitespaces
        //goes toUpper
        int index = 0;

        for (int i = 0; i < keyword.length(); i++)
        {
            char letter = keyword.charAt(i);
            if (!Character.isWhitespace(letter))
            {
                index++;
            }
        }
        //System.out.println("Size after whitespace removal count: " + index);
        char [] array = new char [index];
        int j = 0;
        for (int i = 0; i < keyword.length(); i++)
        {
            
            char letter = keyword.charAt(i);
            if (!Character.isWhitespace(letter))
            {
                letter = Character.toUpperCase(letter);
                array[j] = letter;
                j++;
            }
            
        }
        return array;
    }
    

}