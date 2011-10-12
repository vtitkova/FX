package com.dmma.base.gwt.client.ui.menu;

import java.util.ArrayList;

import com.dmma.base.gwt.client.event.AppEvent;
import com.google.gwt.resources.client.ImageResource;

public class MenuEntry{
	private final String        title;
	private final ImageResource imageResource;
	private ArrayList<MenuItem> items;
	
	public MenuEntry(String title) {
		this(title, null);
	}
	
	public MenuEntry(String title, ImageResource icon) {
		this.title = title;
		this.imageResource = icon;
	}

	public String getTitle() {
		return title;
	}

	public ImageResource getImageResource() {
		return imageResource;
	}

	public ArrayList<MenuItem> getItems() {
		return items;
	}

	public void addItem(MenuItem item) {
		if(items == null)
			items = new ArrayList<MenuItem>(1);
		items.add(item);
	}
	
	public void addItem(String itemTitle, String presenterId){
		AppEvent event = new AppEvent(presenterId, this.getClass().getName());
		MenuItem item = new MenuItem(itemTitle, event);
		addItem(item);
	}
	
	
}
