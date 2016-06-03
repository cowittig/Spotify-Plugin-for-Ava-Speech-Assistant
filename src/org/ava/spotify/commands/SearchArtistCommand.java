package org.ava.spotify.commands;

import java.util.List;

import org.ava.eventhandling.SpeakEvent;
import org.ava.eventhandling.TTSEventBus;
import org.ava.pluginengine.AppCommand;
import org.ava.spotify.SpotifySearch;

import com.wrapper.spotify.models.Artist;

public class SearchArtistCommand implements AppCommand {

	private final String searchArtistCommand = "search artist *";
	
	private SpotifySearch search;
	
	public SearchArtistCommand(SpotifySearch search) {
		this.search = search;
	}
	
	@Override
	public void execute(String arg) {
		List<Artist> searchResults = search.searchForArtists(arg);
		
		String msg = null;
		if( searchResults.isEmpty() ) {
			msg = "No artists for search request '" + arg + "' found.";
		} else {
			Artist firstResult = searchResults.get(0);
			msg = "Found " + searchResults.size() + " results."
					+ " The first result is: " + firstResult.getName() + ".";
		}
		TTSEventBus.getInstance().fireSspeakEvent(new SpeakEvent(msg));
	}

	@Override
	public String getCommand() {
		return searchArtistCommand;
	}
}
