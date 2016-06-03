package org.ava.spotify.commands;

import java.util.List;

import org.ava.eventhandling.SpeakEvent;
import org.ava.eventhandling.TTSEventBus;
import org.ava.pluginengine.AppCommand;
import org.ava.spotify.SpotifySearch;

import com.wrapper.spotify.models.Track;

public class SearchTrackCommand implements AppCommand {

	private final String searchTrackCommand = "search track *";
	
	private SpotifySearch search;
	
	public SearchTrackCommand(SpotifySearch search) {
		this.search = search;
	}
	
	@Override
	public void execute(String arg) {
		List<Track> searchResults = search.searchForTracks(arg);
		
		String msg = null;
		if( searchResults.isEmpty() ) {
			msg = "No tracks for search request '" + arg + "' found.";
		} else {
			Track firstResult = searchResults.get(0);
			msg = "Found " + searchResults.size() + " results."
					+ " The first result is: " + firstResult.getName() + " by " + firstResult.getArtists().get(0) + ".";
		}
		TTSEventBus.getInstance().fireSspeakEvent(new SpeakEvent(msg));
	}

	@Override
	public String getCommand() {
		return searchTrackCommand;
	}

}
