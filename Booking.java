import java.io.*;

/**
 * The Booking class represents a reservation made for a flight.
 * It stores information about the flight and the passenger's name,
 * and provides functionality to generate a receipt and save it to a file.
 * 
 * This class is designed to be simple and easy to use, making it suitable
 * for applications that manage airline bookings.
 * 
 * @author Bravean Ketheeswaran, Logan Virdiramo
 */
public class Booking {
    /**
     * The flight associated with this booking.
     */
    private final Flight flight;

    /**
     * The name of the passenger who made the booking.
     */
    private final String passengerName;

    /**
     * Constructs a Booking object with the specified flight and passenger name.
     * 
     * @param flight The flight associated with the booking.
     * @param passengerName The name of the passenger making the booking.
     */
    public Booking(Flight flight, String passengerName) {
        this.flight = flight;
        this.passengerName = passengerName;
    }

    /**
     * Generates a receipt for the booking.
     * The receipt includes flight details and the passenger's name.
     * 
     * @return A string representing the booking receipt.
     */
    public String getReceipt() {
        return "Booking Receipt\n" + flight.getDetails() + "Passenger: " + passengerName + "\n";
    }

    /**
     * Saves the booking receipt to a file named "booking_receipt.txt".
     * If the file already exists, the receipt is appended to it.
     * 
     * This method handles any errors that occur during file writing
     * and prints an error message if saving fails.
     */
    public void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("booking_receipt.txt", true))) {
            writer.println(getReceipt());
            writer.println("----------------------");
        } catch (IOException e) {
            System.out.println("Error saving receipt: " + e.getMessage());
        }
    }
}