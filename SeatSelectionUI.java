import javax.swing.*;
import java.awt.*;
import java.util.*;

class SeatSelectionUI extends JFrame {
    private final JButton[][] seatButtons = new JButton[6][4];
    private final Set<String> selectedSeats = new HashSet<>();
    private final Flight selectedFlight;

    public SeatSelectionUI(Flight flight) {
        this.selectedFlight = flight;
        setTitle("Select Your Seat - Flight " + flight.getFlightNumber());
        setSize(500, 500);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        JPanel seatPanel = new JPanel(new GridLayout(6, 4, 10, 10));
        String[] seatLabels = {"A", "B", "C", "D"};

        // Simulate booked seats (randomly, just for this example)
        Set<String> bookedSeats = new HashSet<>();
        Random rand = new Random();
        while (bookedSeats.size() < 5) {
            int row = rand.nextInt(6);
            int col = rand.nextInt(4);
            bookedSeats.add(seatLabels[col] + (row + 1));
        }

        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 4; col++) {
                String seatId = seatLabels[col] + (row + 1);
                JButton seat = new JButton(seatId);

                if (bookedSeats.contains(seatId)) {
                    seat.setEnabled(false);
                    seat.setBackground(Color.LIGHT_GRAY);
                } else {
                    seat.addActionListener(e -> {
                        if (selectedSeats.contains(seatId)) {
                            selectedSeats.remove(seatId);
                            seat.setBackground(null);
                        } else {
                            selectedSeats.add(seatId);
                            seat.setBackground(Color.GREEN);
                        }
                    });
                }

                seatButtons[row][col] = seat;
                seatPanel.add(seat);
            }
        }

        JButton confirm = new JButton("Confirm Seat Selection");
        confirm.addActionListener(e -> {
            if (selectedSeats.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please select at least one seat.");
            } else {
                JOptionPane.showMessageDialog(this, "Seats booked: " + selectedSeats);
                dispose();
                new PaymentPage(selectedFlight.getPrice());
            }
        });

        add(seatPanel, BorderLayout.CENTER);
        add(confirm, BorderLayout.SOUTH);
        setVisible(true);
    }
}