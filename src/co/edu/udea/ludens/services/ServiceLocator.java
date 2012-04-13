package co.edu.udea.ludens.services;

import org.springframework.scheduling.quartz.SimpleTriggerBean;

public abstract class ServiceLocator {

	public ElementProcess createElementProcess() {

		return getElementProcess();
	}

	public GameProcess createGameProcess() {

		return getGameProcess();
	}

	public TradeProcess createTradeProcess() {

		return getTradeProcess();
	}

	public EventProcess createEventProcess() {

		return getEventProcess();
	}

	public SimpleTriggerBean createTriggerBean() {

		return getSimpleTriggerBean();
	}

	public abstract ElementProcess getElementProcess();

	public abstract GameProcess getGameProcess();

	public abstract TradeProcess getTradeProcess();

	public abstract EventProcess getEventProcess();

	public abstract SimpleTriggerBean getSimpleTriggerBean();

}
