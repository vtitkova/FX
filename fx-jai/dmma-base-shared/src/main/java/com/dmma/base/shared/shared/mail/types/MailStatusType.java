package com.dmma.base.shared.shared.mail.types;



public enum MailStatusType {
	isNew(   1),
	isSent(  2),
	isFailed(3);
	
	private Integer id;
	
	public static MailStatusType getById(Integer id){
		for(MailStatusType type: MailStatusType.values()){
			if(type.getId()==id)
				return type;
		}
		return null;
	}
	
	private MailStatusType(Integer id){
		this.setId(id);
	}

	

	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getId() {
		return id;
	}
		
}
