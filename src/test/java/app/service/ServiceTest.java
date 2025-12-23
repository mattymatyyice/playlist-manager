package app.service;

import app.model.Playlist;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ServiceTest {

    private Service service;

    @BeforeEach
    void setup() {
        // Test mode (safe even if CSV exists)
        service = new Service(true);
    }

    @Test
    void addSong_success() {
        service.addSong("Song A", "Artist A", "Pop", 200);

        assertTrue(
                service.getAllSongs()
                        .stream()
                        .anyMatch(s -> s.getTitle().equals("Song A"))
        );
    }

    @Test
    void addPlaylist_success() {
        service.addPlaylist("My Playlist", true);

        assertTrue(
                service.getAllPlaylists()
                        .stream()
                        .anyMatch(p -> p.getName().equals("My Playlist"))
        );
    }

    @Test
    void addSongToPlaylist_success() {
        service.addSong("Song A", "Artist A", "Pop", 200);
        service.addPlaylist("My Playlist", true);

        service.addSongToPlaylistByName("My Playlist", "Song A");

        Playlist playlist = service.getAllPlaylists()
                .stream()
                .filter(p -> p.getName().equals("My Playlist"))
                .findFirst()
                .orElseThrow();

        assertTrue(
                playlist.getSongs()
                        .stream()
                        .anyMatch(s -> s.getTitle().equals("Song A"))
        );
    }

    @Test
    void addSongToPlaylist_songMissing() {
        service.addPlaylist("My Playlist", true);

        assertThrows(
                IllegalArgumentException.class,
                () -> service.addSongToPlaylistByName("My Playlist", "Missing Song")
        );
    }

    @Test
    void addSongToPlaylist_playlistMissing() {
        service.addSong("Song A", "Artist A", "Pop", 200);

        assertThrows(
                IllegalArgumentException.class,
                () -> service.addSongToPlaylistByName("Missing Playlist", "Song A")
        );
    }

    @Test
    void getSongsByArtist() {
        service.addSong("Song A", "Artist A", "Pop", 200);

        assertTrue(
                service.getSongsByArtist("Artist A")
                        .stream()
                        .anyMatch(s -> s.getTitle().equals("Song A"))
        );
    }

    @Test
    void getSongsByGenre() {
        service.addSong("Song A", "Artist A", "Pop", 200);

        assertTrue(
                service.getSongsByGenre("Pop")
                        .stream()
                        .anyMatch(s -> s.getTitle().equals("Song A"))
        );
    }

    @Test
    void personalPlaylistsOnly() {
        service.addPlaylist("Personal", true);
        service.addPlaylist("Shared", false);

        assertTrue(
                service.getPersonalPlaylists()
                        .stream()
                        .allMatch(Playlist::isPersonal)
        );
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
