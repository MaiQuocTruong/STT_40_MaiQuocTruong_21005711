package fit.iuh.se.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fit.iuh.se.entities.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer>{
    @Query(value = "SELECT * FROM flights f WHERE f.airplane_name LIKE %:keyword%", nativeQuery = true)
	List<Flight> search(@Param("keyword") String keyword);
    
    //kiểm tra trùng chuyến bay hay không
    @Query("SELECT f FROM Flight f WHERE f.airplaneName = :airplaneName AND " +
    	       "(:departureDate BETWEEN f.departureDate AND f.arrivalDate OR " +
    	       ":arrivalDate BETWEEN f.departureDate AND f.arrivalDate)")
    	List<Flight> findByAirplaneNameAndDateOverlap(
    	        @Param("airplaneName") String airplaneName,
    	        @Param("departureDate") Date departureDate,
    	        @Param("arrivalDate") Date arrivalDate);

}
