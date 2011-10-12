package com.dmma.base.gwt.client.ui.loader.presenter;

import com.dmma.base.gwt.client.event.AppEvent;
import com.dmma.base.gwt.client.presenter.Presenter;
import com.dmma.base.gwt.client.ui.loader.presenter.ApplicationLoadingDisplay;
import com.google.gwt.user.client.ui.HasWidgets;

public class ApplicationLoadingPresenter implements Presenter {
	public static String PRESENTER_ID = "AppModelLoding";

	private final ApplicationLoadingDisplay display;
	
	public ApplicationLoadingPresenter(ApplicationLoadingDisplay display) {
		this.display = display;
	}
	

	@Override
	public void applyNewParams(AppEvent e) {
	}

	@Override
	public String getPresenterId() {
		return PRESENTER_ID;
	}

	@Override
	public void repaintWidget(HasWidgets container) {
		container.clear();
		container.add(display.asWidget());
	}
	
}
