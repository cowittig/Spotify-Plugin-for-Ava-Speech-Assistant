package org.ava.spotify.test;

import java.util.List;

import org.ava.spotify.SpotifyPlugin;
import org.ava.spotify.SpotifySearch;

import com.wrapper.spotify.Api;
import com.wrapper.spotify.models.Artist;
import com.wrapper.spotify.models.SimpleAlbum;
import com.wrapper.spotify.models.Track;

public class TestSpotifyPlugin {

	public static void main(String[] args) {
		SpotifyPlugin sp = new SpotifyPlugin();
		sp.start();
		
		// Create an API instance. The default instance connects to https://api.spotify.com/.
		Api api = Api.DEFAULT_API; 
		SpotifySearch ss = new SpotifySearch(api);
		
		testTrackSearch(ss);
		testArtistSearch(ss);
		testAlbumSearch(ss);
	}
	
	private static void testTrackSearch(SpotifySearch ss) {
		System.out.println("\nTesting track search");
		List<Track> result = ss.searchForTracks("The Way I Am");
		for(Track t : result) {
			System.out.println("\tArtist: " + t.getArtists().get(0).getName() + ", Title: " + t.getName());
		}
	}
	
	private static void testArtistSearch(SpotifySearch ss) {
		System.out.println("\nTesting artits search");
		List<Artist> result = ss.searchForArtists("Drake");
		for(Artist a : result) {
			System.out.println("\tArtist: " + a.getName());
		}
	}
	
	private static void testAlbumSearch(SpotifySearch ss) {
		System.out.println("\nTesting album search");
		List<SimpleAlbum> result = ss.searchForAlbums("red");
		for(SimpleAlbum a : result) {
			System.out.println("\tAlbum: " + a.getName());
		}
	}

}
