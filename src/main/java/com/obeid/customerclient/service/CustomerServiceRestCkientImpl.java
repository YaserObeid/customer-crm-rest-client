package com.obeid.customerclient.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.obeid.customerclient.model.Customer;


@Service
public class CustomerServiceRestCkientImpl implements CustomerService {
	
	private RestTemplate restTemplate;
	
	// will be inject in the constructor
	private String crmRestUrl;
	
	
	// constructor dependency injection
	@Autowired
	public CustomerServiceRestCkientImpl(
							RestTemplate template,
							@Value(value = "${crm.rest.url}")
							String url) {
		restTemplate = template;
		crmRestUrl = url;
			
	}
	
	
	
	
	
	// GET: /customers 

	@Override
	public List<Customer> getCustomers() {
		// make REST call
		ResponseEntity<List<Customer>> responseEntity =
		restTemplate.exchange(crmRestUrl, 
							  HttpMethod.GET, 
							  null, 
						 new ParameterizedTypeReference<List<Customer>>() {});
		
		// get the list of customers from response		
		return responseEntity.getBody();
	}
	
	
	
	
	//POST: /customers & PUT: /customers
	@Override
	public void saveCustomer(Customer theCustomer) {
		
		// test the customerId -> post or put:
		if(theCustomer.getId() ==0) {
			// add customer
			restTemplate.postForEntity(crmRestUrl, theCustomer, String.class);
		}
		else {
			// update customer
			restTemplate.put(crmRestUrl, theCustomer);
		}

	}
	
	
	
	
	// GET: /customers/{customerId)
	@Override
	public Customer getCustomer(int theId) {
		
		// make REST call
		Customer customer = 
			restTemplate.getForObject(crmRestUrl+ "/" + theId, Customer.class);
		return customer;
	}
	
	
	
	
	
	//DELETE: /customers/{customerId)
	@Override
	public void deleteCustomer(int theId) {
		
		//make call
		restTemplate.delete(crmRestUrl);

	}

}
