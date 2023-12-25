package com.gridnine.testing.filters;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;

import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;


public class FilterTotalTransferTimeGraterThen implements FlightFilter {

    @Override
    public List<Flight> filter(List<Flight> flights) {
        return flights
                .stream()
                .parallel()
                .filter(this::checkTransferTimeIsLowerThenDefault)
                .toList();
    }

    private boolean checkTransferTimeIsLowerThenDefault(Flight flight) {

        List<Segment> segments = flight.getSegments();
        if (segments.size() == 1) {
            return true;
        }
        int totalTime = 0;
        segments.sort(Comparator.comparing(Segment::getDepartureDate));
        for (int i = 0; i < segments.size() - 1; i++) {
            totalTime += ChronoUnit.MINUTES.between(segments.get(i).getArrivalDate(), segments.get(i + 1).getDepartureDate());
        }
        return totalTime <= DEFAULT_MAXIMUM_FLIGHT_TRANSFER_TIME;
    }

}
