package com.VO;

public class etc_reservationVO {
	
	private String rsv_location;
	private String rsv_class;
	private String rsv_id;
	private String rsv_name;
	private String rsv_seat;
	private String checkout;
	private String rsv_date;
	
	
	public etc_reservationVO(String rsv_location, String rsv_class, String rsv_id, String rsv_name, String rsv_seat,
			String checkout, String rsv_date) {
		super();
		this.rsv_location = rsv_location;
		this.rsv_class = rsv_class;
		this.rsv_id = rsv_id;
		this.rsv_name = rsv_name;
		this.rsv_seat = rsv_seat;
		this.checkout = checkout;
		this.rsv_date = rsv_date;
	}


	public String getRsv_location() {
		return rsv_location;
	}


	public void setRsv_location(String rsv_location) {
		this.rsv_location = rsv_location;
	}


	public String getRsv_class() {
		return rsv_class;
	}


	public void setRsv_class(String rsv_class) {
		this.rsv_class = rsv_class;
	}


	public String getRsv_id() {
		return rsv_id;
	}


	public void setRsv_id(String rsv_id) {
		this.rsv_id = rsv_id;
	}


	public String getRsv_name() {
		return rsv_name;
	}


	public void setRsv_name(String rsv_name) {
		this.rsv_name = rsv_name;
	}


	public String getRsv_seat() {
		return rsv_seat;
	}


	public void setRsv_seat(String rsv_seat) {
		this.rsv_seat = rsv_seat;
	}


	public String getCheckout() {
		return checkout;
	}


	public void setCheckout(String checkout) {
		this.checkout = checkout;
	}


	public String getRsv_date() {
		return rsv_date;
	}


	public void setRsv_date(String rsv_date) {
		this.rsv_date = rsv_date;
	}
	
	
	
}
