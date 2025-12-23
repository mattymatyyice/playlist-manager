package app.service;

import app.model.Playlist;
import app.model.Song;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ServiceTest {

    private final Service service = new Service();

    @Test
    void addSong_success() {
        service.addSong("Song A", "Artist A", "Pop", 200);
        assertEquals(1, service.getAllSongs().size());
    }

    @Test
    void addPlaylist_success() {
        service.addPlaylist("My Playlist", true);
        assertEquals(1, service.getAllPlaylists().size());
    }

    @Test
    void addSongToPlaylist_success() {
        service.addSong("Song A", "Artist A", "Pop", 200);
        service.addPlaylist("My Playlist", true);

        service.addSongToPlaylistByName("My Playlist", "Song A");

        Playlist p = service.getAllPlaylists().get(0);
        assertEquals(1, p.getSongs().size());
    }

    @Test
    void addSongToPlaylist_songMissing() {
        service.addPlaylist("My Playlist", true);

        assertThrows(IllegalArgumentException.class, () ->
                service.addSongToPlaylistByName("My Playlist", "Missing Song")
        );
    }

    @Test
    void addSongToPlaylist_playlistMissing() {
        service.addSong("Song A", "Artist A", "Pop", 200);

        assertThrows(IllegalArgumentException.class, () ->
                service.addSongToPlaylistByName("Missing Playlist", "Song A")
        );
    }

    @Test
    void getSongsByArtist() {
        service.addSong("Song A", "Artist A", "Pop", 200);
        service.addSong("Song B", "Artist B", "Rock", 180);

        assertEquals(1, service.getSongsByArtist("Artist A").size());
    }

    @Test
    void getSongsByGenre() {
        service.addSong("Song A", "Artist A", "Pop", 200);
        service.addSong("Song B", "Artist B", "Rock", 180);

        assertEquals(1, service.getSongsByGenre("Rock").size());
    }

    @Test
    void personalPlaylistsOnly() {
        service.addPlaylist("Personal", true);
        service.addPlaylist("Shared", false);

        assertEquals(1, service.getPersonalPlaylists().size());
    }

    @Test
    void login_success() {
        assertTrue(service.login(1, "password"));
    }

    @Test
    void login_failure() {
        assertFalse(service.login(1, "wrong"));
    }
}
