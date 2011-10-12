package com.dmma.fxjai.web.gwt.admin.client;

import com.dmma.base.gwt.client.app.entrypoint.BaseEntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

public class AdminEP extends BaseEntryPoint{
	@Override
	public void onModuleAndContextLoaded(RootPanel rootPanel) {
		new AdminMainController(rootPanel);
	}
}
