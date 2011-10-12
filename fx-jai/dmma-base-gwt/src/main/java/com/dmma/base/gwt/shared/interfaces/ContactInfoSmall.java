package com.dmma.base.gwt.shared.interfaces;


public interface ContactInfoSmall{

	/** return id of contact person 
	 */
	public Integer getId();
	
	/** if image URL is null, default image will be created
	 */
	public String  getSmallImageUrl();
	
	/** return Name and Surname of contact person 
	 */
	public String  getFullName();
	
	/** return additional information. 
	 * this information will be displayed in second row
	 */
	public String  getInfo();
	
}
