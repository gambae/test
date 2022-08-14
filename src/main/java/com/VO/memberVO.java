package com.VO;

// 회원들의 정보만 가지고있는 클래스
public class memberVO {
	
	// 한 사람의 가지고 있어야하는 필드
	// 외부에서 접근하지못하게 접근제한자를 private로 설정
	private String cls;
	private String id;
	private String pw;
	private String email;
	private String name;
	private String birth;
	private String tel;
	
	// ★★ 로그인할 때 로그인 한 사람의 세션을 저장할 생성자 (객체 생성시 4개의 값을 무조건 넣어줘야한다)
	public memberVO(String cls, String id, String email, String name) {
		super();
		this.cls = cls;
		this.id = id;
		this.email = email;
		this.name = name;
	}
	
	// 현재 클래스의 필드들의 접근제한자가 private이기 때문에
	// 외부에서 필드의 값을 가져오거나 바꿀때에는 getter & setter 메소드를 통해 접근하거나 수정한다.
	public String getCls() {
		return cls;
	}

	public void setCls(String cls) {
		this.cls = cls;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	
	
}
