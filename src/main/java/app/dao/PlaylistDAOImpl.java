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

    @Override
    public Playlist getPlaylistByName(String name) {
        return playlists.stream()
                .filter(p -> p.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }
}

