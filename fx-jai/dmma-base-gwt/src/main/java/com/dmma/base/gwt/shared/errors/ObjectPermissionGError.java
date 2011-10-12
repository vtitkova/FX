package com.dmma.base.gwt.shared.errors;

import java.io.Serializable;

/** 
 * Throw this Error if client have no permission 
 * to requested object.
 * 
 * Example: broker request estate that not belong to him.
 * 
 * @author Dima
 * */
public class ObjectPermissionGError  extends Exception implements Serializable {
	private static final long serialVersionUID = 4773228066703410973L;
	public ObjectPermissionGError() {
	}
}
