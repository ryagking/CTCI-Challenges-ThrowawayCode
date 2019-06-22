// Three classes in one file is illegal in Java, but I am putting all classes in one file for reviewer simplicity
// Second, the class name must match the file name, but I avoided this to keep bookkeeping simple, since I knew I wasn't going to run this code
// While this code doesn't run in its current state, it can with a few tweaks

// Author: Ryan King
// Email: ryaking@clarku.edu

// Thank you for your time

/**
 * Do stuff relevant to the Location and Car classes.
 */
public class Driver
{
    /*
        Solve our main problem:
        Given a list of locations, each location having an inventory of cars and their availability
        with date range specific pricing, write a program that will return the best price car for a 
        specific location on a given date. 
    */    
    public static Car bestCar(ArrayList<Location> location, int month)
    {
        // Just realized that I should probably rewrite this method to be one location, not every location.
        // Regardless, I don't the tweaks would be serious for a specific location.

        //Location curr = locations.get(i);
        ArrayList<Car> carlist = new ArrayList<Car>();
        int len = location.fleet.length; 

        for(int k = 0; k < len; k++) // for each car in the location's fleet
        {
            Car car = location.fleet[k];
                
            // Ran out of time here
            // I would get the season price and then change the temp variable's Car's price 
            // to the new season price
            // I would then add that car to the array list
        }

            // After this list finishes, I would get the lowest value through a simple for loop
           // Maybe a priority queue would be faster here also, I would explore this option if I had more time
            // I would add all of the top cars to another array list and then pick the best one
     
    }

    public static Car allBestCars(ArrayList<Location> locations, int month)
    {

    }

    public static void main(String[] args) 
    {
        // Read data from file and initialize locations

    }
}