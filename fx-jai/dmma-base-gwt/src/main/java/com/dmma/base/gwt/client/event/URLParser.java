package com.dmma.base.gwt.client.event;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class URLParser {
	
	public static final String PARAMETER_PART_SPLITTER     = "!";
	public static final String MULTIPLE_PARAMETER_SPLITTER = "&";	
	public static final String PARAMETER_VALUE_SPLITTER    = "=";
	public static final String MULTIPLE_VALUES_SPLITTER    = ";";
	
	
	private URLParser() {
	}
	
	
	/**
	 * Create ApplicationEvent instance from it's string representation
	 * @param fullHistoryToken History token string containing mandatory base token 
	 * 	 and not mandatory parameter part
	 * @return Application event with parsed event key and parameters 
	 */
	public static AppEvent parse(String fullHistoryToken) {
		AppEvent event = null;
		
		int index = fullHistoryToken.indexOf(PARAMETER_PART_SPLITTER);
		//URL has parameters
		if (index != -1) {
			event = new AppEvent(fullHistoryToken.substring(0, index), URLParser.class.getName());
			
			String paramPart = fullHistoryToken.substring(index + 1);
			String[] paramStrings = paramPart.split(MULTIPLE_PARAMETER_SPLITTER);
			
			//parse parameters names, values
			for (int i  = 0; i < paramStrings.length; i++) {
				int splitIndex = paramStrings[i].indexOf(PARAMETER_VALUE_SPLITTER);
				if (splitIndex != -1) {
					String name = paramStrings[i].substring(0, splitIndex);
					String value = paramStrings[i].substring(splitIndex + 1);
				
					if (value.contains(MULTIPLE_VALUES_SPLITTER))
						event.addParam(name, stringToCollectionParam(value));
					else 
						event.addParam(name, value);
				}
			}
		}
		else
		//no parameters
			event = new AppEvent(fullHistoryToken, URLParser.class.getName()); 
		return event;
	}
	
	
	/**
	 * Converts application event to history token (external) form
	 * @param event event to be converted
	 * @return application event history token form
	 */
	public static String toHistoryToken(AppEvent event) {
		if (event == null)
			return "";
		StringBuffer fullHistoryToken = new StringBuffer(event.getKey());
		
		if (event.getParamCount() > 0) {
			fullHistoryToken.append(PARAMETER_PART_SPLITTER);
			
			Set<String> names = event.getParamNames();
			Iterator<String> namesIter = names.iterator();
			
			for (int i = 0; i < event.getParamCount() - 1; i++) {
				String name = namesIter.next().toString();
				fullHistoryToken.append(createParamNameValuePair(name, event.getParam(name)));
				fullHistoryToken.append(MULTIPLE_PARAMETER_SPLITTER);
			}
			String name = namesIter.next().toString();
			fullHistoryToken.append(createParamNameValuePair(name, event.getParam(name)));
		}
			
		return fullHistoryToken.toString();
	}
	
	
	/**
	 * @param name parameter name
	 * @param value parameter value
	 * @return parameter name and value textual representation 
	 */
	@SuppressWarnings("unchecked")
	private static StringBuffer createParamNameValuePair(String name, Object value) {
		StringBuffer result = new StringBuffer();
		result.append(name);
		result.append(PARAMETER_VALUE_SPLITTER);
		if (value instanceof Collection)
			result.append(collectianParamToString((Collection<java.io.Serializable>) value));
		else 
			result.append(value);
		return result;
	}
	
	
	private static String collectianParamToString(Collection<java.io.Serializable> value){
		if ( value == null )
			return "";
		
		String result = value.toString();
		if (result.length() < 2 )
			return "";
		
		result = result.substring(1, result.length() - 1);
		
		result = result.replaceAll(",", MULTIPLE_VALUES_SPLITTER);
		
		result = result.replaceAll(" ", "");
		
		return result; 
	}
	
	
	private static ArrayList<java.io.Serializable> stringToCollectionParam (String  value ){
		String [] tokenArray = value.split(MULTIPLE_VALUES_SPLITTER);		
		
		ArrayList<Serializable> result = new ArrayList<Serializable>();
		for (int i = 0; i < tokenArray.length; i ++){
			String token = tokenArray[i];
			result.add(token);
		}
		
		return result; 
	}
	
}
