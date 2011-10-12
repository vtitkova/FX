package com.dmma.base.app.o2xml;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;



public abstract class O2XmlAbstractServlet extends HttpServlet{
	private static final long serialVersionUID = -7420870116614942648L;


	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());
		initMe(context);
	}

	protected abstract void initMe(WebApplicationContext context);
	protected abstract ObjectAndFilename generateModelAndFilename(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;


	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		renderPage(request, response);
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		renderPage(request, response);
	}

	private void renderPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		ObjectAndFilename wrapper = generateModelAndFilename(request, response);
		if(wrapper!=null ){
			doCreatePdf(response, wrapper.getModel(), wrapper.getFileName());
		}
	}

	
	protected void doCreatePdf(HttpServletResponse response, Object model, String fileName) {
		try {
			response.setContentType("application/xml");
			if(fileName == null || fileName.isEmpty()){
				fileName = "document.xml";
			}
			response.addHeader("Content-Disposition", "attachment; filename="+fileName);

			O2XML creator = new O2XML();
			OutputStream outputStream = response.getOutputStream();

			creator.writeObjectToXMLOutputStream(model, outputStream);

			outputStream.flush();
			outputStream.close();

		}catch(IOException e) {
			response.reset();
			try {
				response.getWriter().write("Error: PDF creation failed");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}


	protected class ObjectAndFilename{
		private Object model;
		private String fileName;

		public ObjectAndFilename() {
		}

		public Object getModel() {
			return model;
		}

		public void setModel(Object model) {
			this.model = model;
		}

		public String getFileName() {
			return fileName;
		}

		public void setFileName(String fileName) {
			this.fileName = fileName;
		}
	}


}
