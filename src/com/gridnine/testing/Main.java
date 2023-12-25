package com.gridnine.testing;

import com.gridnine.testing.filters.FilterArrivalEarlierDeparture;
import com.gridnine.testing.filters.FilterDepartureBeforeCurrentTime;
import com.gridnine.testing.filters.FilterTotalTransferTimeGraterThen;
import com.gridnine.testing.filters.FlightFilter;
import com.gridnine.testing.model.Flight;
import com.gridnine.testing.utils.ConsolePrinter;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        FlightFilter filterArrivalEarlierDeparture = new FilterArrivalEarlierDeparture();
        FlightFilter filterDepartureBeforeCurrentTime = new FilterDepartureBeforeCurrentTime();
        FlightFilter filterTotalTransferTimeGraterThen = new FilterTotalTransferTimeGraterThen();
        List<Flight> flights = FlightBuilder.createFlights();
        System.out.println("All flights");
        flights.forEach(ConsolePrinter::print);
        List<Flight> flightsWithoutArrivalEarlierDeparture = filterArrivalEarlierDeparture.filter(flights);
        System.out.println("\nFlights without arrival earlier then departure");
        flightsWithoutArrivalEarlierDeparture.forEach(ConsolePrinter::print);
        List<Flight> flightsDepartureBeforeCurrentTime = filterDepartureBeforeCurrentTime.filter(flights);
        System.out.println("\nFlights without departure earlier then current time");
        flightsDepartureBeforeCurrentTime.forEach(ConsolePrinter::print);
        List<Flight> flightsTotalTransferTimeGraterThen = filterTotalTransferTimeGraterThen.filter(flights);
        System.out.println("\nFlights without transfer garter then 2 hours");
        flightsTotalTransferTimeGraterThen.forEach(ConsolePrinter::print);
    }
}