package com.gridnine.testing.filters;

import com.gridnine.testing.model.Flight;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class FilterDepartureBeforeCurrentTime implements FlightFilter {

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
                                                .isAfter(LocalDateTime.now()))).
                collect(Collectors.toList());
    }
}
