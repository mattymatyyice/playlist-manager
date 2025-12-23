package app.dao;

import app.model.Playlist;
import java.util.ArrayList;
import java.util.List;

public class PlaylistDAOImpl implements PlaylistDAO {

    private List<Playlist> playlists = new ArrayList<>();
    private int idCounter = 1;

    @Override
    public void addPlaylist(String name, boolean personal) {
        playlists.add(new Playlist(idCounter++, name, personal));
    }

    @Override
    public List<Playlist> getAllPlaylists() {
        return playlists;
    }

    @Override
    public Playlist getPlaylistByName(String name) {
        for (Playlist p : playlists) {
            if (p.getName().equalsIgnoreCase(name)) {
                return p;
            }
        }
        return null;
    }
}
