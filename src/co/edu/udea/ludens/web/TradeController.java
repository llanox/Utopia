package co.edu.udea.ludens.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;

import com.icesoft.faces.async.render.SessionRenderer;

import co.edu.udea.ludens.domain.Element;
import co.edu.udea.ludens.domain.Interchange;
import co.edu.udea.ludens.domain.MessageEvent;
import co.edu.udea.ludens.enums.EnumElementType;
import co.edu.udea.ludens.enums.EnumMsgs;
import co.edu.udea.ludens.exceptions.LudensException;
import co.edu.udea.ludens.services.ElementService;
import co.edu.udea.ludens.services.ProcessHolderService;
import co.edu.udea.ludens.services.SystemContainer;
import co.edu.udea.ludens.services.GameProcess;
import co.edu.udea.ludens.services.TradeProcess;
import co.edu.udea.ludens.util.InterchangeEvent;
import co.edu.udea.ludens.util.TradeListener;

public class TradeController implements TradeListener {
    
	private static final String OWNER_VIEW="owner";
	private static final String OTHERS_VIEW="others";
	
	private Logger logger = Logger.getLogger(getClass());
	private UserSessionBean userSession;
	private MessagesBean messagesBean;
	private ElementService elementService;

	private List<SelectItem> elementItems = new ArrayList<SelectItem>();
	private List<InterchangeBean> interchangeRequests = new ArrayList<InterchangeBean>();
	
	
	private String elFromSender;
	private int quantityFromSender;
	private String elFromReceiver;
	private int quantityFromReceiver;
	
	

	private ProcessHolderService processHolderService;

	public TradeController() {
		super();
	}
	

	public void postOffer(ActionEvent ae) {

		logger.info("postOffer ....");
		
		
		GameProcess gameProcess = (GameProcess) processHolderService.findProcessById(GameProcess.class,userSession.getActualGame());
		TradeProcess tradeProcess = gameProcess.getTradeProcess();	
		String offerSender = userSession.getUser().getLogin();
		

		tradeProcess.postOffer(elFromSender,quantityFromSender,elFromReceiver,quantityFromReceiver,offerSender);
		
		quantityFromSender =0;
		quantityFromReceiver =0;

	}

	
	
	@Override
	public void requestTrade(InterchangeEvent event) {
		interchangeRequests = new ArrayList<InterchangeBean>();
		InterchangeBean bean =null;
		
	for(Interchange interchange: event.getInterchanges()){
			
			bean = new InterchangeBean();	
			String offerSender = userSession.getUser().getLogin();
			
		    bean.setProcess((TradeProcess) event.getSource());
		    bean.setInterchange(interchange);
			
			
			String sender =interchange.getSender().getUser().getLogin();
			
			Integer qtFromSender = interchange.getQuantityFromSender();
			String  elFromSender = interchange.getElFromSender().getIncrementable().getName();
			
			Integer qtFromRecv = interchange.getQuantityFromReceiver();
			String  elFromRecv = interchange.getElFromReceiver().getIncrementable().getName();
			//                         
			String msg = String.format(EnumMsgs.DETAILS_NEW_OFFER.getMsg(),sender ,qtFromSender,elFromSender,qtFromRecv,elFromRecv);
			
			bean.setMessage(msg);
			
			if(offerSender.equalsIgnoreCase(sender)){				
				bean.setViewers(TradeController.OWNER_VIEW);
			}else{
				bean.setViewers(TradeController.OTHERS_VIEW);
			}
		    
		    
			interchangeRequests.add(bean);
		}
		
		
		
		SessionRenderer.render(userSession.getUser().getLogin());

	}

	public void setInterchangeRequests(List<InterchangeBean> interchangeRequests) {
		this.interchangeRequests = interchangeRequests;
	}

	public List<InterchangeBean> getInterchangeRequests() {
		return interchangeRequests;
	}

	public void setMessagesBean(MessagesBean messagesBean) {
		this.messagesBean = messagesBean;
	}

	public MessagesBean getMessagesBean() {
		return messagesBean;
	}

	@Override
	public void notifyMsg(MessageEvent event) {
		messagesBean.notifyMsg(event);
		SessionRenderer.render(userSession.getUser().getLogin());

	}

	public void setElementItems(List<SelectItem> elementItems) {
		this.elementItems = elementItems;
	}

	public List<SelectItem> getElementItems() {

		if (elementItems == null || elementItems.isEmpty()) {
			
					
			Set<Element> elements = elementService.getAllElementsByPlayer(EnumElementType.MATERIAL,userSession.getUser().getLogin());

			for (Element element : elements) {
				elementItems.add(new SelectItem(element.getIncrementable().getName(), element.getIncrementable().getName()));
			}
			
			logger.debug("Retrieving elements to trade controller "+elements);
		}

		return elementItems;
	}

	public void setElementService(ElementService elementService) {
		this.elementService = elementService;
	}

	public void setUserSession(UserSessionBean userSession) {
		this.userSession = userSession;
	}

	public UserSessionBean getUserSession() {
		return userSession;
	}

	
	


	


	public void setQuantityFromSender(int quantityFromSender) {
		this.quantityFromSender = quantityFromSender;
	}


	public int getQuantityFromSender() {
		return quantityFromSender;
	}


	public void setQuantityFromReceiver(int quantityFromReceiver) {
		this.quantityFromReceiver = quantityFromReceiver;
	}


	public void setElFromReceiver(String elFromReceiver) {
		this.elFromReceiver = elFromReceiver;
	}


	public String getElFromReceiver() {
		return elFromReceiver;
	}


	public int getQuantityFromReceiver() {
		return quantityFromReceiver;
	}


	public void setElFromSender(String elFromSender) {
		this.elFromSender = elFromSender;
	}


	public String getElFromSender() {
		return elFromSender;
	}


	public ProcessHolderService getProcessHolderService() {
		return processHolderService;
	}


	public void setProcessHolderService(ProcessHolderService processHolderService) {
		this.processHolderService = processHolderService;
	}

}
