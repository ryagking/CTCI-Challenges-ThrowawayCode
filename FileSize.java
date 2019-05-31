import java.util.*;
/***
 * 
 * @author Ryan King
 * 
 */
public class FileSize
{
    /**
     * Parses user input and returns number of bytes of particular file size
     * @param Input is wrapped in quotation marks containing <number><units>. A space is optional between number and unit
     */
    public static void main(String[] args)
    {
        String num = "";
        String fileSize = "B"; // Default
        System.out.println(args[0]);

        if( args.length == 1 )
        {
            String s = args[0]; // Store for clarity
            int len = s.length(); // Decrease lookups

            // Parse our input
            
            int index = 0;
            int dotCounter = 0;
            
            while( index < len )
            {
                if( dotCounter > 1 ) // floating point numbers don't have two decimal delimiters
                {
                    // Throw exception
                }
                char c = s.charAt(index);

                if(Character.isDigit(c))
                {
                    index++;
                }
                else if(c == '.')
                {
                    dotCounter++;
                    index++;
                }
                else // character isn't related to a number, break
                {
                    break;
                }
            }

            if( index == 0 || len - index > 3 ) // illegal input
            {
                // Throw exception
                System.out.println("Exception thrown");
                return;
            }

            // Take substrings for our values
            num = s.substring(0, index);

            if(index < len)
            {
                fileSize = s.substring(index);
            }

        }
        else // Parsing done for us
        {
            num = args[0];
            fileSize = args[1]; 
        }

        
        System.out.println(lookup);
        System.out.println(fileSize);
    }

    private static int getFileSize(String fSize, int base)
    {
        // Get dict
        HashMap<String, Integer> ourMap = makeFileDict();
        
        double ourNum = Double.parseDouble(num);
        long finNum = 0;

        //If fileSize == 2 - its SI
        int lookup = ourMap.get(fileSize);
        long one = 1; // wrap 1 in long to avoid shift overflow

        if( lookup % 10 == 0 ) // If the lookup is not divisible by 10, its binary
        {
            finNum = (long) (ourNum * (one << lookup));
            System.out.println(one << lookup);
            System.out.println(finNum);
        }
        else // other case: base-10
        {
            finNum = (long) (ourNum * Math.pow(10, lookup));
            System.out.println(finNum);
        }


    }
    private static HashMap<String, Integer> makeFileDict()
    {
        // Keys are suffixes
        // Values are power of 2 or 10
        HashMap<String, Integer> ourMap = new HashMap<String, Integer>();

        // Put everything in
        ourMap.put("B", 0);
        
        // Start with SI suffixes
        ourMap.put("KB", 3);
        ourMap.put("MB", 6);
        ourMap.put("GB", 9);
        ourMap.put("TB", 12);
        ourMap.put("PB", 15);

        // Binary suffixes
        ourMap.put("KiB", 10);
        ourMap.put("MiB", 20);
        ourMap.put("GiB", 30);
        ourMap.put("TiB", 40);
        ourMap.put("PiB", 50);

        return ourMap;
    }
}