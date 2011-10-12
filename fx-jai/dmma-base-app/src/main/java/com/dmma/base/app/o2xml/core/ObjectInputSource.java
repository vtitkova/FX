package com.dmma.base.app.o2xml.core;

import org.xml.sax.InputSource;

/**
 * This class is a special InputSource for O2P annotated Object
 * instances as XML sources.
 */
public class ObjectInputSource extends InputSource {
	private Object objectToConvert;

	/** Create instance of  ObjectInputSource
	 * @param objectToConvert the target object. 
	 **/
	public ObjectInputSource(Object objectToConvert) {
		this.objectToConvert = objectToConvert;
	}

	/**
	 * Returns the object to be converted.
	 * @return objectToConvert
	 */
	public Object getObjectToConvert() {
		return objectToConvert;
	}
}
