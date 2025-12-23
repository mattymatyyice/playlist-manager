package app.service;

import app.dao.PlaylistDAO;
import app.dao.PlaylistDAOImpl;
import app.dao.SongDAO;
import app.dao.SongDAOImpl;
import app.dao.UserDAO;
import app.dao.UserDAOImpl;
import app.model.Playlist;
import app.model.Song;

import java.util.List;

public class Service {

    private final SongDAO songDAO = new SongDAOImpl();
    private final PlaylistDAO playlistDAO = new PlaylistDAOImpl();
    private final UserDAO userDAO = new UserDAOImpl();

    private int songIdCounter = 1;
    private int playlistIdCounter = 1;

    /* ===================== AUTH ===================== */

    public boolean login(int userId, String password) {
        return userDAO.login(userId, password);
    }

    /* ===================== SONGS ===================== */

    public void addSong(String title, String artist, String genre, int lengthSeconds) {
        Song song = new Song(songIdCounter++, title, artist, genre, lengthSeconds);
        songDAO.addSong(song);
    }

    public List<Song> getAllSongs() {
        return songDAO.getAllSongs();
    }

    /* ===================== PLAYLISTS ===================== */

    public void addPlaylist(String name, boolean personal) {
        Playlist playlist = new Playlist(playlistIdCounter++, name, personal);
        playlistDAO.addPlaylist(playlist);
    }

    public List<Playlist> getAllPlaylists() {
        return playlistDAO.getAllPlaylists();
    }

    public Playlist getPlaylistByName(String name) {
        return playlistDAO.getPlaylistByName(name);
    }

    /* ===================== PLAYLIST ??? SONG ===================== */

    public void addSongToPlaylistByName(String playlistName, String songTitle) {
        Playlist playlist = playlistDAO.getPlaylistByName(playlistName);
        Song song = songDAO.getSongByTitle(songTitle);

        if (playlist == null) {
            throw new IllegalArgumentException("Playlist not found");
        }
        if (song == null) {
            throw new IllegalArgumentException("Song not found");
        }

        playlist.addSong(song);
    }
}

