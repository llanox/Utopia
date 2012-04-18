package co.edu.udea.ludens.util;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

public class LudensUtilBean {



	public String getHostName() {

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();
		int localPort = request.getRemotePort();
		String localHost = request.getRemoteAddr();

		String url = localHost + ":" + localPort;

		return url;

	}

	public String getRequestUrl() {

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();

		String url = request.getRequestURL().toString();

		return url;
	}

	public String getQueryString() {

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();
		String queryString = request.getQueryString();

		return queryString;
	}
	
	
	public String getServerName() {

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();
		String serverName = request.getServerName();

		return serverName;
	}
	
	public String getServerPort() {

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();
		String serverPort = request.getServerPort()+"";

		return serverPort;
	}
	
	
	public String getPathInfo() {

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();
		String pathInfo = request.getPathInfo();

		return pathInfo;
	}
	
	public String getContextPath() {

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();
		String contextPath = request.getContextPath();

		return contextPath;
	}
	
	public String getUrlBase() {

		FacesContext context = FacesContext.getCurrentInstance();
		
		HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
		
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(ConstantsLudens.HTTP_PROTOCOL);
		sb.append(request.getServerName());
		sb.append(":");
		sb.append(request.getServerPort());
		sb.append(request.getContextPath());
		

		return sb.toString();
	}
	
	public String getSoftwareVersion(){
		
		return ConstantsLudens.SOFTWARE_VERSION;
		
	}
	
	public static String getFileSeparator(){
		
		return System.getProperty("file.separator");
		
	}
	
	@SuppressWarnings("unchecked")
	public static Object findBean(String beanName) {
	    FacesContext context = FacesContext.getCurrentInstance();
	    return context.getApplication().evaluateExpressionGet(context, "#{" + beanName + "}", Object.class);
	}




}
