package com.dmma.base.gwt.shared.errors;

import java.io.Serializable;

/** 
 * Throw this Error if client have no permission 
 * to execute this method.
 * 
 * @author Dima
 * */
public class MethodPermissionGError  extends Exception implements Serializable {
	private static final long serialVersionUID = -3567734238133457261L;
	public MethodPermissionGError() {
	}
}
