package com.dmma.base.gwt.client.utils;


public class BaseStringUtils {


	public static boolean isEmpty(String str){
		if(str==null) return true;
		return str.length() == 0;
	}

	
	/**
	 * if input = com.thehutgroup.wms.base.client.utils.AppUtils 
	 * then output  =  AppUtils
	 */
	public static String getClassShortName(String string) {
		String ret = string.substring(string.indexOf(".")+1, string.length());
		if(string.indexOf(".")>-1)
			ret = getClassShortName(ret);
		return ret;
	}
	
	
	public static String getResizedString(String source, int lenght){
		return getResizedString(source, lenght, false, false, " ");
	}

	public static String getResizedString(String source, int lenght, boolean before, boolean trunkAlowed, String insertChar){
		if(source==null) source = "";
		if(insertChar==null) insertChar = " ";
		if(lenght<1) return "";
		if(lenght==source.length()) return source;
		if(lenght>source.length()){
			StringBuilder b = new StringBuilder(source);
			while(b.length()<lenght){
				if(before) b.insert(0, insertChar);
				else b.append(insertChar);
			}
			source = b.toString();
		}else{
			if(trunkAlowed)
				if(before)source = source.substring(source.length()-lenght,source.length());
				else source = source.substring(0, lenght);
		}
		return source;
	}

}


