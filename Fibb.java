/**
 * A twist on the typical Fibonacci sequence function
 * 
 * @author Paul Brown Bear and Ryan King
 * 
 * To compile: javac Fibb.java
 * Example run: java Fibb
 * 
 * Note: This is just my interpretation of what Paul was trying to do. I'm open to going over alternate interpretations
 * and consequently how I would try to program those. 
 */
public class Fibb
{
    /**
     * Paul's Program - An attempt to get in the mind of another programmer
     * 
     * @author Paul Brown Bear and Ryan King
     * @param args No arguments needed on the command line
     */
    public static void main(String[] args) 
    {
        
        int fibN = 10; // How many pairs of numbers we want to compute

        /* 
         * Paul seems to want a sum of all even numbers between 0 and his initial ArrayList size
         * He shows us this in his for loop
         * We can do this more efficiently using math 
         */
        int numOfPairs = fibN / 2;
        long sum = numOfPairs * (numOfPairs + 1);

        int count = fibN; // temp variable

        /* 
         * Paul also appears to sum pairs of numbers, starting from the back of his List
         * This effectively doesn't do anything, since an odd number plus an even number will always be odd
         * If this code was being used in it's current state, this comment and while loop wouldn't exist
         * If we tweaked the incrementation or had it iterate through an array/list, it would be more interesting
         */
        while( count > 0 ) 
        {
            long p = paulFibb(count); 

            if( p > 4000000 )
                break;
            
            if( p % 2 == 0 ) // Sum all even fibonacci numbers
                sum += p;
                        
            count--;
        }

        System.out.println("Paul's sum is: " + sum);
    }

    /**
     * Paul's "Fibonacci Sequence"
     * Technically unnecessary right now, but if Paul's vision was a bit more clear, we could tweak this helper function
     * to do more useful things.
     * 
     * @author Paul Brown Bear and Ryan King
     * @param num Some integer
     * @return Nth number in fibonacci sequence
     */
    public static long paulFibb(int num)
    {
        if( num <= 0 )
            return 0;
        
        return num + (num - 1);
    }

}

/***
 * What this function does: Sums the last and penultimate elements of the ArrayList, then adds them to the end of the ArrayList. 
 * If the sum is above 4 million, it won't put them in the ArrayList. 
 * It returns the sum of all even numbers in the ArrayList. 
 * In my code, I am assuming Paul isn't just interested in lists of size 2.
 */