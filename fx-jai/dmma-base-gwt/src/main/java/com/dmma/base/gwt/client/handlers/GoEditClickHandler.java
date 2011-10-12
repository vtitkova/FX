package com.dmma.base.gwt.client.handlers;

import com.dmma.base.gwt.client.event.AppEvent;
import com.dmma.base.gwt.client.event.AppEventManager;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public class GoEditClickHandler implements ClickHandler{
	private AppEvent event;

	public GoEditClickHandler(AppEvent event) {
		this.event = event;
	}

	@Override
	public void onClick(ClickEvent clickEvent) {
		if(event != null && event.isValid())
			AppEventManager.get().changeHistory(event);
	}
}