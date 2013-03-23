package co.edu.udea.ludens.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;

import co.edu.udea.ludens.domain.Element;
import co.edu.udea.ludens.domain.IncrementableConstraint;
import co.edu.udea.ludens.domain.Player;
import co.edu.udea.ludens.domain.PlayerStatus;
import co.edu.udea.ludens.exceptions.LudensException;

public class UtopiaUtil {

	public static final int LEVEL_TO_GETTING_START = 1;
	public static final int POPULATION_DEFAULT_LEVEL = 1;
	public static final int PRODUCTION_PERIOD_MILSEC = 300 * 1000;
	public static final int POPULATION_N = PRODUCTION_PERIOD_MILSEC;
	public static final int EVENT_HAPPENING_PERIOD_MILSEC = PRODUCTION_PERIOD_MILSEC * 5;

	public static long idCounter = 0;

	public static final Logger logger = Logger.getLogger(UtopiaUtil.class);

	public static long getId() {

		return idCounter++;
	}

	public static Double calculateExpGrowth(int p0, double lambda, int n) {

		logger.info("Calculating exp ..");

		double lambdaD = lambda / 100;

		logger.info("LambdaD .." + lambdaD);

		Double result = p0 * Math.exp(lambdaD * n);

		logger.info(p0 + "*E^(" + lambdaD + "*" + n + ")");
		logger.info("result .." + result);

		return result;
	}

	public static void addStartTimePlayer(Player player) {
		Calendar cal = Calendar.getInstance();
		Long time = player.getStartTime();

		if (time == 0) {
			time = cal.getTimeInMillis();
			player.setStartTime(time);
			logger.info("adding start time " + time);
		}
	}

	public static long getElapsedTime(Player player) {
		Calendar cal = Calendar.getInstance();
		long elapsedTime = 0;
		long startTime = 0;

		if (player != null)
			startTime = player.getStartTime();

		if (startTime > 0)
			elapsedTime = cal.getTimeInMillis() - startTime;

		return elapsedTime;
	}

	public static Integer calculateTimeN(Player player) {
		Calendar cal = Calendar.getInstance();
		Long actualTime = cal.getTimeInMillis();
		Long time = player.getStartTime();
		long result = 0;
		int n = 0;

		logger.info("Time " + time);

		if (time != null && time > 0) {
			result = actualTime - time;
			double nd = (result / POPULATION_N);
			n = (int) nd;
			logger.info(" result " + result + " n " + n + " nd " + nd);
		}

		return n;
	}

	public static void checkOutResources(
			List<IncrementableConstraint> levelResources, Element element,
			Player player) throws LudensException {
	}

	public static void updateUpgradingDelay(Element element) {
		int lambda = element.getLevelIncrementDelayRate();
		int n = element.getLevel() + 1;
		int p0 = element.getLevelIncrementDelayRate();
		Integer delayTime = (int) (calculateExpGrowth(p0, lambda, n) + 0);

		element.setActualUpgradingTime(delayTime);
	}



	public static void calculateCoverage(Element element, Player player) {
		double capacity = element.getQuantity();
		double population = player.getPopulation().getQuantity();
		int coverage = (int) ((capacity / population) * 100);

		logger.info("Coverage: " + coverage + " element "
				+ element.getIncrementable() + " population " + population
				+ " capacity " + capacity);

		element.setCalculatedValue(coverage);
	}

	public static List<SelectItem> getItems(String[] names) {
		List<SelectItem> items = new ArrayList<SelectItem>();
		for (int i = 0; i < names.length; i++)
			items.add(new SelectItem(names[i], names[i]));

		return items;
	}

	public static List<SelectItem> getItems(List<Object> objects) {

		return null;
	}

	public static Player getPlayerByName(List<Player> players,
			String offerSender) {
		for (Player player : players) {
			if (player.getUser().getLogin().equalsIgnoreCase(offerSender))
				return player;
		}

		return null;
	}

	public static int generateNumberBetweenRange(int lower, int upper) {
		int result = -1;
		result = lower + (int) (Math.random() * ((upper - lower) + 1));

		return result;
	}
}
