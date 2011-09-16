package com.dmma.fxjai.core.types;


public enum PeriodType {
	isM1(    1, "1 минута"),
	isM5(    5, "5 минут"),
	isM15(  15, "15 минут"),
	isM30(  30, "30 минут"),
	isH1(   60, "1 час"),
	isH4(  240, "4 часа"),
	isD1( 1440, "1 день"),
	isW1(10080, "1 неделя"),
	isMN(43200, "1 месяц");
	
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
			if(type.getId()==id)
				return type;
		}
		return null;
	}
	public static PeriodType findById(String id){
		return findById(Integer.valueOf(id));
		
	}
}
