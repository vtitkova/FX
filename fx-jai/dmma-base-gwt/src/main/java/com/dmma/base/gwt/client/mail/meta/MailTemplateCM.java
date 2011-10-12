package com.dmma.base.gwt.client.mail.meta;


import com.dmma.base.gwt.client.resources.i18n.BaseMessages;
import com.dmma.base.gwt.client.ui.gwtentity.column.GwtColumnMetadata;
import com.dmma.base.gwt.client.ui.gwtentity.column.IGwtEntityCM;
import com.dmma.base.gwt.shared.entities.MailTemplateDTO;

public class MailTemplateCM implements IGwtEntityCM<MailTemplateDTO>{
	private GwtColumnMetadata idColumn;
	private GwtColumnMetadata nameColumn;
	private GwtColumnMetadata titleColumn;
	private GwtColumnMetadata headerColumn;
	private GwtColumnMetadata bodyColumn;
	
	
	public MailTemplateCM() {
		idColumn     = new GwtColumnMetadata(BaseMessages.MSG.id(),                 Integer.class, 20);
		nameColumn   = new GwtColumnMetadata(BaseMessages.MSG.mailTemplateName(),   String.class,  90);
		titleColumn  = new GwtColumnMetadata(BaseMessages.MSG.mailTemplateTitle(),  String.class,  200);
		headerColumn = new GwtColumnMetadata(BaseMessages.MSG.mailTemplateHeader(), String.class,  -1);
		bodyColumn   = new GwtColumnMetadata(BaseMessages.MSG.mailTemplateBody(),   String.class,  -1);
	}
	
	@Override
	public GwtColumnMetadata[] getColumnMeta(){
		return new GwtColumnMetadata[]{ idColumn, nameColumn,titleColumn,headerColumn, bodyColumn};
	}

	@Override
	public Object getValueAt(MailTemplateDTO entity, int index) {
		switch(index){
		case 0: return entity.getId();
		case 1: return entity.getName();
		case 2: return entity.getTitle();
		case 3: return entity.getHeader();
		case 4: return entity.getBody();
		}
		return null;
	}

	@Override
	public Object getId(MailTemplateDTO entity) {
		return entity.getId();
	}

	@Override
	public void setValueAt(MailTemplateDTO entity, int index, Object value) {
		// TODO Auto-generated method stub
	}
}
