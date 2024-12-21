package fit.iuh.se.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fit.iuh.se.entities.Flight;
import fit.iuh.se.services.FlightService;
import jakarta.validation.Valid;

@Controller
public class FlightController {
	@Autowired
	FlightService flightService;
	
	//hien thi danh sach flight
	@GetMapping("/flight")
	public String getAllFlight(Model model) {
		model.addAttribute("fl", flightService.findAll());
		return "flight-list";
	}
	
	//hien thi FORM THÊM
	@GetMapping("/flight/add-flight")
	public String addFlightForm(Model model) {
		model.addAttribute("addNewFlight", new Flight());
		return "AddFlight";
	}
	
	//lưu kiểu Date thành dạng text
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new java.beans.PropertyEditorSupport() {
			@Override
			public void setAsText(String text) throws IllegalArgumentException {
				try {
					setValue(dateFormat.parse(text));
				} catch (Exception e) {
					setValue(null); 
              }
			}
		});
	}
	
	// THÊM bình thường không ràng buộc (Validate)
//	@PostMapping("/flight/save")
//	public String saveFlight(@ModelAttribute("addNewFlight") Flight flight) {
//		flightService.save(flight);
//		return "redirect:/flight";
//	}
	
	// THÊM có ràng buộc (Validate)
	@PostMapping("/flight/save")
	public String saveFlight(@Valid @ModelAttribute("addNewFlight") Flight flight, 
	                         BindingResult result, Model model) {
	    if (result.hasErrors()) {
	        model.addAttribute("addNewFlight", flight);
	        return "AddFlight";
	    }

	    try {
	        flightService.save(flight);
	    } catch (IllegalArgumentException e) {
	        model.addAttribute("errorMessage", e.getMessage());
	        model.addAttribute("addNewFlight", flight);
	        return "AddFlight";
	    }
	    
	    return "redirect:/flight";
	}
	
	//Xóa theo id của flight
	@GetMapping("/flight/delete/{id}")
	public String deleteFlight(@PathVariable("id") int id) {
		boolean isDeleted = flightService.delete(id);
		if (isDeleted) {
			return "redirect:/flight";
        } else {
            return "redirect:/flight";
		} 
	}
	
	//Hiển thị form EDIT FLIGHT (Chỉnh sửa)
	@GetMapping("/flight/edit-flight/{id}")
	public String editFlightForm(@PathVariable("id") int id, Model model) {
		Flight flight = flightService.findByID(id);
		model.addAttribute("updateFlight", flight);
		return "EditFlight";
	}

	//Update flight bình thường không có validate
//	@PostMapping("/flight/update/{id}")
//	public String updateFlight(@PathVariable("id") int id,
//			                   @ModelAttribute("updateFlight") Flight flight, Model model) {
//		flight.setId(id);
//		flightService.save(flight);
//		return "redirect:/flight";
//	}
	
	//Update flight có validate
	@PostMapping("/flight/update/{id}")
	public String updateFlight(@PathVariable("id") int id,
	                           @ModelAttribute("updateFlight") Flight flight,
	                           BindingResult result, Model model) {
	    if (result.hasErrors()) {
	        model.addAttribute("updateFlight", flight);
	        return "EditFlight";
	    }

	    try {
	        flight.setId(id);  // Ensure the ID is maintained
	        flightService.updateFlight(flight);
	    } catch (IllegalArgumentException e) {
	        model.addAttribute("errorMessage", e.getMessage());
	        model.addAttribute("updateFlight", flight);
	        return "EditFlight";
	    }
	    return "redirect:/flight";
	}

	// Tìm kiếm theo tên chuyến bay (airplane_name)
	@GetMapping("/flight/search")
	public String searchFlight(@RequestParam("keyword") String keyword, Model model) {
		List<Flight> flight = flightService.search(keyword);
		model.addAttribute("fl", flight);
		model.addAttribute("keyword", keyword);
		return "flight-list";
	}
}
