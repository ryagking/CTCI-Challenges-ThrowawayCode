/***
 * Room class
 * @author Accu-time Systems (most of it) and Ryan King (isOutbreak, dfs, isValid)
 * 
 * To compile: javac Room.java
 * To run: java Room
 * 
 * Input does not influence output for this class's main method
 */
public class Room
{
    /**
     * Notes for reader: 
     * Problem solved using DFS with Room's "visited" variable aiding our pruning mechanism
     * I created 3 test cases. All testing is done in main method. 
     */
    public final boolean isInfected;
    public boolean visited = false;

    /***
     * Constructor for Room
     * @param infected Infection status for this room
     */
    public Room( boolean infected )
    {
        isInfected = infected;
    }

    /***
    * Determine if the matrix of rooms has an outbreak. I.e. 5 or more rooms connected
    *   horizontally or vertically are infected
    *
    * @author Ryan King
    *
    * @param floor Matrix of rooms
    * 
    * @return True if we have an outbreak, false otherwise
    */
    public static boolean isOutbreak( Room[][] floor )
    {
        int rowNum = floor.length;
        int colNum = floor[0].length;

        // Iterate through floor and check for outbreak
        for( int row = 0; row < rowNum; row++ ) 
        {
            for( int col = 0; col < colNum; col++ )
            {
                if( isValid(floor, row, col) )
                {
                    if( dfs(floor, 1, row, col) )
                    {
                        return true;
                    }
                }
            }
        }

        return false;
    }
    
    /***
     * Perform textbook depth-first search algorithm
     * 
     * Algorithmic tweaks for this problem:
     *   - Path length as base case
     *  
     * @author Ryan King
     * 
     * @param floor Matrix of rooms
     * @param path Current depth of search
     * @param row Row index of floor
     * @param col Column index of floor
     * @param marked Boolean array of cells we have visited
     * @return True if final path >= 5, else false
     */
    private static boolean dfs( Room[][] floor, int path, int row, int col )
    {
        // 5 or more connected rooms are infected, return true
        if(path >= 5)
        {
            return true;
        }

        floor[row][col].visited = true; // Mark our current cell

        // "Lookup" arrays used to streamline neighbor calculation
        // Due to no diagonals, a Room can have at most 4 neighbors
        int[] rowLookup = new int[] {1, 0, -1, 0}; 
        int[] colLookup = new int[] {0, 1, 0, -1};

        for( int m = 0; m < 4; m++ ) // iterate through all neighbors
        {
            int tempRow = row + rowLookup[m];
            int tempCol = col + colLookup[m];
            
            if( isValid(floor, tempRow, tempCol) )
            {
                return dfs(floor, ++path, tempRow, tempCol);
            }
        }

        // Path not 5 or longer and no valid neighbors: fail
        return false;
    }   

    /***
     * Determine if cell is a valid neighbor/in bounds.
     * Also check if it's infected and been visited
     * 
     * @author Ryan King
     * 
     * @param floor Matrix of rooms
     * @param row Current row index
     * @param col Current column index
     * 
     * @return True if cell is valid, false otherwise
     */
    private static boolean isValid( Room[][] floor, int row, int col )
    {
        int rowNum = floor.length;
        int colNum = floor[0].length;

        if( row >= rowNum || row < 0 ) // row is not greater than or equal to array size and not less than 0
        {
            return false;
        }
        else if( col >= colNum || col < 0 ) // same conditions for columns
        {
            return false;
        }

        // Is infected and hasn't been visited
        if( floor[row][col].isInfected && !floor[row][col].visited )
        {
            return true;
        }
        
        return false;
    }

