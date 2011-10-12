package com.dmma.base.gwt.client.utils;


import com.dmma.base.gwt.client.event.AppEvent;
import com.dmma.base.gwt.client.handlers.GoEditClickHandler;
import com.dmma.base.gwt.client.resources.css.BaseCssResource;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class BaseWidgetUtils {
	public static Widget createEditWidget(ImageResource editIcon, AppEvent params){
		if(editIcon!=null){
			Image img = new Image(editIcon);
			img.setStyleName(BaseCssResource.RES.style().actionWrap());
			img.addClickHandler(new GoEditClickHandler(params));
			return img;
		}else{
			Label l = new Label("edit");
			l.addClickHandler(new GoEditClickHandler(params));
			l.setStyleName(BaseCssResource.RES.style().actionWrap());
			return l;
		}
	}

	
}
