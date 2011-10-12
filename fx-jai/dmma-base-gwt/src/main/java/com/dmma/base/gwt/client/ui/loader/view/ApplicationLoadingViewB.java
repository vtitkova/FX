package com.dmma.base.gwt.client.ui.loader.view;

import com.dmma.base.gwt.client.ui.loader.presenter.ApplicationLoadingDisplay;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

public class ApplicationLoadingViewB extends Composite implements ApplicationLoadingDisplay{
	interface MyUiBinder extends UiBinder<Widget, ApplicationLoadingViewB> {}
	private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);
	
	
	@UiField 
	HTML captionMsg;
	
	@UiField 
	HTML aditionalMsg;
	
	public ApplicationLoadingViewB(){
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	public void setCaptionMsg(String msg){		
		captionMsg.setHTML(msg);
	}
	
	public void setMsg(String msg){		
		aditionalMsg.setHTML(msg);
	}
	
	
	
}
