/**
 * Represents a kiosk/branch of a rental company
 */
public class Location
{
    String companyName;
    String airportName;
    String cityName;
    Car[] fleet;
    double weight; // Car prices also depend on airport location

    public Location(String company, String airport, String city, Car[] cars, double cost)
    {
        companyName = company;
        airportName = airport;
        cityName = city;
        fleet = cars; // Probably would want to do a deep copy here. 
        weight = cost;
    }
}