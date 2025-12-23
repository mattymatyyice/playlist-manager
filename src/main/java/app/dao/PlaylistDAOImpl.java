package app.dao;

import app.model.Playlist;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PlaylistDAOImpl implements PlaylistDAO {

    private final List<Playlist> playlists = new ArrayList<>();
    private final boolean persist;
    private static final String FILE = "data/playlists.csv";

    // =========================
    // PRODUCTION CONSTRUCTOR
    // =========================
    public PlaylistDAOImpl() {
        this.persist = true;
        load();
    }

    // =========================
    // TEST CONSTRUCTOR (NO FILES)
    // =========================
    public PlaylistDAOImpl(boolean testMode) {
        this.persist = !testMode;
        if (this.persist) {
            load();
        }
    }

    @Override
    public void addPlaylist(Playlist playlist) {
        playlists.add(playlist);
        if (persist) {
            save();
        }
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

    // =========================
    // FILE I/O
    // =========================
    private void load() {
        File file = new File(FILE);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                playlists.add(
                        new Playlist(
                                parts[0],
                                Boolean.parseBoolean(parts[1])
                        )
                );
            }
        } catch (IOException e) {
            System.out.println("Failed to load playlists");
        }
    }

    private void save() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE))) {
            for (Playlist p : playlists) {
                pw.println(p.getName() + "," + p.isPersonal());
            }
        } catch (IOException e) {
            System.out.println("Failed to save playlists");
        }
    }
}
