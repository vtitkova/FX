package com.dmma.base.gwt.shared.errors;

import java.io.Serializable;

/** 
 * Throw this Error if object not found 
 * usually when database entity is not existing by requested id
 * 
 * @author Dima
 * */
public class ObjectNotExistGError  extends Exception implements Serializable {
	private static final long serialVersionUID = 6219842821768227576L;
	public ObjectNotExistGError() {
	}
}
