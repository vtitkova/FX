package com.dmma.base.gwt.client.app.entrypoint;


import com.dmma.base.gwt.client.app.cc.ClientContextMng;
import com.dmma.base.gwt.client.app.pinger.AppPinreg;
import com.dmma.base.gwt.client.event.AppEvent;
import com.dmma.base.gwt.client.event.AppEventListener;
import com.dmma.base.gwt.client.event.AppEventManager;
import com.dmma.base.gwt.client.ui.dialog.AppDialog;
import com.dmma.base.gwt.client.ui.loader.presenter.ApplicationLoadingPresenter;
import com.dmma.base.gwt.client.ui.loader.view.ApplicationLoadingViewB;
import com.dmma.base.gwt.shared.AppVersion;
import com.dmma.base.gwt.shared.dtos.ClientContextDTO;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;


public abstract class BaseEntryPoint implements EntryPoint{
	public static final String MAINCONTENT = "maincontent";
	
	public void onModuleLoad() {
		AppPinreg.get();
		ApplicationLoadingPresenter applicationLoadingPresenter = new ApplicationLoadingPresenter(new ApplicationLoadingViewB());
		applicationLoadingPresenter.repaintWidget(RootPanel.get(MAINCONTENT));
		AppEventManager.get().addEventListener(ClientContextDTO.APP_MODEL, new AppEventListener() {
			@Override
			public void onEvent(AppEvent event) {
				doClientContextReceived();
			}
		});
		ClientContextMng.get();
	}
	
	public void doClientContextReceived() {
		String version = ClientContextMng.get().getParamAsString("AppVersion");
		if(version==null || !version.equals(AppVersion.APP_VERSION)){
			AppDialog.show("Application version is out of date... refresh your browser", AppDialog.ERROR_MESSAGE);
			return;
		}
		if(ClientContextMng.get().isSynchronised()){
			onModuleAndContextLoaded(RootPanel.get(MAINCONTENT));
		}else{
			AppEventManager.get().changeURL("login.jsp");
		}
	}

	public abstract void onModuleAndContextLoaded(RootPanel rootPanel);
}
