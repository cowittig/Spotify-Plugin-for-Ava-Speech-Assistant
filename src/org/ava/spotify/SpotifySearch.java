package org.ava.spotify;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.wrapper.spotify.Api;
import com.wrapper.spotify.methods.AlbumSearchRequest;
import com.wrapper.spotify.methods.ArtistSearchRequest;
import com.wrapper.spotify.methods.TrackSearchRequest;
import com.wrapper.spotify.models.Artist;
import com.wrapper.spotify.models.Page;
import com.wrapper.spotify.models.SimpleAlbum;
import com.wrapper.spotify.models.Track;

/**
 * This class provides Spotify search functionality to the plugin commands.
 * <p>
 * Currently the search supports:
 * 		-- tracks
 * 		-- artists
 * 		-- albums
 *
 * @author conwitti
 * @since 2016-05-25
 * @version 1
 *
 */
public class SpotifySearch {

	private final static Logger log = LogManager.getLogger();

	/** The Spotify API instance. */
	private Api apiHandler;

	/**
	 * Initialize the search functionality.
	 *
	 * @param apiHandler The Spotify API instance.
	 */
	public SpotifySearch(Api apiHandler) {
		this.apiHandler = apiHandler;
	}

	/**
	 * Search for tracks using the given input string.
	 *
	 * @param searchRequest The search string.
	 * @return List<Track> A list containing found tracks. If no track was found,
	 * 				or there was an error while performing the request, an empty
	 * 				list will be returned.
	 */
	public List<Track> searchForTracks(String searchRequest) {
		log.debug("Search for tracks. [search request = " + searchRequest + "]");
		List<Track> trackList = new ArrayList<Track>();
		TrackSearchRequest request = apiHandler.searchTracks(searchRequest)
										.market(SpotifyConfiguration.getMarket())
										.build();

		try {
		   Page<Track>trackSearchResult = request.get();
		   trackList = trackSearchResult.getItems();
		} catch (Exception e) {
			log.error("An exception occured while searching for tracks. Error message: " + e.getMessage());
			log.catching(Level.DEBUG, e);
		}

		return  trackList;
	}

	/**
	 * Search for artists using the given input string.
	 *
	 * @param searchRequest The search string.
	 * @return List<Artist> A list containing found artists. If no artist was found,
	 * 				or there was an error while performing the request, an empty
	 * 				list will be returned.
	 */
	public List<Artist> searchForArtists(String searchRequest) {
		log.debug("Search for artists. [search request = " + searchRequest + "]");
		List<Artist> artistList = new ArrayList<Artist>();
		ArtistSearchRequest request = apiHandler.searchArtists(searchRequest)
										.market(SpotifyConfiguration.getMarket())
										.build();

		try {
		   Page<Artist> artistSearchResult = request.get();
		   artistList = artistSearchResult.getItems();
		} catch (Exception e) {
			log.error("An exception occured while searching for artists. Error message: " + e.getMessage());
			log.catching(Level.DEBUG, e);
		}

		return artistList;
	}

	/**
	 * Search for albums using the given input string.
	 *
	 * @param searchRequest The search string.
	 * @return List<Album> A list containing found albums. If no album was found,
	 * 				or there was an error while performing the request, an empty
	 * 				list will be returned.
	 */
	public List<SimpleAlbum> searchForAlbums(String searchRequest) {
		log.debug("Search for albums. [search request = " + searchRequest + "]");
		List<SimpleAlbum> albumList = new ArrayList<SimpleAlbum>();
		AlbumSearchRequest request = apiHandler.searchAlbums(searchRequest)
										.market(SpotifyConfiguration.getMarket())
										.build();

		try {
		   Page<SimpleAlbum> artistSearchResult = request.get();
		   albumList = artistSearchResult.getItems();
		} catch (Exception e) {
			log.error("An exception occured while searching for albums. Error message: " + e.getMessage());
			log.catching(Level.DEBUG, e);
		}

		return albumList;
	}
}
