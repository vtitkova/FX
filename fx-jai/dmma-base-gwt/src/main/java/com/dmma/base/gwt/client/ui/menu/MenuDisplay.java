package com.dmma.base.gwt.client.ui.menu;

import java.util.List;

import com.google.gwt.user.client.ui.Widget;

public interface MenuDisplay{
	public Widget asWidget();
	public void setMenuEntries(List<MenuEntry> entries);
}
