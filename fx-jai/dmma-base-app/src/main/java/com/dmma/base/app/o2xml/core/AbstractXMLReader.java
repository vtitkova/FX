package com.dmma.base.app.o2xml.core;


import java.io.IOException;
import java.util.Map;

import org.xml.sax.ContentHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;



/**
 * This class can be used as base class for XMLReaders that generate SAX 
 * events from Java objects.
 */

public abstract class AbstractXMLReader implements XMLReader {
    private static final String NAMESPACES  = "http://xml.org/sax/features/namespaces";
    private static final String NS_PREFIXES = "http://xml.org/sax/features/namespace-prefixes";
        
    private Map<String, Boolean> features = new java.util.HashMap<String, Boolean>();
    private   ContentHandler contentHandler;
    protected EasyGenerationContentHandlerProxy handler;
    protected ErrorHandler errorHandler;


    /**
     * Constructor for the AbstractObjectReader object
     */
    public AbstractXMLReader() {
        setFeature(NAMESPACES, false);
        setFeature(NS_PREFIXES, false);
    }
    
    /* ============ XMLReader interface ============ */

    /**
     * @see org.xml.sax.XMLReader#getContentHandler()
     */
    public ContentHandler getContentHandler() {
        return this.contentHandler;
    }

    /**
     * @see org.xml.sax.XMLReader#setContentHandler(ContentHandler)
     */
    public void setContentHandler(ContentHandler handler) {
        this.contentHandler = handler;
        this.handler = new EasyGenerationContentHandlerProxy(handler);
    }

    /**
     * @see org.xml.sax.XMLReader#getErrorHandler()
     */
    public ErrorHandler getErrorHandler() {
        return this.errorHandler;
    }

    /**
     * @see org.xml.sax.XMLReader#setErrorHandler(ErrorHandler)
     */
    public void setErrorHandler(ErrorHandler handler) {
        this.errorHandler = handler;
    }

    /**
     * @see org.xml.sax.XMLReader#getDTDHandler()
     */
    public DTDHandler getDTDHandler() {
        return null;
    }

    /**
     * @see org.xml.sax.XMLReader#setDTDHandler(DTDHandler)
     */
    public void setDTDHandler(DTDHandler handler) {
    }

    /**
     * @see org.xml.sax.XMLReader#getEntityResolver()
     */
    public EntityResolver getEntityResolver() {
        return null;
    }

    /**
     * @see org.xml.sax.XMLReader#setEntityResolver(EntityResolver)
     */
    public void setEntityResolver(EntityResolver resolver) {
    }

    /**
     * @see org.xml.sax.XMLReader#getProperty(String)
     */
    public Object getProperty(java.lang.String name) {
        return null;
    }

    /**
     * @see org.xml.sax.XMLReader#setProperty(String, Object)
     */
    public void setProperty(java.lang.String name, java.lang.Object value) {
    }

    /**
     * @see org.xml.sax.XMLReader#getFeature(String)
     */
    public boolean getFeature(java.lang.String name) {
        return features.get(name).booleanValue();
    }

    /**
     * Returns true if the NAMESPACES feature is enabled.
     * @return boolean true if enabled
     */
    protected boolean isNamespaces() {
        return getFeature(NAMESPACES);
    }

    /**
     * Returns true if the MS_PREFIXES feature is enabled.
     * @return boolean true if enabled
     */
    protected boolean isNamespacePrefixes() {
        return getFeature(NS_PREFIXES);
    }

    /**
     * @see org.xml.sax.XMLReader#setFeature(String, boolean)
     */
    public void setFeature(String name, boolean value) {
        this.features.put(name, new Boolean(value));
    }

    /**
     * @see org.xml.sax.XMLReader#parse(String)
     */
    public void parse(String systemId) throws IOException, SAXException {
        throw new SAXException(
            this.getClass().getName()
                + " cannot be used with system identifiers (URIs)");
    }

    /**
     * @see org.xml.sax.XMLReader#parse(InputSource)
     */
    
    @Override
	public void parse(InputSource input) throws IOException, SAXException {
		if (input instanceof ObjectInputSource) {
            parse(((ObjectInputSource)input).getObjectToConvert());
        } else {
            throw new SAXException("Unsupported InputSource specified. "
                    + "Must be a ProjectTeamInputSource");
        }
		
	}
    
    
	private void parse(Object objectToConvert) throws SAXException {
		if (objectToConvert == null) {
			throw new NullPointerException("Parameter projectTeam must not be null");
		}
		if (handler == null) {
			throw new IllegalStateException("ContentHandler not set");
		}

		//Start the document
		handler.startDocument();

		if (handler == null) {
			throw new IllegalStateException("ContentHandler not set");
		}
		
		generateForObject(objectToConvert, null, null);

		//End the document
		handler.endDocument();        
	}
    
	protected abstract void generateForObject(Object objectToConvert, String tagName, String listChildNameInXML) throws SAXException ;
    
    
}
