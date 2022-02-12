package com.obeid.customerclient.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.obeid.customerclient.model.Customer;
import com.obeid.customerclient.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	// inject CustomerService
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/list")
	public String getCustomers(Model model) {
		
		List<Customer> customers = customerService.getCustomers();
		// save in model 
		model.addAttribute("customers",customers);
		return "customer-list";
	}
	
	// open empty form (customer)
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model model) {
		
		Customer customer = new Customer();
		
		model.addAttribute("customer", customer);
		
		return "customer-form";
	}
	
	// add new customer
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(
			@ModelAttribute("customer")
			Customer customer) {
		
		customerService.saveCustomer(customer);
		
		return "redirect:/customer/list";
	}
	
	// open filled form (customer/given_id)
	
		@GetMapping("/showFormForUpdate") 
		public String showFormForUpdate(
				@RequestParam("customerId") 
				int id,
				Model model) {
			// get customer for the given id
			Customer customer = customerService.getCustomer(id);
			
			// add customer to model
			model.addAttribute("customer", customer);
			
			return "customer-form";
		}
		
		// delete customer by id
		
		@GetMapping("/delete")
		public String deleteCustomer(
				@RequestParam("customerId") 
				int id) {
			customerService.deleteCustomer(id);
			return "redirect:/customer/list";
			
		}
	
	
	
	
}
