package fit.iuh.se.services;

import java.util.List;

import fit.iuh.se.entities.Flight;

public interface FlightService {
	List<Flight> findAll();
	Flight findByID(int id);
	boolean save(Flight flight);
	boolean delete(int id);
	boolean updateFlight(Flight flight);
	List<Flight> search(String keyword);
}
