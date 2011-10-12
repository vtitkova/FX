package com.dmma.base.gwt.client.ui.menu;

import com.dmma.base.gwt.client.event.AppEvent;
import com.google.gwt.resources.client.ImageResource;


public class MenuItem {
	private final String        title;
	private final ImageResource imageResource;
	private final AppEvent      event;

	public MenuItem(String title, AppEvent event) {
		this(title, null, event);
	}
	
	public MenuItem(String title, ImageResource icon, AppEvent event) {
		this.title = title;
		this.imageResource = icon;
		this.event = event;
	}

	public String getTitle() {
		return title;
	}

	public ImageResource getImageResource() {
		return imageResource;
	}

	public AppEvent getEvent() {
		return event;
	}
	

}
