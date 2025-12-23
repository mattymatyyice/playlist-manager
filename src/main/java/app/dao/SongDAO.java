package app.dao;

import app.model.Song;
import java.util.List;

public interface SongDAO {
    void addSong(Song song);
    List<Song> getAllSongs();
}
