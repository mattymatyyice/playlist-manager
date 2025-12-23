package app.service;

import app.dao.*;
import app.model.*;

import java.util.List;
import java.util.stream.Collectors;

public class Service {

    private final SongDAO songDAO;
    private final PlaylistDAO playlistDAO;
    private final UserDAO userDAO;

    // ✅ DEFAULT constructor (used by App)
    public Service() {
        this.songDAO = new SongDAOImpl();
        this.playlistDAO = new PlaylistDAOImpl();
        this.userDAO = new UserDAOImpl();
    }

    // ✅ TEST constructor (used by ServiceTest)
    public Service(SongDAO songDAO, PlaylistDAO playlistDAO, UserDAO userDAO) {
        this.songDAO = songDAO;
        this.playlistDAO = playlistDAO;
        this.userDAO = userDAO;
    }

    // ---------- AUTH ----------
    public boolean login(int userId, String password) {
        return userDAO.login(userId, password);
    }

    // ---------- SONGS ----------
    public void addSong(String title, String artist, String genre, int lengthSeconds) {
        songDAO.addSong(new Song(title, artist, genre, lengthSeconds));
    }

    public List<Song> getAllSongs() {
        return songDAO.getAllSongs();
    }

    public List<Song> getSongsByArtist(String artist) {
        return songDAO.getAllSongs().stream()
                .filter(s -> s.getArtist().equalsIgnoreCase(artist))
                .collect(Collectors.toList());
    }

    public List<Song> getSongsByGenre(String genre) {
        return songDAO.getAllSongs().stream()
                .filter(s -> s.getGenre().equalsIgnoreCase(genre))
                .collect(Collectors.toList());
    }

    // ---------- PLAYLISTS ----------
    public void addPlaylist(String name, boolean personal) {
        playlistDAO.addPlaylist(new Playlist(name, personal));
    }

    public List<Playlist> getAllPlaylists() {
        return playlistDAO.getAllPlaylists();
    }

    public List<Playlist> getPersonalPlaylists() {
        return playlistDAO.getAllPlaylists().stream()
                .filter(Playlist::isPersonal)
                .collect(Collectors.toList());
    }

    // ---------- MANY-TO-MANY ----------
    public void addSongToPlaylistByName(String playlistName, String songTitle) {
        Playlist playlist = playlistDAO.getPlaylistByName(playlistName);
        if (playlist == null) {
            throw new IllegalArgumentException("Playlist not found");
        }

        Song song = songDAO.getSongByTitle(songTitle);
        if (song == null) {
            throw new IllegalArgumentException("Song not found");
        }

        playlist.addSong(song);
    }
}
