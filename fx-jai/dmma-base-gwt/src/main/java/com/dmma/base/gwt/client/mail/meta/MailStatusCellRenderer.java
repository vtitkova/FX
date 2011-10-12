package com.dmma.base.gwt.client.mail.meta;

import com.dmma.base.gwt.client.resources.img.BaseImages;
import com.dmma.base.gwt.client.ui.etf.utils.EtfTableCellRendererInterface;
import com.dmma.base.gwt.shared.entities.MailDTO;
import com.dmma.base.shared.shared.mail.types.MailStatusType;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.AbstractImagePrototype;

public class MailStatusCellRenderer implements EtfTableCellRendererInterface<MailDTO> {
	private static final String imageIsNewHtml;
	private static final String imageIsSentHtml;
	private static final String imageIsFailedHtml;
	
	private static final ImageResource imageIsNew = BaseImages.IMG.indifferentSmall();
	private static final ImageResource imageIsSent = BaseImages.IMG.goodSmall();
	private static final ImageResource imageIsFailed = BaseImages.IMG.badSmall();
	
	static {
		imageIsNewHtml  = AbstractImagePrototype.create(BaseImages.IMG.indifferentSmall()).getHTML();
		imageIsSentHtml = AbstractImagePrototype.create(BaseImages.IMG.goodSmall()).getHTML();
		imageIsFailedHtml = AbstractImagePrototype.create(BaseImages.IMG.badSmall()).getHTML();
	}
	
	@Override
	public String getHTML(MailDTO entity) {
		Integer status = entity.getStatus();
		if(MailStatusType.isNew.getId().equals(status)){
			return imageIsNewHtml;
		} 
		if(MailStatusType.isSent.getId().equals(status)){
			return imageIsSentHtml;
		}
		if(MailStatusType.isFailed.getId().equals(status)){
			return imageIsFailedHtml;
		}
		return null;
	}

}
