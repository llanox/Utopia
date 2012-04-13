package co.edu.udea.ludens.util;

import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

import co.edu.udea.ludens.domain.Interchange;


public class InterchangeEvent extends EventObject {
	
	private static final long serialVersionUID = -4770541524000580917L;

	private List<Interchange> interchanges = new ArrayList<Interchange>();
	  
	public InterchangeEvent(Object source,List<Interchange> interchanges) {
		super(source);
		this.setInterchanges(interchanges);
	
	}



	public void setInterchanges(List<Interchange> interchanges) {
		this.interchanges = interchanges;
	}

	public List<Interchange> getInterchanges() {
		return interchanges;
	}
	



}
