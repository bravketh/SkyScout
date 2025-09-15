import java.util.*;

public class FlightSorter{
    
    public List<Flight> binarySearch(List<Flight> sortedFlights, String origin, String destination) {
        List<Flight> filtered = new ArrayList<>();
        int left = 0;
        int right = sortedFlights.size() - 1;
                
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            Flight midFlight = sortedFlights.get(mid);
            
            if ((origin.isEmpty() || midFlight.getOrigin().equals(origin)) && (destination.isEmpty() || midFlight.getDestination().equals(destination))) {
                
                filtered.add(midFlight);

                // Look for additional matches before and after mid
                int i = mid - 1;
                while (i >= 0 && (origin.isEmpty() || sortedFlights.get(i).getOrigin().equals(origin)) &&
                                (destination.isEmpty() || sortedFlights.get(i).getDestination().equals(destination))) {
                    filtered.add(sortedFlights.get(i));
                    i--;
                }
    
                i = mid + 1;
                while (i < sortedFlights.size() && (origin.isEmpty() || sortedFlights.get(i).getOrigin().equals(origin)) &&
                                                 (destination.isEmpty() || sortedFlights.get(i).getDestination().equals(destination))) {
                    filtered.add(sortedFlights.get(i));
                    i++;
                }
    
                break; // Exit loop after finding all matches
            } 
            else if (!origin.isEmpty() && origin.compareTo(midFlight.getOrigin()) > 0) {
                left = mid + 1;
            }
            else if (!destination.isEmpty() && destination.compareTo(midFlight.getDestination()) > 0){
                 left = mid + 1; 
            }
            else {
                right = mid - 1;
            }
        }
        return filtered;
    }
}