package com.VO;

// 예약한 사람들의 정보만 가진 클래스
public class reservationVO {
	
	// lecture_reservation 테이블에있는 컬럼들을 필드로 선언
	private String rsv_cls;
	private String rsv_id;
	private String rsv_name;
	private String checkout;
	private String rsv_date;

	
	// 예약을 한 사람의 정보를 담은 세션을 만들기위한 생성자
	public reservationVO(String rsv_cls, String rsv_id, String rsv_name, String checkout, String rsv_date) {
		super();
		this.rsv_cls = rsv_cls;
		this.rsv_id = rsv_id;
		this.rsv_name = rsv_name;
		this.checkout = checkout;
		this.rsv_date = rsv_date;
	}


	public String getRsv_cls() {
		return rsv_cls;
	}


	public void setRsv_cls(String rsv_cls) {
		this.rsv_cls = rsv_cls;
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
