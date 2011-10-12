package com.dmma.base.app.mail.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dmma.base.app.mail.daos.MailTemplateDao;
import com.dmma.base.app.mail.entities.MailTemplate;
import com.dmma.base.app.services.base.BaseService;

public class MailTemplateService implements BaseService<MailTemplate, Integer>{
	private static final Logger log = LoggerFactory.getLogger(MailTemplateService.class);
	private MailTemplateDao mailTemplateDao;
	
	@Override
	public MailTemplate findById(Integer id) {
		log.debug("findById");
		return mailTemplateDao.findById(id);
	}
	@Override
	public void saveOrUpdate(MailTemplate entity) {
		log.debug("saveOrUpdate");
		mailTemplateDao.saveOrUpdate(entity);
	}
	@Override
	public void delete(MailTemplate entity) {
		log.debug("delete");
		mailTemplateDao.delete(entity);
	}
	@Override
	public List<MailTemplate> findAll() {
		log.debug("findAll");
		return mailTemplateDao.findAll();
	}
	@Override
	public List<Integer> findAllIDs() {
		log.debug("findAllIDs");
		return mailTemplateDao.findAllIDs();
	}
	
	public boolean keyExist(String name) {
		log.debug("nameExist"+name);
		return mailTemplateDao.nameExist(name);
	}
	
	public MailTemplate findByKey(String name){
		log.debug("findByName"+name);
		return mailTemplateDao.findByName(name);
	}
	public ArrayList<String> findAllKeys() {
		log.debug("findAllKeys");
		return mailTemplateDao.findAllNames();
	}

	
	public void setMailTemplateDao(MailTemplateDao mailTemplateDao) {
		this.mailTemplateDao = mailTemplateDao;
	}
}
