package com.boot.accellearn.respropertyhiding;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class PropertyHidingController {
	@GetMapping("/orders-json-ignore-on-pojo")
	public List<Order> getOrders(){
		//Tez notes:
		//Order cost will not be sent in response
		//It is hidden from exposure because the Order pojo has 
		//fasterxml @JsonIgnore annotated over orderCost data member.
		//This however is static.
		return Arrays.asList(
				new Order("ORD1", 200, 5000.49, "CUST1"),
				new Order("ORD2", 310, 7030.58, "CUST2"));
	}
	
	@GetMapping("/orders-dynamic-prop-hiding")
	//Response here hides orderCost & customerCode properties in response.
	public MappingJacksonValue getCustomerOrders(){
		
		List<CustomerOrder> customerOrders = Arrays.asList(
				new CustomerOrder("ORD1", 200, 5000.49, "CUST1"),
				new CustomerOrder("ORD2", 310, 7030.58, "CUST2"));
		
		//Tez notes:
		//
		Set<String> propsToShow = new HashSet<String>();
					propsToShow.add("orderId");
					propsToShow.add("orderQuantity");
		SimpleBeanPropertyFilter beanPropFilter = SimpleBeanPropertyFilter
				.filterOutAllExcept(propsToShow);
		//Tez notes:
		//Filter id DynaCustOrderPropHideFilter is to be referenced from the POJO
		//using @JsonFilter. Seems like a funny useless step or config
		FilterProvider filterProvider = new SimpleFilterProvider()
				.addFilter("DynaCustOrderPropHideFilter", beanPropFilter);
		
		
		MappingJacksonValue filteredResponse = new MappingJacksonValue(customerOrders);
							filteredResponse.setFilters(filterProvider); 
		return filteredResponse;
	}
	
	@GetMapping("/orders-dynamic-prop-cost-hiding")
	//Response here hides just orderCost property in response.
	public MappingJacksonValue getCustomerOrders2(){
		
		List<CustomerOrder> customerOrders = Arrays.asList(
				new CustomerOrder("ORD1", 200, 5000.49, "CUST1"),
				new CustomerOrder("ORD2", 310, 7030.58, "CUST2"));
		
		//Tez notes:
		//
		Set<String> propsToShow = new HashSet<String>();
					propsToShow.add("orderId");
					propsToShow.add("orderQuantity");
					propsToShow.add("orderCost");
		SimpleBeanPropertyFilter beanPropFilter = SimpleBeanPropertyFilter
				.filterOutAllExcept(propsToShow);
		//Tez notes:
		//Filter id DynaCustOrderPropHideFilter is to be referenced from the POJO
		//using @JsonFilter. Seems like a funny useless step or config
		FilterProvider filterProvider = new SimpleFilterProvider()
				.addFilter("DynaCustOrderPropHideFilter", beanPropFilter);
		
		
		MappingJacksonValue filteredResponse = new MappingJacksonValue(customerOrders);
							filteredResponse.setFilters(filterProvider); 
		return filteredResponse;
	}

}
