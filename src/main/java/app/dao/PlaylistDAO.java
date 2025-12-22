package app.dao;

import app.model.Playlist;
import java.util.List;

public interface PlaylistDAO {
    void addPlaylist(String name, boolean personal);
    List<Playlist> getAllPlaylists();
    Playlist getPlaylistByName(String name);
}
