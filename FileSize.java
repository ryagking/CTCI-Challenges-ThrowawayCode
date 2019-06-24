import java.util.*;

/**
 * Get the number of bytes for inputted file size
 * @author Ryan King
 * 
 * To compile: javac FileSize.java
 * Example run: java FileSize "1.5 MiB"
 */
public class FileSize
{
    /**
     * Parses user input and returns number of bytes of particular file size
     * @param args Input is wrapped in quotation marks containing <number><units>. A space is optional between number and unit
     */
    public static void main(String[] args)
    {
        // Side note: Never used exceptions in undergrad. I've heard of try, catch, and throw, but didn't want to introduce more bugs.
        // Print statement and exiting works for right now, but I completely understand that in more sophisticated instances, I will need to use/learn exceptions

        String num = "";
        String fileSize = "B"; // Default

        if( args.length != 1 )
        {
            System.out.println("Illegal input");
            return;
        }

        String s = args[0]; // Store string for clarity
        int len = s.length(); // Decrease lookups

        // Parse our input         
        int index = 0;
        int dotCounter = 0;
            
        while( index < len )
        {
            if( dotCounter > 1) // floating point numbers don't have two decimal delimiters
            {
                exception("Illegal input");
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

        // Get our number
        num = s.substring(0, index);

        // Take substring if needed and trim white space
        if(index < len)
        {
            fileSize = s.substring(index);
        }

        fileSize = fileSize.trim();

        if( num.length() < 1 || fileSize.length() > 3 || fileSize.length() < 1) 
        {
            // Number must exist
            // File size must exist
            // No file size word length is greater than 3

            exception("Illegal input");
        }

        double ourNum = Double.parseDouble(num);
        long finNum = getFileSize(fileSize, ourNum);

        System.out.println(finNum);
    }

    /**
     * Private helper method that computes the file size using a HashMap
     * Example input: getFileSize("GB", 5) - which is 5 Gigabytes
     * 
     * @param fSize File size
     * @param base Coefficient inputted by user
     * @return File size in bytes
     */
    private static long getFileSize(String fSize, double base)
    {
        // Get dict
        HashMap<String, Integer> ourMap = makeFileDict();
        long finNum = 0;

        if(!ourMap.containsKey(fSize)) // Avoid failed lookup
        {
            exception("Illegal file size");
        }

        int lookup = ourMap.get(fSize);
        long one = 1; // wrap 1 in long to avoid shift overflow (long is 64 bits)

        if( lookup % 10 == 0 ) // If the lookup is not divisible by 10, its binary
        {
            finNum = (long) (base * (one << lookup));
        }
        else // other case: base-10 (decimal)
        {
            finNum = (long) (base * Math.pow(10, lookup));
        }

        return finNum;
    }

    /**
     * Helper function that contains a map allowing us to compute file sizes efficiently and cleanly
     * @return Dictionary containing file size suffixes and their values
     */
    private static HashMap<String, Integer> makeFileDict()
    {
        // Keys are suffixes
        // Values are power of 2 or 10
        HashMap<String, Integer> ourMap = new HashMap<String, Integer>();

        // B = bytes. No work necessary
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
    
    /**
     * Exception handler. I'm sure Java's is better for this, but went with comfort under time pressure.
     * @param message Error message
     */
    private static void exception(String message)
    {
        System.out.println("Error!");
        System.out.println("Issue: " + message);
        System.exit(1); // Unsuccessful termination
    }
}