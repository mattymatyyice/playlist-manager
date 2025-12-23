package app.service;

import app.dao.PlaylistDAO;
import app.dao.PlaylistDAOImpl;
import app.dao.SongDAO;
import app.dao.SongDAOImpl;
import app.model.Playlist;
import app.model.Song;

import java.util.List;
import java.util.stream.Collectors;

public class Service {

    private final SongDAO songDAO;
    private final PlaylistDAO playlistDAO;

    // =========================
    // PRODUCTION CONSTRUCTOR
    // =========================
    public Service() {
        this.songDAO = new SongDAOImpl();          // uses CSV
        this.playlistDAO = new PlaylistDAOImpl(); // uses CSV
    }

    // =========================
    // TEST CONSTRUCTOR (NO FILE IO)
    // =========================
    public Service(boolean testMode) {
        this.songDAO = new SongDAOImpl(false);
        this.playlistDAO = new PlaylistDAOImpl(false);
    }

    // =========================
    // AUTH
    // =========================
    public boolean login(int userId, String password) {
        return "password".equals(password);
    }

    // =========================
    // SONGS
    // =========================
    public void addSong(String title, String artist, String genre, int lengthSeconds) {
        songDAO.addSong(new Song(title, artist, genre, lengthSeconds));
    }

    public List<Song> getAllSongs() {
        return songDAO.getAllSongs();
    }

    public List<Song> getSongsByArtist(String artist) {
        return songDAO.getAllSongs()
                .stream()
                .filter(s -> s.getArtist().equalsIgnoreCase(artist))
                .collect(Collectors.toList());
    }

    public List<Song> getSongsByGenre(String genre) {
        return songDAO.getAllSongs()
                .stream()
                .filter(s -> s.getGenre().equalsIgnoreCase(genre))
                .collect(Collectors.toList());
    }

    // =========================
    // PLAYLISTS
    // =========================
    public void addPlaylist(String name, boolean personal) {
        playlistDAO.addPlaylist(new Playlist(name, personal));
    }

    public List<Playlist> getAllPlaylists() {
        return playlistDAO.getAllPlaylists();
    }

    public List<Playlist> getPersonalPlaylists() {
        return playlistDAO.getAllPlaylists()
                .stream()
                .filter(Playlist::isPersonal)
                .collect(Collectors.toList());
    }

    // =========================
    // MANY-TO-MANY
    // =========================
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
