package app.dao;

import app.model.Playlist;
import java.util.ArrayList;
import java.util.List;

public class PlaylistDAOImpl implements PlaylistDAO {

    private final List<Playlist> playlists = new ArrayList<>();

    @Override
    public void addPlaylist(Playlist playlist) {
        playlists.add(playlist);
    }

    @Override
    public List<Playlist> getAllPlaylists() {
        return playlists;
    }
}
