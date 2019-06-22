import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Car class
 * Represents a car in a rental company's fleet
 */
public class Car
{
    String name;
    double[] demand = new double[12]; // each month demand changes for a car
    double price; // base price to rent this car
    boolean inStock; // is the car in stock? 

    public Car(String name, double[] demand, double price, boolean stock)
    {
        if(demand.length != 12)
        {
            System.out.println("Invalid demand weights");
            return; 
        }
        
        this.name = name;
        this.demand = demand;
        this.price = price;
        inStock = stock; 
    }

    /**
     * Get base price
     * @return
     */
    public double getBasePrice()
    {
        return this.price;
    }

    /**
     * Get price on a given date - only month matters in this example
     * 
     */
    public double getSeasonPrice(int month)
    {
        if(month < 0 || month > 13)
        {
            //Throw exception
            System.out.println("Invalid date");
            System.exit(0);
        }

        month -= 1; // January is technically at index 0

        return price * demand[month]; 
    }

    /**
     * Get price on a given date - only month matters in this example
     * 
     */
    public double getSeasonPrice(String month)
    {
        HashMap<String, Integer> monthDict = makeDict();

        // Do a lookup - I would put some lookup bulletproofing in production code
        int numMonth = monthDict.get(month);

        numMonth -= 1;

        return price * demand[numMonth];
    }

    public double newPrice(double price)
    {
        this.price = price;
    }
    
    private HashMap<String, Integer> makeDict()
    {
        HashMap<String, Integer> temp = new HashMap<String, Integer>(12);

        temp.put("January", 1);
        temp.put("Februrary", 2);
        temp.put("March", 3);
        temp.put("April", 4);
        temp.put("May", 5);
        temp.put("June", 6);
        temp.put("July", 7);
        temp.put("August", 8);
        temp.put("September", 9);
        temp.put("October", 10);
        temp.put("November", 11);
        temp.put("December", 12);
    }
}