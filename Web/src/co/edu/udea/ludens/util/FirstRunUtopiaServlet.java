package co.edu.udea.ludens.util;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import co.edu.udea.ludens.services.FirstRunConfiguration;

public class FirstRunUtopiaServlet implements ServletContextListener {

	private static Logger logger = Logger
			.getLogger(FirstRunUtopiaServlet.class);

	@Override
	public void contextInitialized(ServletContextEvent event) {
		ServletContext context = event.getServletContext();
		// Calendar cal = Calendar.getInstance();
		String filePath = null;
		String fileName = context
				.getInitParameter(LudensConstants.KEY_CONFIGURATION_FILE_NAME);
		String realAppPath = context.getRealPath("");

		logger.info("realAppPath: CONTEXT INITIALIZED " + realAppPath);

		File file = new File(realAppPath);

		// Up one level in directory structure
		filePath = file.getParentFile().getAbsolutePath()
				+ LudensUtilBean.getFileSeparator() + fileName;

		logger.info("File path ---> " + filePath);
		file = new File(filePath);

		if (true/*!file.exists()*/) {
			logger.info("First Run ....");

			ApplicationContext ctx = WebApplicationContextUtils
					.getWebApplicationContext(context);
			FirstRunConfiguration config = (FirstRunConfiguration) ctx
					.getBean(LudensConstants.FIRST_RUN_CONFIG_BEAN);
			config.loadAllDefaultConfiguration();

			try {
				file.createNewFile();
			} catch (IOException e) {
				logger.error(e);
			}
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
	}
}
