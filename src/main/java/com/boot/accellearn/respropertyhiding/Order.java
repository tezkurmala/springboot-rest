package com.boot.accellearn.respropertyhiding;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//@JsonIgnoreProperties(value = {"orderCost", "customerCode"})
//Not recommended as this is hard coded list and can be 
//of problem if the variable names get changed. We have to change here too.
//@JsonIgnore is better on the variable directly.
public class Order {
	private String orderId;
	private Integer orderQuantity;
	@JsonIgnore
	private Double orderCost;
	private String customerCode;
	public Order() {

	}
	public Order(String orderId, Integer orderQuantity, Double orderCost, String customerCode) {
		super();
		this.orderId = orderId;
		this.orderQuantity = orderQuantity;
		this.orderCost = orderCost;
		this.customerCode = customerCode;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public Integer getOrderQuantity() {
		return orderQuantity;
	}
	public void setOrderQuantity(Integer orderQuantity) {
		this.orderQuantity = orderQuantity;
	}
	public Double getOrderCost() {
		return orderCost;
	}
	public void setOrderCost(Double orderCost) {
		this.orderCost = orderCost;
	}
	public String getCustomerCode() {
		return customerCode;
	}
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	
}
