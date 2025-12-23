package app.dao;

import app.model.Song;

import java.util.ArrayList;
import java.util.List;

public class SongDAOImpl implements SongDAO {

    private final List<Song> songs = new ArrayList<>();

    @Override
    public void addSong(Song song) {
        songs.add(song);
    }

    @Override
    public List<Song> getAllSongs() {
        return songs;
    }

    @Override
    public Song getSongByTitle(String title) {
        return songs.stream()
                .filter(s -> s.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .orElse(null);
    }
}
