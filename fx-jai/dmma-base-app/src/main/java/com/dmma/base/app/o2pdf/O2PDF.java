package com.dmma.base.app.o2pdf;

import java.io.File;
import java.io.OutputStream;
import java.util.HashMap;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;

import com.dmma.base.app.o2xml.core.SAXSourceFactory;

public class O2PDF {
	private TransformerFactory transformerFactory;
	
	public O2PDF() {
		transformerFactory = TransformerFactory.newInstance();
	}
	
	
	/*public boolean writeObjectToPdfFile(File pdfOutput , File xsltSource, Object objSource){
		OutputStream outputStream = null;
		try {
			outputStream = new java.io.FileOutputStream(pdfOutput);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			return false;
		}
		
		outputStream = new java.io.BufferedOutputStream(outputStream);
		writeObjectToPdfOutputStream(outputStream, xsltSource, objSource );
		try {
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	*/
	/*public byte[]  writeObjectToByteArray(File xsltSource, Object objSource){
		byte[] byteArray = null;
		
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		writeObjectToPdfOutputStream(outputStream, xsltSource, objSource );
		byteArray = outputStream.toByteArray();
		
		
		return byteArray;
		
	}*/


	public void writeObjectToPDFOutputStream(OutputStream outputStream, File xsltSource, Object  objSource){
		writeObjectToPDFOutputStream(outputStream, xsltSource, objSource, null);
	}
	
	public void writeObjectToPDFOutputStream(OutputStream outputStream, File xsltSource, Object  objSource, HashMap<String, String> aditionalParams){
		try {
			FopFactory  fopFactory  = FopFactory.newInstance();
			fopFactory.setUseCache(false);
			fopFactory.getImageManager().getCache().clearCache();
						
			FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
			Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, outputStream);
			
			// Setup XSLT
			Transformer transformer = transformerFactory.newTransformer(new StreamSource(xsltSource));
			if(aditionalParams!=null && !aditionalParams.isEmpty()){
				for(String key : aditionalParams.keySet()){
					String value = aditionalParams.get(key);
					transformer.setParameter(key,value);
				}
			}
			
			
			// Setup input for XSLT transformation
			Source src = SAXSourceFactory.createSAXSourceForObject(objSource);

			// Resulting SAX events (the generated FO) must be piped through to FOP
			Result res = new SAXResult(fop.getDefaultHandler());

			// Start XSLT transformation and FOP processing
			transformer.transform(src, res);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	

}
