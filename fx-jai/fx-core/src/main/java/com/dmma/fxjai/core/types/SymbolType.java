package com.dmma.fxjai.core.types;

public enum SymbolType{
	GBPUSD(1),
	EURUSD(2),
	USDJPY(3),
	USDCHF(4),
	USDCAD(5),
	AUDUSD(6),
	NZDUSD(7),
	GBPCHF(8),
	GBPJPY(9),
	
	GOLD  (101),
	SILVER(102);
	
	private Integer id;
	
	
	private SymbolType(Integer id) {
		this.id   = id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	
	public static SymbolType findById(Integer id){
		for(SymbolType type: SymbolType.values()){
			if(type.getId().equals(id))
				return type;
		}
		return null;
		
	}

	public static SymbolType findByStr(String symbolStr) {
		for(SymbolType type: SymbolType.values()){
			if(type.name().equalsIgnoreCase(symbolStr))
				return type;
		}
		return null;
	}
	
	
	
	
}
