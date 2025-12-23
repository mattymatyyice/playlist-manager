package app.dao;

import app.model.Song;
import java.util.ArrayList;
import java.util.List;

public class SongDAOImpl implements SongDAO {

    private List<Song> songs = new ArrayList<>();
    private int idCounter = 1;

    @Override
    public void addSong(String title, String artist, String genre, int lengthSeconds) {
        songs.add(new Song(idCounter++, title, artist, genre, lengthSeconds));
    }

    @Override
    public List<Song> getAllSongs() {
        return songs;
    }

    @Override
    public Song getSongByTitle(String title) {
        for (Song s : songs) {
            if (s.getTitle().equalsIgnoreCase(title)) return s;
        }
        return null;
    }
}
