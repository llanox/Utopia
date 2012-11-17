package co.edu.udea.ludens.web;

import java.util.ArrayList;
import java.util.List;

import co.edu.udea.ludens.domain.Element;
import co.edu.udea.ludens.domain.MessageEvent;
import co.edu.udea.ludens.util.MessageListener;

import com.icesoft.faces.async.render.SessionRenderer;

public class BoardController implements MessageListener {

	private UserSessionBean userSession;
	private MessagesBean messagesBean;

	private List<ElementBean> materials = new ArrayList<ElementBean>();
	private List<ElementBean> developmentFactors = new ArrayList<ElementBean>();
	private Element population;

	public List<ElementBean> getMaterials() {

		return (this.materials);
	}

	public void setUserSession(UserSessionBean userSession) {
		this.userSession = userSession;
	}

	public UserSessionBean getUserSession() {

		return (this.userSession);
	}

	public List<ElementBean> getDevelopmentFactors() {

		return (this.developmentFactors);
	}

	public void setDevelopmentFactors(List<ElementBean> developmentFactors) {
		this.developmentFactors = developmentFactors;
	}

	public void setMaterials(List<ElementBean> materials) {
		this.materials = materials;
	}

	public Element getPopulation() {
		
		return (this.population);
	}

	public void setPopulation(Element population) {
		this.population = population;
	}

	// public void changeIncrementable(IncrementableEvent event) {
	//
	// if (EnumEventType.CHANGE_FACTOR == event.getEventType()) {
	// this.developmentFactors = event.getIncrementables();
	// }
	//
	// if (EnumEventType.CHANGE_MATERIAL == event.getEventType()) {
	// this.materials = event.getIncrementables();
	// }
	//
	// if (EnumEventType.CHANGE_POPULATION == event.getEventType()) {
	// this.population = (Population)
	// (event.getIncrementables().get(0)).getIncrementable();
	//
	// }
	//
	// SessionRenderer.render(userSession.getUser().getLogin());
	//
	// }

	@Override
	public void notifyMsg(MessageEvent event) {
		messagesBean.notifyMsg(event);

		SessionRenderer.render(userSession.getUser().getLogin());
	}

	public void setMessagesBean(MessagesBean messagesBean) {
		this.messagesBean = messagesBean;
	}

	public MessagesBean getMessagesBean() {

		return (this.messagesBean);
	}
}
