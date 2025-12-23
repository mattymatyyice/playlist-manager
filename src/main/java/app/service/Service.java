package app.service;

import app.dao.*;
import app.model.*;

import java.util.List;
import java.util.stream.Collectors;

public class Service {

    private final SongDAO songDAO = new SongDAOImpl();
    private final PlaylistDAO playlistDAO = new PlaylistDAOImpl();
    private final UserDAO userDAO = new UserDAOImpl();

    // ---------- AUTH ----------
    public boolean login(int userId, String password) {
        return userDAO.login(userId, password);
    }

    // ---------- SONGS ----------
    public void addSong(String title, String artist, String genre, int lengthSeconds) {
        Song song = new Song(title, artist, genre, lengthSeconds);
        songDAO.addSong(song);
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
        Playlist playlist = new Playlist(name, personal);
        playlistDAO.addPlaylist(playlist);
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

        Playlist playlist = playlistDAO.getAllPlaylists().stream()
                .filter(p -> p.getName().equalsIgnoreCase(playlistName))
                .findFirst()
                .orElse(null);

        if (playlist == null) {
            throw new IllegalArgumentException("Playlist not found");
        }

        Song song = songDAO.getAllSongs().stream()
                .filter(s -> s.getTitle().equalsIgnoreCase(songTitle))
                .findFirst()
                .orElse(null);

        if (song == null) {
            throw new IllegalArgumentException("Song not found");
        }

        playlist.addSong(song);
    }
}
