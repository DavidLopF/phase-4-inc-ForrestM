package co.edu.unbosque.model;

import java.io.File;
import java.util.Collections;
import java.util.List;

/**
 * 
 * @author David López, Andres Marin, Esteban Uribe, Yensy Gonzalez
 *
 */
public abstract class PlayList {

	public static final int LOOP_NONE = 0;
	public static final int LOOP_PLAYLIST = 1;
	public static final int LOOP_SONG = 2;

	private List<File> songs;
	private int playlistPos;
	private int loopMode;
	private boolean isShuffled;

	/**
	 * Constructor de la clase PlayList
	 */
	public PlayList() {
		isShuffled = false;
		loopMode = 0;
	}
	
	/**
	 * carga las playist
	 * @return List<File
	 */

	protected abstract List<File> loadPlaylist();

	public void initPlaylist() {
		songs = loadPlaylist();
	}
	/**
	 * 
	 * @return
	 */

	public File getCurrentSong() {
		return songs.get(playlistPos);
	}

	public int getPlaylistPosition() {
		return playlistPos;
	}

	public void setPlaylistPosition(int playlistPos) {
		this.playlistPos = playlistPos;
	}

	public int getPlaylistSize() {
		return songs.size();
	}

	public boolean isFinalSong() {
		return playlistPos == songs.size() - 1;
	}

	public boolean isShuffled() {
		return isShuffled;
	}

	public int getLoopMode() {
		return loopMode;
	}

	public void toggleShuffle() {
		setShuffle(!isShuffled);
	}

	public void toggleLoop() {
		loopMode = (loopMode + 1) % 3;
	}

	public void skipForward() {
		playlistPos = (playlistPos + 1) % songs.size();

		if (playlistPos == 0 && isShuffled) {
			shuffle();
		}
	}

	public void skipBack() {
		playlistPos = playlistPos == 0 ? songs.size() - 1 : playlistPos - 1;
	}

	public void setLoop(int loopMode) {
		this.loopMode = loopMode;
	}

	public void setShuffle(boolean shuffle) {
		this.isShuffled = shuffle;
		if (isShuffled) {
			shuffle();
		} else {
			unshuffle();
		}
	}

	protected void shuffle() {
		Collections.shuffle(songs.subList(playlistPos + 1, songs.size()));
	}

	protected void unshuffle() {
		initPlaylist();
	}

}