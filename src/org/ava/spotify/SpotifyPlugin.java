package org.ava.spotify;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ava.pluginengine.AppCommand;
import org.ava.pluginengine.AppPlugin;

/**
 * This class implements the base plugin class to be
 * compatible to Ava's plugin engine.
 * <p>
 * It maninly provides lifecycle functionality, like
 * starting and stopping the plugin.
 *
 * @author Constantin
 * @since 2016-05-25
 * @version 1
 */
public class SpotifyPlugin implements AppPlugin {

	private final static Logger log = LogManager.getLogger();

	/** The plugin controller manages the plugin activity. */
	private SpotifyController pluginController;

	/**
	 * Default constructor.
	 */
	public SpotifyPlugin() {

	}

	/**
	 * Start the plugin.
	 */
	@Override
	public void start() {
		this.pluginController = new SpotifyController();
		log.info("Spotify plugin started.");
	}

	/**
	 * Stop the plugin.
	 */
	@Override
	public void stop() {
		if( !hasPluginBeenStarted() ) {
			return;
		}

		this.pluginController.destruct();
		log.info("Spotify plugin stopped.");
	}

	/**
	 * Continue any paused plugin activity.
	 */
	@Override
	public void continueExecution() {
		if( !hasPluginBeenStarted() ) {
			return;
		}

		log.info("Spotify plugin resume triggered.");
	}

	/**
	 * Pause any paused plugin activity.
	 */
	@Override
	public void interruptExecution() {
		if( !hasPluginBeenStarted() ) {
			return;
		}

		log.info("Spotify plugin interrupt triggered.");
	}

	/**
	 * Return the commands supplied by this plugin.
	 *
	 * @return List<AppCommand> The commands supplied by this plugin.
	 */
	@Override
	public List<AppCommand> getApplicationCommands() {
		if( !hasPluginBeenStarted() ) {
			return new ArrayList<AppCommand>();
		}

		return this.pluginController.getCommandList();
	}

	/**
	 * Checks if the plugin has been started yet.
	 *
	 * @return boolean True if the plugin has been started yet, false if not.
	 */
	private boolean hasPluginBeenStarted() {
		if( this.pluginController == null ) {
			log.error("Spotify plugin not initialized. Start plugin first.");
			return false;
		} else {
			return true;
		}
	}

}
