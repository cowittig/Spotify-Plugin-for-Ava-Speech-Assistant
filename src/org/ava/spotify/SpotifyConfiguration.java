package org.ava.spotify;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class provides configuration information for the
 * Spotify plugin.
 *
 * @author conwitti
 * @since 2016-05-25
 * @version 1
 *
 */
public class SpotifyConfiguration {

	private final static Logger log = LogManager.getLogger(SpotifyConfiguration.class);

	/** The target market used in API requests. */
	private static String market;

	/**
	 * This class is not be instantiated. Information has to be accessed through
	 * getter and setter.
	 */
	private SpotifyConfiguration() {

	}

	/**
	 * Set the target market used in API requests.
	 *
	 * @param market The new target market.
	 */
	public static void setMarket(String market) {
		SpotifyConfiguration.market = market;
		log.debug("Spotify market set to: " + market);
	}

	/**
	 * Return the target market used in API requests.
	 *
	 * @return String The target market.
	 */
	public static String getMarket() {
		return SpotifyConfiguration.market;
	}
}
