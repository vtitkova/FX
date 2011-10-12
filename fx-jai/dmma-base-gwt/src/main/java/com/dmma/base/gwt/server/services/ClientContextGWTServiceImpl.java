package com.dmma.base.gwt.server.services;

import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.servlet.http.HttpSession;

import com.dmma.base.app.utils.BaseServerFormats;
import com.dmma.base.gwt.client.services.ClientContextGWTService;
import com.dmma.base.gwt.shared.AppVersion;
import com.dmma.base.gwt.shared.dtos.ClientContextDTO;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * Client context allows you to keep in session all parameters 
 * 
 * 	<!-- Base Servlets -->
    <servlet>
	 	<servlet-name>clientContextGWTServlet</servlet-name>
		<servlet-class>com.dmma.base.gwt.server.services.ClientContextGWTServiceImpl</servlet-class>
	</servlet>
	<servlet-mapping>
		<servlet-name>clientContextGWTServlet</servlet-name>
		<url-pattern>/Broker/ClientContextGWTService</url-pattern>
	</servlet-mapping>
	....
 **/
public class ClientContextGWTServiceImpl extends RemoteServiceServlet implements ClientContextGWTService{
	private static final long serialVersionUID = 3283456347194249670L;

	
	@Override
	public ClientContextDTO getAppModel() {
		ClientContextDTO model = getStoredOrCreateNewAppModel();
		System.out.println(model);
		System.out
				.println(BaseServerFormats.getFormattedDateTime(new Date()));;
		return model;
	}

	@Override
	public Boolean updateOrSetParam(HashMap<String, String> paramMap) {
		if(paramMap!=null&&paramMap.size()>0){
			ClientContextDTO model = getStoredOrCreateNewAppModel();
			for(Entry<String, String> entry:paramMap.entrySet()){
				model.addParam(entry.getKey(), entry.getValue());
			}
			return true;
		}
		return false;
	}
	
	@Override
	public Boolean pingSession() {
		return true;
	}
	
	
	private ClientContextDTO getStoredOrCreateNewAppModel() {
		ClientContextDTO model = (ClientContextDTO) getSession().getAttribute(ClientContextDTO.APP_MODEL);
		if(model==null){
			///PKC.WH_ID
			model = new ClientContextDTO();
			//model.addParam("whId", "1");
			model.setIsSynchronised(true);
			model.addParam("AppVersion", AppVersion.APP_VERSION);
			getSession().setAttribute(ClientContextDTO.APP_MODEL, model);
		}
		return model;
	}
	
	private HttpSession getSession(){
		HttpSession session = getThreadLocalRequest().getSession(true);
		return session;
	}


}
