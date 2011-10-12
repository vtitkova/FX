package com.dmma.base.gwt.client.ui.contactInfo;


import com.dmma.base.gwt.shared.interfaces.ContactInfoBig;
import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.UIObject;

public class ContactInfoBigPopup{
	private PopupPanel popupPanel;

	public ContactInfoBigPopup(ContactInfoBig info) {
		popupPanel = new PopupPanel();
		popupPanel.setAutoHideEnabled(true);
		if (!GWT.isScript())
			popupPanel.setTitle(this.getClass().getName());
		SafeHtmlBuilder sb = new SafeHtmlBuilder();
		new ContactCellBig().render(null, info, sb);
		popupPanel.add(new HTML(sb.toSafeHtml()));
		
	}

	public void showRelativeTo(UIObject target){
		popupPanel.showRelativeTo(target);
	}

	public static void show(ContactInfoBig info, UIObject target){
		ContactInfoBigPopup instance = new ContactInfoBigPopup(info);
		instance.showRelativeTo(target);
	}

}
