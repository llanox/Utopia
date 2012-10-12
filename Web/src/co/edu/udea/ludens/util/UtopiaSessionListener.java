package co.edu.udea.ludens.util;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

import co.edu.udea.ludens.domain.User;
import co.edu.udea.ludens.services.SystemContainer;
import co.edu.udea.ludens.web.UserSessionBean;

import com.icesoft.faces.async.render.SessionRenderer;

public class UtopiaSessionListener implements HttpSessionListener {
	private Logger logger = Logger.getLogger(getClass());

	@Override
	public void sessionCreated(HttpSessionEvent event) {
		logger.info("------------- SESSION CREATED-------------");
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		UserSessionBean userBean = (UserSessionBean) session
				.getAttribute(ConstantsLudens.SESSION_BEAN);
		SystemContainer systemContainer = (SystemContainer) session
				.getAttribute(ConstantsLudens.SYSTEM_CONTAINER_BEAN);
		User user = null;

		if (userBean == null)
			return;

		user = userBean.getUser();
		if (user == null)
			return;

		user.setOnline(false);
		SessionRenderer.removeCurrentSession(user.getLogin());
		logger.info("------------- set online false -------------");

		if (systemContainer == null)
			return;

		systemContainer.logOut(user);
		logger.info("------------- logged out user -------------");
		logger.info("------------- Session destroyed -------------");

	}
}
