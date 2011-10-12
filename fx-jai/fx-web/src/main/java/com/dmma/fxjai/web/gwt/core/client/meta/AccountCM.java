package com.dmma.fxjai.web.gwt.core.client.meta;


import java.util.Date;

import com.dmma.base.gwt.client.resources.i18n.BaseMessages;
import com.dmma.base.gwt.client.ui.gwtentity.column.GwtColumnMetadata;
import com.dmma.base.gwt.client.ui.gwtentity.column.IGwtEntityCM;
import com.dmma.fxjai.shared.shared.types.AccountType;
import com.dmma.fxjai.web.gwt.core.client.i18n.FXMessages;
import com.dmma.fxjai.web.gwt.core.shared.entities.AccountDTO;

public class AccountCM implements IGwtEntityCM<AccountDTO>{
	private final GwtColumnMetadata idColumn;
	private final GwtColumnMetadata accountColumn;
	private final GwtColumnMetadata accountTypeColumn;
	private final GwtColumnMetadata userNameColumn;
	private final GwtColumnMetadata serverNameColumn;
	private final GwtColumnMetadata createdColumn;
	
	
	public AccountCM() {
		idColumn          = new GwtColumnMetadata(BaseMessages.MSG.id(),              Integer.class, 20);
		accountColumn     = new GwtColumnMetadata(FXMessages.MSG.account(),           String.class,  90);
		accountTypeColumn = new GwtColumnMetadata(FXMessages.MSG.accountType(),       String.class,  200);
		userNameColumn    = new GwtColumnMetadata(FXMessages.MSG.accountUserName(),   String.class,  200);
		serverNameColumn  = new GwtColumnMetadata(FXMessages.MSG.accountServerName(), String.class,  -1);
		createdColumn     = new GwtColumnMetadata(BaseMessages.MSG.created(),         Date.class,    70);
	}
	
	@Override
	public GwtColumnMetadata[] getColumnMeta(){
		return new GwtColumnMetadata[]{ idColumn, accountColumn,accountTypeColumn,userNameColumn, serverNameColumn, createdColumn};
	}

	@Override
	public Object getValueAt(AccountDTO entity, int index) {
		switch(index){
		case 0: return entity.getId();
		case 1: return entity.getAccount();
		case 2: return AccountType.findById(entity.getAccountType()).getName();
		case 3: return entity.getUserName();
		case 4: return entity.getServerName();
		case 5: return entity.getCreated();
		}
		return null;
	}

	@Override
	public Object getId(AccountDTO entity) {
		return entity.getId();
	}

	@Override
	public void setValueAt(AccountDTO entity, int index, Object value) {
		// TODO Auto-generated method stub
	}
}
