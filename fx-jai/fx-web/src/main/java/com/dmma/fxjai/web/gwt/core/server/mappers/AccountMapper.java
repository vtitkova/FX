package com.dmma.fxjai.web.gwt.core.server.mappers;

import java.util.ArrayList;
import java.util.List;

import com.dmma.fxjai.db.entities.Account;
import com.dmma.fxjai.web.gwt.core.shared.entities.AccountDTO;

public class AccountMapper {

	public static AccountDTO toDTO(Account source){
		if(source == null) return null;
		AccountDTO target = new AccountDTO();
		target.setId(          source.getId());
		target.setAccount(     source.getAccount());
		target.setAccountType( source.getAccountType());
		target.setUserName(    source.getUserName());
		target.setServerName(  source.getServerName());
		target.setCreated(     source.getCreated());
		return target;
	}
	
	public static ArrayList<AccountDTO> toDTOs(List<Account> entities){
		if(entities == null) return null;
		ArrayList<AccountDTO> retVal = new ArrayList<AccountDTO>(entities.size());
		
		for(Account entity:entities){
			retVal.add(toDTO(entity));
		}
		return retVal;
	}
	
	/*public static Poc mapToEntity(PocDTO source, Poc target){
		if(target==null){
			target = new Poc();
		}
		//target.setId(         source.getId());
		target.setText( source.getText());
		target.setCreated( source.getCreated());
		return target;
	}*/
}
