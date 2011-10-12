package com.dmma.base.gwt.client.presenter;


import com.dmma.base.gwt.client.event.AppEvent;

public interface IPresenter {
	
	/**
	 * get unique presenter identifier
	 * */
	public String getPresenterId();
	/**
	 * get presenter display 
	 **/
	public IPresenterDisplay getPresenterDisplay();
	
	/**
	 * apply new parameters (usually history parameters)
	 * */
	public abstract void applyNewParams(AppEvent e);
}
