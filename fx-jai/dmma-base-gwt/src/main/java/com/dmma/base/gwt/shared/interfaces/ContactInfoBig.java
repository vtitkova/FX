package com.dmma.base.gwt.shared.interfaces;



public interface ContactInfoBig{

	/** return id of contact person 
	 */
	public Integer getId();
	
	/** if image URL is null, default image will be created
	 */
	public String  getBigImageUrl();
	
	/** return Name and Surname of contact person 
	 */
	public String  getFullName();
	
	
	/** return list of additional information.  
	 *  this information will be subsequently displayed in rows
	 *  You can use safe HTML in return value
	 */
	public String[] getInfoList();
	
}
