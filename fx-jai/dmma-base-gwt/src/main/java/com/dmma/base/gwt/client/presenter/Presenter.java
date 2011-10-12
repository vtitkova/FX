package com.dmma.base.gwt.client.presenter;


import com.dmma.base.gwt.client.event.AppEvent;
import com.google.gwt.user.client.ui.HasWidgets;

@Deprecated
public interface Presenter {
	
	/** returns unique presenter identifier */
	public abstract String getPresenterId();
	/** repaint current active widget with widget from presenter */
	public abstract void repaintWidget(final HasWidgets hasWidgetContainer);
	/** apply new parameters (usually history parameters)*/
	public abstract void applyNewParams(AppEvent e);
}
