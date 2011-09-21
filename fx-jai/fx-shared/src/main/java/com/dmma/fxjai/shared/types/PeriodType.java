package com.dmma.fxjai.shared.types;


public enum PeriodType {
	isM1(    1, "M1"),
	isM5(    5, "M5"),
	isM15(  15, "M15"),
	isM30(  30, "M30"),
	isH1(   60, "H1"),
	isH4(  240, "H4"),
	isD1( 1440, "D1"),
	isW1(10080, "W1"),
	isMN1(43200, "MN1");
	
	private int    id;
	private String name;
	
	private PeriodType(Integer id, String name) {
		this.id   = id;
		this.name = name;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public static PeriodType findById(Integer id){
		for(PeriodType type: PeriodType.values()){
			if(type.getId().equals(id))
				return type;
		}
		return null;
	}
	public static PeriodType findById(String id){
		return findById(Integer.valueOf(id));
		
	}
}
