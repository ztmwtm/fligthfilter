package com.gridnine.testing.filters;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

public interface FlightFilter {

    int DEFAULT_FLIGHT_CHANGE_TIME = 5;
    int DEFAULT_MINIMUM_FLIGHT_DURATION_TIME = 10;
    int DEFAULT_MAXIMUM_FLIGHT_TRANSFER_TIME = 120;

    List<Flight> filter(List<Flight> flights);

    private boolean validateFlightArriveBeforeDeparture(Flight flight) {
        List<Segment> segments = flight.getSegments();
        if (segments.size() == 1) {
            return true;
        }
        segments.sort(Comparator.comparing(Segment::getDepartureDate));
        for (int i = 0; i < segments.size() - 1; i++) {
            LocalDateTime arriveTime = segments.get(i).getArrivalDate();
            LocalDateTime departureTime = segments.get(i + 1).getDepartureDate();
            if (arriveTime.isBefore(departureTime.plusMinutes(DEFAULT_FLIGHT_CHANGE_TIME))) {
                return false;
            }
        }
        return true;
    }


    private boolean validateFlightNotEmpty(Flight flight) {
        return !flight.getSegments().isEmpty();
    }
}
