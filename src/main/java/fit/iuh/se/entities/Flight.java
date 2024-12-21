package fit.iuh.se.entities;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "flights")
public class Flight {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "departure_airport", nullable = false, length = 255)
	@NotEmpty(message = "Departure airport không được để trống")
	private String departureAirport;
	
	@Column(name = "arrival_airport", nullable = false, length = 255)
	@NotEmpty(message = "Arrival airport không được để trống")
	private String arrivalAirport;
	
	@Column(name = "departure_date", nullable = false)
    @Temporal(TemporalType.DATE)
	@FutureOrPresent(message = "Departure date phải sau ngày hiện tại")
    @NotNull(message = "Departure date không được để trống")
	private Date departureDate;
	
	@Column(name = "arrival_date", nullable = false)
    @Temporal(TemporalType.DATE)
	@FutureOrPresent(message = "Arrival date phải sau ngày hiện tại")
    @NotNull(message = "Arrival date không được để trống")
	private Date arrivalDate;
	
	@Column(name = "seat_fare", nullable = false)
	@NotNull(message = "Seat fare không được để trống")
	@Min(value = 1, message = "Seat fare phải lớn hơn 0")
	private Double seatFare;
	
	@Column(name = "flight_status", nullable = false)
	private Boolean flightStatus;
	
	@Column(name = "airplane_name", nullable = false, length = 255)
	@NotEmpty(message = "Airplane name không được để trống")
	private String airplaneName;

	public Flight() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Flight(int id, @NotEmpty(message = "Departure airport không được để trống") String departureAirport,
			@NotEmpty(message = "Arrival airport không được để trống") String arrivalAirport,
			@FutureOrPresent(message = "Departure date phải sau ngày hiện tại") @NotNull(message = "Departure date không được để trống") Date departureDate,
			@FutureOrPresent(message = "Arrival date phải sau ngày hiện tại") @NotNull(message = "Arrival date không được để trống") Date arrivalDate,
			@NotNull(message = "Seat fare không được để trống") @Min(value = 1, message = "Seat fare phải lớn hơn 0") Double seatFare,
			Boolean flightStatus, @NotEmpty(message = "Airplane name không được để trống") String airplaneName) {
		super();
		this.id = id;
		this.departureAirport = departureAirport;
		this.arrivalAirport = arrivalAirport;
		this.departureDate = departureDate;
		this.arrivalDate = arrivalDate;
		this.seatFare = seatFare;
		this.flightStatus = flightStatus;
		this.airplaneName = airplaneName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDepartureAirport() {
		return departureAirport;
	}

	public void setDepartureAirport(String departureAirport) {
		this.departureAirport = departureAirport;
	}

	public String getArrivalAirport() {
		return arrivalAirport;
	}

	public void setArrivalAirport(String arrivalAirport) {
		this.arrivalAirport = arrivalAirport;
	}

	public Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}

	public Date getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public Double getSeatFare() {
		return seatFare;
	}

	public void setSeatFare(Double seatFare) {
		this.seatFare = seatFare;
	}

	public Boolean getFlightStatus() {
		return flightStatus;
	}

	public void setFlightStatus(Boolean flightStatus) {
		this.flightStatus = flightStatus;
	}

	public String getAirplaneName() {
		return airplaneName;
	}

	public void setAirplaneName(String airplaneName) {
		this.airplaneName = airplaneName;
	}
	
	public String getDepartureDateFormatted() {
        if (departureDate != null) {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            return formatter.format(departureDate);
        }
        return null;
    }

    public String getArrivalDateFormatted() {
        if (arrivalDate != null) {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            return formatter.format(arrivalDate);
        }
        return null;
    }
    
    public String getFlightStatusText() {
        return flightStatus != null && flightStatus ? "New" : "Scheduled";
    }

    public String getFormattedSeatFare() {
        if (seatFare != null) {
            NumberFormat formatter = NumberFormat.getInstance(new Locale("vi", "VN"));
            return formatter.format(seatFare);
        }
        return null;
    }
}
