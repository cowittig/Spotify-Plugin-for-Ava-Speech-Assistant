package org.ava.spotify.commands;

import java.util.List;

import org.ava.eventhandling.SpeakEvent;
import org.ava.eventhandling.TTSEventBus;
import org.ava.pluginengine.AppCommand;
import org.ava.spotify.SpotifySearch;

import com.wrapper.spotify.models.SimpleAlbum;

public class SearchAlbumCommand implements AppCommand {

	private final String searchAlbumCommand = "search album *";
	
	private SpotifySearch search;
	
	public SearchAlbumCommand(SpotifySearch search) {
		this.search = search;
	}
	
	@Override
	public void execute(String arg) {
		List<SimpleAlbum> searchResults = search.searchForAlbums(arg);
		
		String msg = null;
		if( searchResults.isEmpty() ) {
			msg = "No albums for search request '" + arg + "' found.";
		} else {
			SimpleAlbum firstResult = searchResults.get(0);
			msg = "Found " + searchResults.size() + " results."
					+ " The first result is: " + firstResult.getName() + ".";
		}
		TTSEventBus.getInstance().fireSspeakEvent(new SpeakEvent(msg));
	}

	@Override
	public String getCommand() {
		return searchAlbumCommand;
	}

}
