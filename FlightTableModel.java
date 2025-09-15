import javax.swing.table.*;
import javax.swing.*;
import java.util.*;

class FlightTableModel extends AbstractTableModel {
    private List<Flight> flights;
    private final String[] columns = {"Flight No", "Airline", "From", "To", "Departure", "Price"};

    public FlightTableModel(List<Flight> flights) {
        this.flights = flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
        fireTableDataChanged();
    }

    public Flight getFlightAt(int row) {
        return flights.get(row);
    }

    public int getRowCount() {
        return flights.size();
    }

    public int getColumnCount() {
        return columns.length;
    }

    public String getColumnName(int col) {
        return columns[col];
    }

    @Override
    public Object getValueAt(int row, int col) {
        Flight f = flights.get(row);
        switch (col) {
            case 0: return f.getFlightNumber();
            case 1: return f.getAirline();
            case 2: return f.getOrigin();
            case 3: return f.getDestination();
            case 4: return f.getDepartureTime();
            case 5: return String.format("$%.2f", f.getPrice());
            default: return "";
        }
    };
    }