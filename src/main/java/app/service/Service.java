package app.service;

import app.dao.*;
import app.model.*;
import java.util.List;
import java.util.stream.Collectors;

public class Service {

    private SongDAO songDAO = new SongDAOImpl();
    private PlaylistDAO playlistDAO = new PlaylistDAOImpl();
    private UserDAO userDAO = new UserDAOImpl();

    public boolean login(int userId, String password) {
        return userDAO.login(userId, password);
    }

    public void addSong(String title, String artist, String genre, int length) {
        songDAO.addSong(title, artist, genre, length);
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

    public void addPlaylist(String name, boolean personal) {
        playlistDAO.addPlaylist(name, personal);
    }

    public List<Playlist> getAllPlaylists() {
        return playlistDAO.getAllPlaylists();
    }

    public List<Playlist> getPersonalPlaylists() {
        return playlistDAO.getAllPlaylists().stream()
            .filter(Playlist::isPersonal)
            .collect(Collectors.toList());
    }

    public void addSongToPlaylistByName(String playlistName, String songTitle) {
        Playlist p = playlistDAO.getPlaylistByName(playlistName);
        Song s = songDAO.getSongByTitle(songTitle);

        if (p != null && s != null) {
            p.addSong(s);
        }
    }
}
