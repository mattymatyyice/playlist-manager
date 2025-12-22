package app.dao;

import app.model.Song;
import java.util.List;

public interface SongDAO {
    void addSong(String title, String artist, String genre, int lengthSeconds);
    List<Song> getAllSongs();
    Song getSongByTitle(String title);
}
