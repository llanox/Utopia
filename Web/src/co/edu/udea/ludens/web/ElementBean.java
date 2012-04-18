package co.edu.udea.ludens.web;

import com.icesoft.faces.async.render.SessionRenderer;

import common.Logger;


import co.edu.udea.ludens.domain.Element;
import co.edu.udea.ludens.domain.Incrementable;
import co.edu.udea.ludens.services.ElementProcess;

public class ElementBean {
	
	private Element element;
	private Integer production;
    private Logger logger = Logger.getLogger(getClass());
    private ElementProcess process;
	
	
    public void upLevel(javax.faces.event.ActionEvent ae) {
        
			process.upLevel(element);	
			SessionRenderer.render(process.getPlayer().getUser().getLogin());
	}
		
	
	public void setProduction(Integer production) {
		this.production = production;
	}
	
	public Integer getProduction() {			
		return production;
	}


	public void setElement(Element incrementable) {
		this.element = incrementable;
	}


	public Element getElement() {
		return element;
	}


	public void setProcess(ElementProcess process) {
		this.process = process;
	}


	public ElementProcess getProcess() {
		return process;
	}


	
	
	
	

}
