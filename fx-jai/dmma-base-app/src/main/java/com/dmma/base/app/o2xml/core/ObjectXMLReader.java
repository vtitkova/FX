package com.dmma.base.app.o2xml.core;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

import org.xml.sax.SAXException;

import com.dmma.base.app.o2xml.annotations.O2XAttribute;
import com.dmma.base.app.o2xml.annotations.O2XElement;
import com.dmma.base.app.utils.BaseServerFormats;
import com.dmma.base.shared.shared.clazz.Currency;
import com.dmma.base.shared.shared.clazz.DateTime;
import com.dmma.base.shared.shared.clazz.Time;

public class ObjectXMLReader extends AbstractXMLReader {
	private static final Object[] EMPTY = {};


	/**
	 * Generate XML structure for Object*/
	@Override
	protected void generateForObject(Object objectToConvert, String tagName, String listChildNameInXML) throws SAXException {
		if(objectToConvert == null){
			addSimpleElement(null, tagName);	
			return;
		}


		O2XElement elementAnnotation = objectToConvert.getClass().getAnnotation(O2XElement.class);
		if(elementAnnotation != null){
			// ANOTADET ELEMENT - O2XElement !
			if(tagName == null || tagName.isEmpty()){
				tagName = elementAnnotation.nameInXml();
				if(tagName.isEmpty()){
					tagName = objectToConvert.getClass().getSimpleName();
				}	
			}

			handler.startElement(tagName);
			generateObjectContentAttributes(objectToConvert);
			handler.endElement(tagName);
		}else{
			// SIMPLE ELEMENT - List or Primitive
			if(objectToConvert instanceof java.util.List<?>){
				//List
				handler.startElement(tagName);

				for(Object o: (List<?>) objectToConvert ){
					generateForObject(o, listChildNameInXML, null);
				}
				handler.endElement(tagName);
			}else{
				//Primitive
				addSimpleElement(objectToConvert, tagName);
			}
		}
	}


	/**
	 * Adding simple element
	 *< tagName / > value  < / tagName >
	 * 
	 * */
	private void addSimpleElement(Object simpleElement, String tagName) throws SAXException {
		if(simpleElement == null && tagName == null)
			return;

		// if tag name is unknown, we will use object's class name
		if( tagName == null || tagName.length() < 1 )
			tagName = simpleElement.getClass().getSimpleName();

		handler.element(tagName, objectToString(simpleElement));
	}


	/**
	 * We are trying to convert any possible object to string
	 * */
	private String objectToString(Object object) {
		if(object==null) return "";
		if(object instanceof Integer)
			return object.toString();
		if(object instanceof DateTime)
			return BaseServerFormats.getFormattedDateTime((Date) object);
		if(object instanceof Time)
			return BaseServerFormats.getFormattedTime((Date) object);
		if(object instanceof Date)
			return BaseServerFormats.getFormattedDate((Date) object);
		if(object instanceof Currency)
			return BaseServerFormats.getFormattedCurrency((Currency) object);
		return object.toString();
	}




	private void generateObjectContentAttributes(Object objectToConvert) {
		for (Field field : objectToConvert.getClass().getDeclaredFields()){
			Boolean skip = false;
			String fieldName  = field.getName();
			String nameForXml = fieldName;
			String nameForListChild = null;

			O2XAttribute fieldAnnotation = field.getAnnotation(O2XAttribute.class);
			if(fieldAnnotation != null){
				skip = fieldAnnotation.skip();
				if(fieldAnnotation.nameInXML().length()>0)
					nameForXml   = fieldAnnotation.nameInXML();
				if(fieldAnnotation.listChildNameInXML().length()>0)
					nameForListChild   = fieldAnnotation.listChildNameInXML();
			}
			if(skip)
				continue;

			StringBuffer methodName = new StringBuffer("get");
			methodName.append(fieldName.substring(0,1).toUpperCase());
			methodName.append(fieldName.substring(1));

			try {
				Method m = objectToConvert.getClass().getMethod(methodName.toString());
				Object value = m.invoke(objectToConvert,EMPTY);
				generateForObject(value, nameForXml, nameForListChild);
			} catch (Exception e) {
				e.printStackTrace();
			}		
		}
	}
}