package fit.iuh.se.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import fit.iuh.se.entities.Flight;
import fit.iuh.se.repositories.FlightRepository;
import fit.iuh.se.services.FlightService;

@Service
public class FlightServiceImpl implements FlightService{
	
	@Autowired
	private FlightRepository flightRepository;

	@Override
	public List<Flight> findAll() {
		// TODO Auto-generated method stub
		// sắp xếp airplaneName tăng dần
		return flightRepository.findAll(Sort.by(Sort.Order.asc("airplaneName")));
	}

	@Override
	public Flight findByID(int id) {
		// TODO Auto-generated method stub
		return flightRepository.findById(id).get();
	}

	//không ràng buộc (validate)
//	@Override
//	public boolean save(Flight flight) {
//		// TODO Auto-generated method stub
//		flightRepository.save(flight);
//		return true;
//	}
	
	//có ràng buộc (validate)
	@Override
	public boolean save(Flight flight) {
	    // Kiểm tra thời gian có hợp lệ hay không
	    if (flight.getArrivalDate().before(flight.getDepartureDate())) {
	        throw new IllegalArgumentException("Arrival date(ngày đến) phải sau (ngày khởi hành)departure date");
	    }

	    // Kiểm tra trùng lặp chuyến bay với airplane_name hay không
	    List<Flight> overlappingFlights = flightRepository.findByAirplaneNameAndDateOverlap(
	            flight.getAirplaneName(),
	            flight.getDepartureDate(),
	            flight.getArrivalDate()
	    );
	    if (!overlappingFlights.isEmpty()) {
	    	//quăng lỗi 
	        throw new IllegalArgumentException("Chuyến bay trùng tên máy bay (airplane_name) bị trùng với chuyến bay khác");
	    }

	    // Lưu chuyến bay nếu tất cả điều kiện đều hợp lệ
	    flightRepository.save(flight);
	    return true;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		if(flightRepository.existsById(id)) {
			flightRepository.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public boolean updateFlight(Flight flight) {
		// TODO Auto-generated method stub
		flightRepository.saveAndFlush(flight);
		return true;
	}

	@Override
	public List<Flight> search(String keyword) {
		// TODO Auto-generated method stub
		return flightRepository.search(keyword);
	}

}
