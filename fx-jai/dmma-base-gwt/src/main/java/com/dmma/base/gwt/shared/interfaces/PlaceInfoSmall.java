package com.dmma.base.gwt.shared.interfaces;


public interface PlaceInfoSmall{

	/** return id of place (Estate, office ... ) 
	 */
	public Integer getId();
	
	/** if image URL is null, default image will be created
	 */
	public String  getSmallImageUrl();
	
	/** return Title or Name of object 
	 */
	public String  getDisplayName();
	
	/** return additional information. 
	 * this information will be displayed in second row
	 */
	public String  getInfo();
	
}
