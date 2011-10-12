package com.dmma.base.gwt.client.mail.meta;


import java.util.Date;

import com.dmma.base.gwt.client.resources.i18n.BaseMessages;
import com.dmma.base.gwt.client.ui.gwtentity.column.GwtColumnMetadata;
import com.dmma.base.gwt.client.ui.gwtentity.column.IGwtEntityCM;
import com.dmma.base.gwt.shared.entities.MailDTO;
import com.dmma.base.shared.shared.clazz.DateTime;

public class MailCM implements IGwtEntityCM<MailDTO>{
	private final GwtColumnMetadata idColumn;
	private final GwtColumnMetadata statusColumn;
	private final GwtColumnMetadata templateNameColumn;
	private final GwtColumnMetadata mailToColumn;
	private final GwtColumnMetadata subjectColumn;
	private final GwtColumnMetadata messageColumn;
	private final GwtColumnMetadata createdColumn;
	private final GwtColumnMetadata sentColumn;
		
	
	
	public MailCM() {
		idColumn     = new GwtColumnMetadata(BaseMessages.MSG.id(),                  Integer.class, 20);
		statusColumn = new GwtColumnMetadata("&nbsp;",                               Integer.class, 21);
		templateNameColumn  = new GwtColumnMetadata(BaseMessages.MSG.mailTemplate(), String.class, 100);
		mailToColumn        = new GwtColumnMetadata(BaseMessages.MSG.to(),           String.class, 120);
		subjectColumn       = new GwtColumnMetadata(BaseMessages.MSG.mailSubject(),  String.class,  -1);
		messageColumn       = new GwtColumnMetadata(BaseMessages.MSG.mailText(),     String.class,  -1);
		createdColumn       = new GwtColumnMetadata(BaseMessages.MSG.created(),      Date.class,70);
		sentColumn          = new GwtColumnMetadata(BaseMessages.MSG.sent(),         DateTime.class,105);
 	}
	
	@Override
	public GwtColumnMetadata[] getColumnMeta(){
		return new GwtColumnMetadata[]{ idColumn, statusColumn, templateNameColumn,
		  mailToColumn, subjectColumn, messageColumn, createdColumn, sentColumn};
		
	}

	@Override
	public Object getValueAt(MailDTO entity, int index) {
		switch(index){
		case 0: return entity.getId();
		case 1: return entity.getStatus();
		case 2: return entity.getTemplateName();
		case 3: return entity.getMailTo();
		case 4: return entity.getSubject();
		case 5: return entity.getMessage();
		case 6: return entity.getCreated();
		case 7: return entity.getSent();
		}
		return null;
	}

	@Override
	public Object getId(MailDTO entity) {
		return entity.getId();
	}

	@Override
	public void setValueAt(MailDTO entity, int index, Object value) {
		// TODO Auto-generated method stub
	}
}
