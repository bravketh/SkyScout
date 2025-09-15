import java.awt.*;
import java.util.List;
import javax.swing.*;

/**
 * The BookingPanel class represents the graphical user interface (GUI) for displaying
 * available flights and allowing users to book a flight. It provides functionality
 * to sort flights by price or airline and handles user interactions for booking.
 * 
 * This class is designed to be user-friendly and integrates with other components
 * such as the Booking and PaymentPage classes.
 * 
 * @author Bravean Ketheeswaran, Logan Virdiramo
 * @version 1.0
 */
public class BookingPanel extends JFrame {
    /**
     * A text area for displaying the list of available flights.
     */
    private JTextArea flightArea;

    /**
     * A list of flights available for booking.
     */
    private List<Flight> flights;

    /**
     * Constructs the BookingPanel GUI and initializes its components.
     * The GUI displays a list of flights and provides buttons for sorting
     * and booking flights.
     * <p>
     * This constructor sets up the window properties, initializes the flight list,
     * and adds interactive components for user actions.
     */
    public BookingPanel() {
        // Set up the main window properties
        setTitle("Available Flights");
        setSize(700, 500);
        setLocationRelativeTo(null); // Center the window on the screen
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close only this window when exiting
        setLayout(new BorderLayout()); // Use BorderLayout for organizing components

        // Generate a list of flights and display them in the text area
        flights = Flight.generateFlights(20); // Generate 20 sample flights
        flightArea = new JTextArea(); // Create a text area for displaying flight details
        flightArea.setEditable(false); // Make the text area read-only
        refreshFlightList(); // Populate the text area with flight details

        // Add a scroll pane to allow scrolling through the flight list
        JScrollPane scrollPane = new JScrollPane(flightArea);
        add(scrollPane, BorderLayout.CENTER); // Add the scroll pane to the center of the layout

        // Create a control panel with buttons for sorting and booking flights
        JPanel controlPanel = new JPanel();
        JButton sortPrice = new JButton("Sort by Price"); // Button to sort flights by price
        JButton sortAirline = new JButton("Sort by Airline"); // Button to sort flights by airline
        JButton bookFlight = new JButton("Book Flight"); // Button to book a flight

        // Add buttons to the control panel
        controlPanel.add(sortPrice);
        controlPanel.add(sortAirline);
        controlPanel.add(bookFlight);
        add(controlPanel, BorderLayout.SOUTH); // Add the control panel to the bottom of the layout

        /*
         * Adds an action listener to the "Book Flight" button.
         * Prompts the user to enter a flight number and passenger name,
         * creates a booking, displays the receipt, and saves it to a file.
         * 
         * @param e The action event triggered by clicking the button.
         */
        bookFlight.addActionListener(e -> {
            // Prompt the user to enter the flight number they want to book
            String flightNum = JOptionPane.showInputDialog("Enter Flight Number to Book:");
            
            // Find the flight with the matching flight number
            Flight selected = flights.stream()
                .filter(f -> f.getFlightNumber().equalsIgnoreCase(flightNum))
                .findFirst().orElse(null);

            if (selected != null) {
                // Prompt the user to enter their name for the booking
                String name = JOptionPane.showInputDialog("Enter Passenger Name:");
                
                // Create a new booking and display the receipt
                Booking booking = new Booking(selected, name);
                JOptionPane.showMessageDialog(this, booking.getReceipt());
                
                // Save the booking receipt to a file
                booking.saveToFile();
                
                // Open the payment page for the flight
                new PaymentPage(selected.getPrice());
            } else {
                // Show an error message if the flight number is not found
                JOptionPane.showMessageDialog(this, "Flight not found.");
            }
        });

        // Make the window visible
        setVisible(true);
    }

    /**
     * Refreshes the flight list displayed in the text area.
     * This method updates the text area with the details of all flights.
     * <p>
     * The flight details include information such as flight number, airline,
     * departure time, and price.
     * 
     * @see Flight#getDetails()
     */
    private void refreshFlightList() {
        // Use a StringBuilder to construct the flight list text
        StringBuilder sb = new StringBuilder();
        for (Flight flight : flights) {
            sb.append(flight.getDetails()).append("\n"); // Append each flight's details
        }
        flightArea.setText(sb.toString()); // Update the text area with the flight list
    }
}