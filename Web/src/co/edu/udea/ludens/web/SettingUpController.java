package co.edu.udea.ludens.web;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import co.edu.udea.ludens.domain.MessageEvent;
import co.edu.udea.ludens.services.SystemContainer;

public class SettingUpController {

	private List<SelectItem> units;
	private static final String SEGS="segs";
	private static final String MINS="mins";
	private static final String HRS="hrs";
	private static final String DIAS="días";
	
	private String[] unitsNames = { SEGS, MINS, HRS, DIAS };
	private SystemContainer systemContainer;
	private MessagesBean messagesBean;

	
	
	
	
	
	public void setUnits(List<SelectItem> units) {
		this.units = units;
	}
	
	

	public List<SelectItem> getUnits() {
		
		if (units == null) {	
			
			units = new ArrayList<SelectItem>();
			for (int i = 0; i < unitsNames.length; i++)
				units.add(new SelectItem(unitsNames[i], unitsNames[i]));			
		}
		
		return units;
	}
	
	

	public void setSystemContainer(SystemContainer systemContainer) {
		this.systemContainer = systemContainer;
	}

	public SystemContainer getSystemContainer() {
		return systemContainer;
	}


	public void setMessagesBean(MessagesBean messagesBean) {
		this.messagesBean = messagesBean;
	}


	public MessagesBean getMessagesBean() {
		return messagesBean;
	}

}
