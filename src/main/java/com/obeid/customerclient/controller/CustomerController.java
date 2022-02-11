package com.obeid.customerclient.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
	
	
	
	
}
