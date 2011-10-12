package com.dmma.base.gwt.client.event;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class AppEvent implements Cloneable {
	private String key;
	private String sender = "unknown";  
	private HashMap<String, Object> params;
	
	/**
	 * use MyEvent(String key, String sender)
	*/
	@Deprecated 
	public AppEvent(String key) {
		this.key = key;
	}
	
	/**
	 * as sender could use this.getClass().getName()
	*/
	public AppEvent(String key, String sender) {
		this.key = key;
		this.sender = sender;
	}
	
	public String getKey() {
		return key;
	}
		
	public void addParam(String name, Object value) {
		if (params == null)
			params = new HashMap<String, Object>();
			
		if (value == null)
			params.remove(name);
		else {
			params.put(name, value);
		}
	}
	
	public void addParam(String name, Date value) {
		if (params == null)
			params = new HashMap<String, Object>();
			
		if (value == null)
			params.remove(name);
		else {
			params.put(name, value.getTime());
		}
	}
	

	/**
	 * Get event parameter
	 * @param name
	 * @return parameter or null, if not found
	 */
	@SuppressWarnings("unchecked")
	public <T> T getParam(String name) {
		if (params != null)
			try {
				return (T) params.get(name);
			}
			catch (ClassCastException ex) {
				return null;
			}
		else
			return null;
	}
	
	/**
	 * Get event parameter as <code>java.lang.Integer</code>
	 * @param name parameter name to get
	 * @return Integer or null if parameter not found or failed to cast 
	 */
	public Integer getParamAsInteger(String name) {
		if (params != null) {
			Object paramObj = params.get(name);
			if (paramObj != null) {
				try {
					Integer intParam = new Integer(paramObj.toString());
					return intParam;
				}
				catch (NumberFormatException ex) {
				}
			}
		}
		
		
		return null;
	}

	public Long getParamAsLong(String name) {
		if (params != null) {
			Object paramObj = params.get(name);
			if (paramObj != null) {
				try {
					Long longParam = new Long(paramObj.toString());
					return longParam;
				}
				catch (NumberFormatException ex) {
				}
			}
		}
		return null;
	}
	
	
	/**
	 * Get event parameter as <code>java.util.Date</code>
	 * @param name parameter name to get
	 * @return Date or null if parameter not found 
	 */
	public Date getParamAsDate(String name) {
		if (params != null) {
			Object paramObj = params.get(name);
			if (paramObj != null) {
				try {
					Long intParam = new Long(paramObj.toString());
					return new Date(intParam);
				}
				catch (NumberFormatException ex) {
				}
			}
		}
		
		
		return null;
	}
	
	
	/**
	 * Get event parameter as <code>java.lang.String</code>
	 * @param name parameter name to get
	 * @return Integer or null if parameter not found 
	 */
	public String getParamAsString(String name) {
		if (params != null) {
			Object paramObj = params.get(name);
			if (paramObj != null)
				return paramObj.toString();
		}
		
		return null;
	}
	
	
	/**
	 * Get event parameter as <code>java.lang.Boolean</code>
	 * @param name parameter name to get
	 * @return Integer or null if parameter not found 
	 */
	public Boolean getParamAsBoolean(String name) {
		if (params != null) {
			Object paramObj = params.get(name);
			if (paramObj != null) {
				try {
					return Boolean.parseBoolean(paramObj.toString());
				}
				catch (NumberFormatException ex) {
				}
			}
		}
		return null;
	}
	
	public int getParamCount() {
		if (params != null)
			return params.size();
		else
			return 0;
	}
	
	/**
	 * 
	 * @return list with parameter names 
	 */
	public Set<String> getParamNames() {
		if (params != null)
			return params.keySet();
		else
			return null;
	}
	
	public AppEvent clone() {
		AppEvent event = new AppEvent(this.getKey(), this.getSender());
		Set<String> paramNames = this.getParamNames();
		if (paramNames != null) {
			Iterator<String> keyIter = paramNames.iterator();
			while (keyIter.hasNext()) {
				String key = keyIter.next().toString();
				event.addParam(key, getParam(key));
			}
		}	
		return event; 
	}
	
	
	public void changeKey(String newKey){
		key = newKey;
	}
	
	
	public void clearParams(){
		if (params != null)
			params.clear();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof AppEvent)
			return URLParser.toHistoryToken(this).equals(URLParser.toHistoryToken((AppEvent) obj));
		return false;
	}
	@Override
	public int hashCode() {
		return URLParser.toHistoryToken(this).hashCode();
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getSender() {
		return sender;
	}

	public boolean isValid(){
		return getKey() != null && getKey().length() > 0;
	}
}