package com.boot.accellearn.respropertyhiding;

import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("DynaCustOrderPropHideFilter")
public class CustomerOrder {
	private String orderId;
	private Integer orderQuantity;
	private Double orderCost;
	private String customerCode;
	public CustomerOrder() {
		
	}
	public CustomerOrder(String orderId, Integer orderQuantity, Double orderCost, String customerCode) {
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
