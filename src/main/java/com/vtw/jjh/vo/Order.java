package com.vtw.jjh.vo;



public class Order {
	
	//aTypeSendRoute, bTypeSendRoute에서 json, xml형태로 데이터 변환전 정보 담을 VO
	private int id;
	private String name;
	private double price;
	
	public Order() {
	}
	public Order(int id, String name, double price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	

}
