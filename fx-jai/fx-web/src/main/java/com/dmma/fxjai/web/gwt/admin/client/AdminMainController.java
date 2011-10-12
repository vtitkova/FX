package com.dmma.fxjai.web.gwt.admin.client;

import java.util.HashMap;

import com.dmma.base.gwt.client.mail.MailTemplateSuggestion;
import com.dmma.base.gwt.client.mail.mailTemplates.MailTemplatesPresenter;
import com.dmma.base.gwt.client.mail.mailform.MailFormPresenter;
import com.dmma.base.gwt.client.mail.mails.MailsPresenter;
import com.dmma.base.gwt.client.mail.mailtemplateform.MailTemplatePresenter;
import com.dmma.base.gwt.client.resources.i18n.BaseMessages;
import com.dmma.base.gwt.client.ui.menu.MenuEntry;
import com.dmma.base.gwt.client.ui_sample.MenuAndPresenterView;
import com.dmma.fxjai.web.gwt.admin.client.mvp.accounts.AccountsPresenter;
import com.dmma.fxjai.web.gwt.admin.client.mvp.bars.BarsPresenter;
import com.dmma.fxjai.web.gwt.admin.client.mvp.barsvisual.BarsVisualPresenter;
import com.dmma.fxjai.web.gwt.core.client.i18n.FXMessages;
import com.google.gwt.user.client.ui.RootPanel;

public class AdminMainController extends MenuAndPresenterView{
	
	public AdminMainController(RootPanel rootPanel) {
		super(rootPanel, 10);
		createMenu();
		registerPresenters();
		fireCurrentHistoryState();
	}
	
	private void createMenu() {
		MenuEntry home = new MenuEntry(FXMessages.MSG.menuHome()); //, DashboardImages.IMG.home());
		home.addItem(FXMessages.MSG.menuBars(), BarsPresenter.PRESENTER_ID); // BankerHomePresenter.PRESENTER_ID);
		home.addItem(FXMessages.MSG.menuBars(), BarsVisualPresenter.PRESENTER_ID); // BankerHomePresenter.PRESENTER_ID);
		home.addItem("Accounts", AccountsPresenter.PRESENTER_ID);
		
		
		
		MenuEntry settings = new MenuEntry(FXMessages.MSG.menuSetups()); //, DashboardImages.IMG.users());
		settings.addItem(BaseMessages.MSG.mailMenuNewMail(),         MailFormPresenter.PRESENTER_ID);
		settings.addItem(BaseMessages.MSG.mailMenuMails(),           MailsPresenter.PRESENTER_ID);
		settings.addItem(BaseMessages.MSG.mailMenuNewMailTemplate(), MailTemplatePresenter.PRESENTER_ID);
		settings.addItem(BaseMessages.MSG.mailMenuMailTemplates(),   MailTemplatesPresenter.PRESENTER_ID);
		
		this.addMenuEntry(home);
		this.addMenuEntry(settings);
	}

	private void registerPresenters() {
		
		addPresenter(new AccountsPresenter());
		addPresenter(new BarsPresenter());
		addPresenter(new BarsVisualPresenter());

		MailTemplateSuggestion mts1 = new MailTemplateSuggestion("Welcome", FXMessages.MSG.welcomeMailTemplate(), new String[]{"NAME","OFFICE"});
		HashMap<String, MailTemplateSuggestion> suggestions = new HashMap<String, MailTemplateSuggestion>();
		suggestions.put(mts1.getName(), mts1);
		
		MailTemplateSuggestion mts2 = new MailTemplateSuggestion("Goodby", "Goodbye template", new String[]{"NAME","SURNAME", "DATE"});
		suggestions.put(mts2.getName(), mts2);
		
		addPresenter(new MailsPresenter());
		addPresenter(new MailFormPresenter());
		addPresenter(new MailTemplatePresenter(suggestions));
		addPresenter(new MailTemplatesPresenter());

	}
}
