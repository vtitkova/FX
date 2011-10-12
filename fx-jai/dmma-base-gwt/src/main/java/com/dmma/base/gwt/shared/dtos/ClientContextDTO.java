package com.dmma.base.gwt.shared.dtos;

import java.util.Date;
import java.util.HashMap;
import java.util.Set;

import com.google.gwt.user.client.rpc.IsSerializable;

public class ClientContextDTO implements IsSerializable {
	public static final String APP_MODEL =  "APP_MODEL";
	
	/** is application model synchronized with server */
	private Boolean isSynchronised; 
	private Date   created; 
	
	
	private HashMap<String, String> params;
	
	
	public ClientContextDTO() {
		setIsSynchronised(false);
		setCreated(new Date());
	}

	public void setIsSynchronised(Boolean isSynchronised) {
		this.isSynchronised = isSynchronised;
	}

	public Boolean getIsSynchronised() {
		return isSynchronised;
	}
	
	/**
	 * put parameter 
	 */
	public void addParam(String name, String value) {
		if (params == null)
			params = new HashMap<String, String>();
		if (value == null)
			params.remove(name);
		else {
			params.put(name, value);
		}
	}

	/**
	 * Get event parameter as <code>java.lang.Integer</code>
	 * @param name parameter name to get
	 * @return Integer or null if parameter not found or failed to cast 
	 */
	public Integer getParamAsInteger(String name) {
		if (params != null) {
			String paramObj = params.get(name);
			if (paramObj != null) {
				try {
					Integer intParam = new Integer(paramObj);
					return intParam;
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
			String paramObj = params.get(name);
			if (paramObj != null)
				return paramObj;
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

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getCreated() {
		return created;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(APP_MODEL).append("<br>");
		sb.append("created:").append(getCreated()).append("<br>");
		params.keySet();
		
		for(String key: params.keySet()){
			sb.append(key).append(": ").append(getParamAsString(key)).append("<br>");
		}
		return sb.toString();
	}
}
