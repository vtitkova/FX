package com.dmma.base.gwt.client.ui.estateInfo;


import com.dmma.base.gwt.shared.interfaces.PlaceInfoSmall;
import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.UIObject;

public class PlaceInfoSmallPopup{
	private PopupPanel popupPanel;

	private PlaceInfoSmallPopup(PlaceInfoSmall info) {
		popupPanel = new PopupPanel();
		popupPanel.setAutoHideEnabled(true);
		if (!GWT.isScript())
			popupPanel.setTitle(this.getClass().getName());

		SafeHtmlBuilder sb = new SafeHtmlBuilder();
		new PlaceCellSmall().render(null, info, sb);
		popupPanel.add(new HTML(sb.toSafeHtml()));
	}
	
	public void showRelativeTo(UIObject target){
		popupPanel.showRelativeTo(target);
	}
	private void center() {
		popupPanel.center();
	}
	
	public static void show(PlaceInfoSmall info, UIObject target){
		PlaceInfoSmallPopup instance = new PlaceInfoSmallPopup(info);
		if(target!=null)
			instance.showRelativeTo(target);
		else
			instance.center();
	}

	

}
