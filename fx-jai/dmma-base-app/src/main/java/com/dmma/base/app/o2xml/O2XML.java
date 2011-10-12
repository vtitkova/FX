package com.dmma.base.app.o2xml;

import java.io.OutputStream;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;

import com.dmma.base.app.o2xml.core.SAXSourceFactory;

public class O2XML {
	private TransformerFactory transformerFactory;
	
	public O2XML() {
		transformerFactory = TransformerFactory.newInstance();
	}
	
	/*public void writeObjectToXML(Object objSource, File xmlOutput){
		Transformer transformer = null;
		try {
			transformer = transformerFactory.newTransformer();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		}
		Source src = SAXSourceFactory.createSAXSourceForObject(objSource);
		Result res = new StreamResult(xmlOutput);
		try {
			transformer.transform(src, res);
		} catch (TransformerException e) {
			e.printStackTrace();
		} 

	}*/

	public void writeObjectToXMLOutputStream(Object objSource, OutputStream outputStream) {
		Transformer transformer = null;
		try {
			transformer = transformerFactory.newTransformer();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		}
		Source src = SAXSourceFactory.createSAXSourceForObject(objSource);
		Result res = new StreamResult(outputStream);
		try {
			transformer.transform(src, res);
		} catch (TransformerException e) {
			e.printStackTrace();
		} 

		
	}
}
