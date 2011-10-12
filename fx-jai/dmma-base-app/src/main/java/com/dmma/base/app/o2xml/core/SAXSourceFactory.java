package com.dmma.base.app.o2xml.core;

import javax.xml.transform.Source;
import javax.xml.transform.sax.SAXSource;


public class SAXSourceFactory {
	
	 public static Source createSAXSourceForObject(Object sourceObj) {
		 SAXSource src = new SAXSource(new ObjectXMLReader(), new  ObjectInputSource(sourceObj));
		 return src;
	 }	
}
