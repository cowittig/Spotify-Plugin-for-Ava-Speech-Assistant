package org.ava.spotify;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ava.pluginengine.AppCommand;
import org.ava.spotify.commands.SearchAlbumCommand;
import org.ava.spotify.commands.SearchArtistCommand;
import org.ava.spotify.commands.SearchTrackCommand;
import org.ava.util.PropertiesFileLoader;

import com.wrapper.spotify.Api;

/**
 * This class controls any activity of the Spotify plugin.
 * <p>
 * This includes:
 * 		-- delivering the commands of this plugin to Ava's plugin engine
 * 		-- initializing the Spotify API connection
 *
 * @author conwitti
 * @since 2016-05-25
 * @version 1
 *
 */
public class SpotifyController {

	private final static Logger log = LogManager.getLogger(SpotifyController.class);

	/** The commands of the Spotify plugin. */
	private List<AppCommand> commandList;

	/** Path to configuration file. Contains necessry information for invoking
	 * 	the Spotify API. */
	private Path CONFIG_PATH;

	/**
	 * Initialize the Spotify plugin.
	 */
	public SpotifyController() {
		this.commandList = new ArrayList<AppCommand>();
		init();
	}

	/**
	 * Initialize the Spotify API and the plugin commands.
	 */
	private void init() {
		log.debug("Initialize Spotify plugin.");

		// initialize the config file path
		try {
			Path basePath = new File(SpotifyController.class.getProtectionDomain().getCodeSource().getLocation().toURI())
					.toPath().getParent();
			CONFIG_PATH = Paths.get(basePath.toString(), "/res/spotify-config.properties");
		} catch (URISyntaxException e) {
			log.error("Error while creating the spotify configuration file path: " + e.getMessage());
			log.catching(Level.DEBUG, e);
		}

		// Create an API instance. The default instance connects to https://api.spotify.com/.
		Api apiHandler = Api.DEFAULT_API;

		// load config
		PropertiesFileLoader pfl = new PropertiesFileLoader(CONFIG_PATH);
		pfl.readPropertiesFile();
		SpotifyConfiguration.setMarket(pfl.getPropertie("spotify.market"));

		// Create commands...
		// each command gets the SpotifySearch object via constructor, to have easy access to
		// search functionality
		SpotifySearch ss = new SpotifySearch(apiHandler);
		this.commandList.add(new SearchAlbumCommand(ss));
		this.commandList.add(new SearchArtistCommand(ss));
		this.commandList.add(new SearchTrackCommand(ss));
	}

	/**
	 * Stop the Spotify plugin. Take care of any open resources (currently none).
	 */
	public void destruct() {
		log.debug("Spotify plugin destruct triggered.");
	}

	/**
	 * Returns a list of commands the Spotify plugin supplies.
	 *
	 * @return List<AppCommand> List containing the commands of this plugin.
	 */
	public List<AppCommand> getCommandList() {
		return this.commandList;
	}

}
