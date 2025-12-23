package app.dao;

import app.model.Playlist;
import java.util.List;

public interface PlaylistDAO {

    void addPlaylist(Playlist playlist);

    List<Playlist> getAllPlaylists();

    Playlist getPlaylistByName(String name);
}
