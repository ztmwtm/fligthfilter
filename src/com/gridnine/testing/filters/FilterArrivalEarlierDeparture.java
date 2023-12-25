package com.gridnine.testing.filters;

import com.gridnine.testing.model.Flight;

import java.util.List;
import java.util.stream.Collectors;

public class FilterArrivalEarlierDeparture implements FlightFilter {

    @Override
    public List<Flight> filter(List<Flight> flights) {
        return flights
                .stream()
                .parallel()
                .filter(
                        flight -> flight.getSegments()
                                .stream()
                                .anyMatch(
                                        segment -> segment.getDepartureDate()
                                                .isBefore(segment.getArrivalDate()
                                                        .plusMinutes(DEFAULT_MINIMUM_FLIGHT_DURATION_TIME)))).
                collect(Collectors.toList());
    }
}