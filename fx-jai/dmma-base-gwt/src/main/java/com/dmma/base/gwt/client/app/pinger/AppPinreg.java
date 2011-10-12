package com.dmma.base.gwt.client.app.pinger;

import com.dmma.base.gwt.client.resources.i18n.BaseMessages;
import com.dmma.base.gwt.client.services.factory.BaseServiceFactory;
import com.dmma.base.gwt.client.ui.dialog.AppDialog;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * in order to use AppPinger, simply call it's instance once 
 * For example, onModuleLoad(), insert AppPinreg.get();
 *
 * */

public class AppPinreg {
	private static AppPinreg INSTANCE;
	

	public AppPinreg() {
		Timer timer = new Timer() {
			@Override
			public void run() {
				BaseServiceFactory.getClientContextService().pingSession(pingCallback);
			}
		};
		timer.scheduleRepeating(20*60*1000);
		timer.run();
	}

	private PingCallback pingCallback = new PingCallback(); 
	private class PingCallback implements AsyncCallback<Boolean>{
		@Override
		public void onFailure(Throwable caught) {
			AppDialog.show(BaseMessages.MSG.appPingerError(), AppDialog.ERROR_MESSAGE);
		}

		@Override
		public void onSuccess(Boolean result) {
			if(!result)
				onFailure(null);
		}
	}
	
	public static AppPinreg get(){
		if(INSTANCE==null)
			INSTANCE = new AppPinreg();
		return INSTANCE;
	}

}
