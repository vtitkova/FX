package com.dmma.base.app.o2pdf;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;



public abstract class O2PdfAbstractServlet extends HttpServlet{
	private static final long serialVersionUID = -6495728089802712656L;


	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());
		initMe(context);
	}

	protected abstract void initMe(WebApplicationContext context);
	protected abstract ObjectAndSource generateModelAndSource(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;


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
		ObjectAndSource wrapper = generateModelAndSource(request, response);
		if(wrapper!=null ){
			doCreatePdf(response, wrapper.getXslSource(), wrapper.getModel(), wrapper.getFileName(), wrapper.getAditionalParams());
		}
	}

	
	protected void doCreatePdf(HttpServletResponse response, File  xslSource, Object model, String fileName, HashMap<String, String> aditionalParams) {
		try {
			response.setContentType("application/pdf");
			if(fileName == null || fileName.isEmpty()){
				fileName = "document.pdf";
			}
			response.addHeader("Content-Disposition", "attachment; filename="+fileName);

			O2PDF creator = new O2PDF();
			OutputStream outputStream = response.getOutputStream();

			creator.writeObjectToPDFOutputStream(outputStream, xslSource, model, aditionalParams );

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


	protected class ObjectAndSource{
		private File  xslSource;
		private Object model;
		private String fileName;
		private HashMap<String, String> aditionalParams;
		
		public ObjectAndSource() {
		}

		public File getXslSource() {
			return xslSource;
		}

		public void setXslSource(File xslSource) {
			this.xslSource = xslSource;
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

		public HashMap<String, String> getAditionalParams() {
			return aditionalParams;
		}

		public void setAditionalParams(HashMap<String, String> aditionalParams) {
			this.aditionalParams = aditionalParams;
		}
	}


}
