import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableRowSorter;

/**
 * The FlightSearchUI class provides a graphical user interface for searching,
 * filtering, and booking flights. It displays flight results in a table and
 * allows users to interact with the data.
 */
class FlightSearchUI extends JFrame {
    private JTextField originField, destinationField;
    private JButton searchButton, bookButton, resetButton;
    private JTable resultTable;
    private List<Flight> flights;
    private FlightTableModel tableModel;

    /**
     * Constructs the FlightSearchUI and initializes its components.
     * <p>
     * This constructor sets up the main window, input fields, buttons, and
     * the flight results table.
     */
    public FlightSearchUI() {
        // Set up the main window properties
        setTitle("SkyScout - Search Flights");
        setSize(900, 600);
        setLocationRelativeTo(null); // Center the window on the screen
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Close the application when the window is closed
        setLayout(new BorderLayout());

        // Create input fields for origin and destination
        originField = new JTextField(10);
        destinationField = new JTextField(10);

        // Create the top panel for user inputs
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        // Add a date field with the current date
        JTextField dateField = new JTextField(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), 10);
        topPanel.add(new JLabel("Date:"));
        topPanel.add(dateField);
        topPanel.add(new JLabel("From:"));
        topPanel.add(originField);
        topPanel.add(new JLabel("To:"));
        topPanel.add(destinationField);

        // Add search and reset buttons
        searchButton = new JButton("Search");
        topPanel.add(searchButton);
        resetButton = new JButton("Reset");
        topPanel.add(resetButton);

        // Add the top panel to the window
        add(topPanel, BorderLayout.NORTH);

        // Create the book button for booking flights
        bookButton = new JButton("Book");
        topPanel.add(bookButton);

        // Add action listener for the book button
        bookButton.addActionListener(e -> {
            String origin = originField.getText();
            String destination = destinationField.getText();
            int selected = -1;

            // Validate user input
            if (originField.getText().isEmpty() && destinationField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please select a flight.");
            } else {
                // Find the selected flight based on origin and destination
                for (int i = 0; i < tableModel.getRowCount(); i++) {
                    String rowOrigin = (String) tableModel.getValueAt(i, 2); // Column index 2 = "From"
                    String rowDestination = (String) tableModel.getValueAt(i, 3); // Column index 3 = "To"

                    if (rowOrigin.equals(origin) && rowDestination.equals(destination)) {
                        selected = i;
                        break;
                    }
                }
            }

            // Proceed with booking if a flight is selected
            Flight selectedFlight = tableModel.getFlightAt(selected);
            String name = JOptionPane.showInputDialog("Enter passenger name:");
            if (name != null && !name.trim().isEmpty()) {
                Booking booking = new Booking(selectedFlight, name);
                booking.saveToFile();
                JOptionPane.showMessageDialog(this, booking.getReceipt());
                new SeatSelectionUI(selectedFlight);
            }
        });

        // Generate a list of flights for the table
        flights = Flight.generateFlights(100);

        // Set up the table model and result table
        tableModel = new FlightTableModel(flights);
        resultTable = new JTable(tableModel);
        resultTable.setFillsViewportHeight(true);
        resultTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        resultTable.setRowSelectionAllowed(true);
        resultTable.setColumnSelectionAllowed(false);

        // Adjust column widths for better readability
        resultTable.getColumnModel().getColumn(0).setPreferredWidth(10);
        resultTable.getColumnModel().getColumn(1).setPreferredWidth(30);
        resultTable.getColumnModel().getColumn(2).setPreferredWidth(40);
        resultTable.getColumnModel().getColumn(3).setPreferredWidth(40);
        resultTable.getColumnModel().getColumn(4).setPreferredWidth(10);
        resultTable.getColumnModel().getColumn(5).setPreferredWidth(10);

        // Enable sorting by clicking headers
        resultTable.setAutoCreateRowSorter(true);
        TableRowSorter<FlightTableModel> sorter = new TableRowSorter<>(tableModel);
        sorter.setComparator(5, Comparator.comparingDouble(price -> Double.parseDouble(price.toString().replace("$", ""))));
        resultTable.setRowSorter(sorter);

        // Alternate row colors for better visibility
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setBackground(row % 2 == 0 ? new Color(245, 245, 255) : Color.WHITE);
                return c;
            }
        };
        for (int i = 0; i < tableModel.getColumnCount(); i++) {
            resultTable.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }

        // Add a listener to update origin and destination fields when a row is selected
        resultTable.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting()) {
                int selectedRow = resultTable.getSelectedRow();
                if (selectedRow > -1) {
                    int modelRow = resultTable.convertRowIndexToModel(selectedRow);
                    String getOrigin = tableModel.getValueAt(modelRow, 2).toString();
                    String getDestination = tableModel.getValueAt(modelRow, 3).toString();
                    originField.setText(getOrigin);
                    destinationField.setText(getDestination);
                }
            }
        });

        // Add the result table to the window
        add(new JScrollPane(resultTable), BorderLayout.CENTER);

        // Add action listener for the search button
        searchButton.addActionListener(e -> {
            flights.sort(Comparator.comparing(Flight::getOrigin).thenComparing(Flight::getDestination));
            FlightSorter flightSorter = new FlightSorter();
            List<Flight> filtered = flightSorter.binarySearch(flights, originField.getText().trim(), destinationField.getText().trim());

            if (!filtered.isEmpty()) {
                tableModel.setFlights(filtered);
            } else {
                JOptionPane.showMessageDialog(this, "Flights Unavailable.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Add action listener for the reset button
        resetButton.addActionListener(e -> {
            tableModel.setFlights(flights);
        });

        // Make the window visible
        setVisible(true);
    }
}