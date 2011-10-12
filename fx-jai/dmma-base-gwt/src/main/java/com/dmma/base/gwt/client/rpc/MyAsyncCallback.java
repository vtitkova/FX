package com.dmma.base.gwt.client.rpc;

import com.dmma.base.gwt.client.resources.i18n.BaseMessages;
import com.dmma.base.gwt.client.ui.dialog.AppDialog;
import com.dmma.base.gwt.shared.errors.MethodPermissionGError;
import com.dmma.base.gwt.shared.errors.ObjectNotExistGError;
import com.dmma.base.gwt.shared.errors.ObjectPermissionGError;
import com.google.gwt.user.client.rpc.AsyncCallback;

public abstract class MyAsyncCallback<T> implements AsyncCallback<T>{

	@Override
	public void onFailure(Throwable caught) {
		if(caught!=null && caught instanceof MethodPermissionGError){
			AppDialog.show(BaseMessages.MSG.methodPermissionGError(), AppDialog.ERROR_MESSAGE);
		}else if(caught!=null && caught instanceof ObjectPermissionGError){
			AppDialog.show(BaseMessages.MSG.objectPermissionGError(), AppDialog.ERROR_MESSAGE);
		}else if(caught!=null && caught instanceof ObjectNotExistGError){
			AppDialog.show(BaseMessages.MSG.objectNotExistGError(), AppDialog.ERROR_MESSAGE);
		}else{			
			onnFailure(caught);
		}
		afterFailure();
	}

	/** this is default behavior after some failure, 
	 * maybe you will want to redirect 
	 * */
	protected void afterFailure(){
		
	}
	
	/** this is default behavior, 
	 * but you can override this method to handle custom reaction 
	 * */
	protected void onnFailure(Throwable caught){
		AppDialog.show(BaseMessages.MSG.applicationError(), AppDialog.ERROR_MESSAGE);
	}
	

}
