package com.dmma.base.gwt.shared.interfaces;



public interface PlaceInfoBig{

	/** return id of place (Estate, office ... ) 
	 */
	public Integer getId();
	
	/** if image URL is null, default image will be created
	 */
	public String  getBigImageUrl();
	
	/** return Title or Name of object 
	 */
	public String  getDisplayName();
	
	
	/** return list of additional information.  
	 *  this information will be subsequently displayed in rows
	 *  You can use safe HTML in return value
	 */
	public String[] getInfoList();
	
}