    /***
     * Testing function
     * @param args Input does not matter
     */
    public static void main(String[] args) 
    {

        Room[][] verticalTrue = new Room[][] {
            {new Room(false), new Room(false), new Room(false), new Room(false), new
            Room(false), new Room(false), new Room(false), new Room(false), new
            Room(false) },
            {new Room(false), new Room(false), new Room(false), new Room(false), new
            Room(false), new Room(false), new Room(false), new Room(false), new
            Room(false) },
            {new Room(false), new Room(false), new Room(false), new Room(false), new
            Room(false), new Room(false), new Room(false), new Room(false), new
            Room(false) },
            {new Room(false), new Room(true), new Room(false), new Room(true), new
            Room(true), new Room(false), new Room(false), new Room(false), new
            Room(false) },
            {new Room(false), new Room(true), new Room(false), new Room(false), new
            Room(false), new Room(false), new Room(false), new Room(false), new
            Room(false) },
            {new Room(false), new Room(true), new Room(false), new Room(false), new
            Room(false), new Room(false), new Room(false), new Room(false), new
            Room(false) },
            {new Room(false), new Room(true), new Room(false), new Room(false), new
            Room(false), new Room(false), new Room(false), new Room(false), new
            Room(false) },
            {new Room(false), new Room(true), new Room(false), new Room(false), new
            Room(false), new Room(false), new Room(false), new Room(false), new
            Room(false) },
            {new Room(false), new Room(false), new Room(false), new Room(false), new
            Room(false), new Room(false), new Room(false), new Room(false), new
            Room(false) },
            {new Room(false), new Room(false), new Room(false), new Room(false), new
            Room(false), new Room(false), new Room(false), new Room(false), new
            Room(false) }
        };

        Room[][] horizontalTrue = new Room[][] {
            {new Room(false), new Room(false), new Room(false), new Room(false), new
            Room(false), new Room(false), new Room(false), new Room(false), new
            Room(false) },
            {new Room(false), new Room(false), new Room(false), new Room(false), new
            Room(false), new Room(false), new Room(false), new Room(false), new
            Room(false) },
            {new Room(false), new Room(false), new Room(false), new Room(false), new
            Room(false), new Room(false), new Room(false), new Room(false), new
            Room(false) },
            {new Room(false), new Room(true), new Room(true), new Room(true), new
            Room(true), new Room(true), new Room(false), new Room(false), new
            Room(false) },
            {new Room(false), new Room(false), new Room(false), new Room(false), new
            Room(false), new Room(false), new Room(false), new Room(false), new
            Room(false) },
            {new Room(false), new Room(false), new Room(false), new Room(false), new
            Room(false), new Room(false), new Room(false), new Room(false), new
            Room(false) }, 
            {new Room(false), new Room(false), new Room(false), new Room(false), new
            Room(false), new Room(false), new Room(false), new Room(false), new
            Room(false) },
            {new Room(false), new Room(false), new Room(false), new Room(false), new
            Room(false), new Room(false), new Room(false), new Room(false), new
            Room(false) },
            {new Room(false), new Room(false), new Room(false), new Room(false), new
            Room(false), new Room(false), new Room(false), new Room(false), new
            Room(false) },
            {new Room(false), new Room(false), new Room(false), new Room(false), new
            Room(false), new Room(false), new Room(false), new Room(false), new
            Room(false) }
        };

        Room[][] noInfection = new Room[][] {
            {new Room(true), new Room(false), new Room(true), new Room(false), new
            Room(false), new Room(false), new Room(false), new Room(false), new
            Room(false) },
            {new Room(false), new Room(true), new Room(false), new Room(true), new
            Room(false), new Room(false), new Room(false), new Room(false), new
            Room(false) },
            {new Room(true), new Room(false), new Room(true), new Room(false), new
            Room(false), new Room(false), new Room(false), new Room(false), new
            Room(false) },
            {new Room(false), new Room(true), new Room(false), new Room(true), new
            Room(false), new Room(true), new Room(false), new Room(false), new
            Room(false) },
            {new Room(false), new Room(true), new Room(false), new Room(false), new
            Room(true), new Room(false), new Room(false), new Room(false), new
            Room(false) },
            {new Room(false), new Room(false), new Room(false), new Room(false), new
            Room(false), new Room(true), new Room(false), new Room(false), new
            Room(false) },
            {new Room(false), new Room(false), new Room(false), new Room(false), new
            Room(false), new Room(false), new Room(true), new Room(false), new
            Room(false) },
            {new Room(false), new Room(false), new Room(false), new Room(false), new
            Room(false), new Room(false), new Room(false), new Room(false), new
            Room(false) },
            {new Room(false), new Room(false), new Room(false), new Room(false), new
            Room(false), new Room(false), new Room(false), new Room(false), new
            Room(false) },
            {new Room(false), new Room(false), new Room(false), new Room(false), new
            Room(false), new Room(false), new Room(false), new Room(false), new
            Room(false) }
        };

        /**
         * Test case: Row of 3 infected rooms, connected to a row of 2 infected rooms
         *  Should return true
         * 
         * Looks like:
         *  - - - - -
         *  - - - - -
         *  - X X X -
         *  - - X X -
         *  - - - - -
         */
        Room[][] clumpTrue = new Room[][] {
            {new Room(false), new Room(false), new Room(false), new Room(false), new
                Room(false) },
            {new Room(false), new Room(false), new Room(false), new Room(false), new
                Room(false) },
            {new Room(false), new Room(true), new Room(true), new Room(true), new
                Room(false) },
            {new Room(false), new Room(false), new Room(true), new Room(true), new
                Room(false) },
            {new Room(false), new Room(false), new Room(false), new Room(false), new
                Room(false) }
        };

        /**
         * Base Test case: Every room is infected
         *  Should return true
         */
        Room[][] allTrue = new Room[][] {
            {new Room(true), new Room(true), new Room(true), new Room(true), new
                Room(true) },
            {new Room(true), new Room(true), new Room(true), new Room(true), new
                Room(true) },
            {new Room(true), new Room(true), new Room(true), new Room(true), new
                Room(true) },
            {new Room(true), new Room(true), new Room(true), new Room(true), new
                Room(true) },
            {new Room(true), new Room(true), new Room(true), new Room(true), new
                Room(true) }
        };

        /**
         * Test case: Path length of 4 and tests cycles
         *  Should return false
         * 
         * Looks like: 
         * - X X - -
         * - X X - -
         * - - - - -
         */
        Room[][] closeFalse = new Room[][] {
            {new Room(false), new Room(true), new Room(true), new Room(false), new
                Room(false) },
            {new Room(false), new Room(true), new Room(true), new Room(false), new
                Room(false) },
            {new Room(false), new Room(false), new Room(false), new Room(false), new
                Room(false) }
        };


        boolean vertCheck = isOutbreak(verticalTrue);
        boolean horzCheck = isOutbreak(horizontalTrue);
        boolean failCheck = isOutbreak(noInfection);
        boolean clumpCheck = isOutbreak(clumpTrue);
        boolean allCheck = isOutbreak(allTrue);
        boolean closeCheck = isOutbreak(closeFalse);

        System.out.println("Vertical check - true?: " + vertCheck);
        System.out.println("Horizontal check - true?: " + horzCheck);
        System.out.println("Fail check - false?: " + failCheck);
        System.out.println("Clump check - true?: " + clumpCheck);
        System.out.println("All room check - true?: " + allCheck);
        System.out.println("Close check - false?: " + closeCheck);
    }
}
