package com.dmma.fxjai.core.types;

public enum AccountType{
	isDemo( 3, "DEMO"),
	isMicro(2, "MICRO"),
	isReal( 1, "REAL");
	
	private Integer id;
	private String name;
	
	private AccountType(Integer id, String name) {
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
	
	
	public static AccountType findByName(String name){
		for(AccountType type: AccountType.values()){
			if(type.getName().equals(name))
				return type;
		}
		return null;
		
	}
	
	public static AccountType findById(Integer id){
		for(AccountType type: AccountType.values()){
			if(type.getId().equals(id))
				return type;
		}
		return null;
	}
	
}

