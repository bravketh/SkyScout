import java.util.*;

/**
 * The Flight class represents a flight with details such as flight number,
 * airline, origin, destination, departure time, and price.
 * <p>
 * This class provides methods to retrieve flight details and generate a list
 * of random flights for testing or demonstration purposes.
 */
public class Flight {
    /**
     * The flight number of the flight.
     */
    private final String flightNumber;

    /**
     * The airline operating the flight.
     */
    private final String airline;

    /**
     * The origin city of the flight.
     */
    private final String origin;

    /**
     * The destination city of the flight.
     */
    private final String destination;

    /**
     * The departure time of the flight.
     */
    private final String departureTime;

    /**
     * The price of the flight ticket.
     */
    private final double price;

    /**
     * Constructs a Flight object with the specified details.
     * 
     * @param flightNumber The flight number.
     * @param airline The airline operating the flight.
     * @param origin The origin city of the flight.
     * @param destination The destination city of the flight.
     * @param departureTime The departure time of the flight.
     * @param price The price of the flight ticket.
     */
    public Flight(String flightNumber, String airline, String origin, String destination, String departureTime, double price) {
        this.flightNumber = flightNumber;
        this.airline = airline;
        this.origin = origin;
        this.destination = destination;
        this.departureTime = departureTime;
        this.price = price;
    }

    /**
     * Retrieves the flight number.
     * 
     * @return The flight number.
     */
    public String getFlightNumber() { return flightNumber; }

    /**
     * Retrieves the airline name.
     * 
     * @return The airline name.
     */
    public String getAirline() { return airline; }

    /**
     * Retrieves the origin city.
     * 
     * @return The origin city.
     */
    public String getOrigin() { return origin; }

    /**
     * Retrieves the destination city.
     * 
     * @return The destination city.
     */
    public String getDestination() { return destination; }

    /**
     * Retrieves the departure time.
     * 
     * @return The departure time.
     */
    public String getDepartureTime() { return departureTime; }

    /**
     * Retrieves the price of the flight ticket.
     * 
     * @return The price of the flight ticket.
     */
    public double getPrice() { return price; }

    /**
     * Retrieves the details of the flight as a formatted string.
     * <p>
     * The details include flight number, airline, origin, destination,
     * departure time, and price.
     * 
     * @return A formatted string containing flight details.
     */
    public String getDetails() {
        return String.format("Flight: %s\nAirline: %s\nFrom: %s -> To: %s\nDeparture: %s\nPrice: $%.2f\n",
                flightNumber, airline, origin, destination, departureTime, price);
    }

    /**
     * Generates a list of random flights for testing or demonstration purposes.
     * <p>
     * The flights are generated with random origins, destinations, airlines,
     * flight numbers, departure times, and prices.
     * 
     * @param count The number of flights to generate.
     * @return A list of randomly generated flights.
     */
    public static List<Flight> generateFlights(int count) {
        List<Flight> flights = new ArrayList<>();
        Random rand = new Random();

        // List of cities for random origin and destination generation
        String[] cities = {
            "New York", "Los Angeles", "Chicago", "Houston", "Miami",
            "London", "Paris", "Berlin", "Amsterdam", "Madrid",
            "Tokyo", "Osaka", "Seoul", "Shanghai", "Beijing",
            "Sydney", "Melbourne", "Auckland", "Perth", "Brisbane",
            "Dubai", "Doha", "Istanbul", "Moscow", "Cairo",
            "São Paulo", "Buenos Aires", "Lima", "Bogotá", "Mexico City",
            "Toronto", "Vancouver", "Montreal", "Calgary", "Ottawa",
            "Cape Town", "Johannesburg", "Nairobi", "Casablanca", "Lagos",
            "Mumbai", "Delhi", "Rome", "Barcelona", "Kolkata",
            "Bangkok", "Singapore", "Kuala Lumpur", "Jakarta", "Manila",
            "Helsinki", "Vienna", "Stockholm", "Copenhagen", "Milan", "Oslo",
            "Munich", "Istanbul", "Liverpool"
        };

        // List of airlines for random airline generation
        String[] airlines = {
            "SkyJet", "FlyHigh Airways", "Global Air", "Sunrise Airlines", "AeroVista",
            "Nimbus Airlines", "Falcon Wings", "StarJet", "BlueSky Airways", "CloudNine"
        };

        // Generate random flights
        for (int i = 0; i < count; i++) {
            String origin = cities[rand.nextInt(cities.length)];
            String destination = cities[rand.nextInt(cities.length)];
            String airline = airlines[rand.nextInt(airlines.length)];
            String flightNum = "FL" + (1000 + rand.nextInt(9000)); // Random flight number
            String departureTime = (10 + rand.nextInt(10)) + ":00"; // Random departure time
            double price = Math.round((100 + rand.nextDouble() * 900) * 100) / 100.0; // Random price
            
            // Ensure origin and destination are not the same
            while (origin.equals(destination)) {
                destination = cities[rand.nextInt(cities.length)];
            }

            // Add the generated flight to the list
            flights.add(new Flight(flightNum, airline, origin, destination, departureTime, price));
        }
        return flights;
    }
}