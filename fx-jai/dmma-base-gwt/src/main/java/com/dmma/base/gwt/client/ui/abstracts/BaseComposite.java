package com.dmma.base.gwt.client.ui.abstracts;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

public class BaseComposite extends Composite{
	protected FlowPanel mainPanel;

	public BaseComposite(String styleName) {
		super();
		mainPanel = new FlowPanel();
		if(styleName!=null&&styleName.length()>0)
			mainPanel.setStyleName(styleName);
		initWidget(mainPanel);
	}

	@Override
	public void initWidget(Widget widget) {
		super.initWidget(widget);

		/* Show class name tool tip in hosted mode browser */
		if (!GWT.isScript())
			widget.setTitle(this.getClass().getName());
	}

	
	public void add(Widget widget) {
		if (mainPanel != null)
			mainPanel.add(widget);
	}

	public void remove(Widget widget) {
		if (mainPanel != null)
			mainPanel.remove(widget);
	}

	@Override
	public Widget asWidget() {
		return this;
	}
	
	@Override
	public void setStyleName(String style) {
		mainPanel.setStyleName(style);
	}
}
