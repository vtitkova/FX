package com.dmma.base.gwt.client.ui_sample;

import com.dmma.base.gwt.client.presenter.IPresenterDisplay;
import com.dmma.base.gwt.client.utils.BaseStringUtils;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

public final class PresenterView extends Composite {
	interface DefUiBinder extends UiBinder<Widget, PresenterView> {}
	private static DefUiBinder defBinder = GWT.create(DefUiBinder.class);
	
	@UiField
	protected HTML captionHT;
	
	@UiField
	protected FlowPanel contentPanel;
	
	public PresenterView() {
		this.initWidget(defBinder.createAndBindUi(PresenterView.this));
		repaint(null);
	}

	public void repaint(IPresenterDisplay presenterDisplay) {
		captionHT.setHTML("");
		contentPanel.clear();
		if(presenterDisplay!=null){
			if (!GWT.isScript()){
				String view = BaseStringUtils.getClassShortName(presenterDisplay.getClass().getName());
				String presenter = view.replace("View","Presenter");
				captionHT.setHTML(presenterDisplay.getCaption()+" - "+view+" "+presenter);
			}else
				captionHT.setHTML(presenterDisplay.getCaption());
			contentPanel.add(presenterDisplay.asWidget());
		}
	}
}
