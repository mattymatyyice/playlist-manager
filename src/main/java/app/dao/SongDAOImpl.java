package app.dao;

import app.model.Song;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SongDAOImpl implements SongDAO {

    private final List<Song> songs = new ArrayList<>();
    private final boolean persist;
    private static final String FILE = "data/songs.csv";

    // =========================
    // PRODUCTION CONSTRUCTOR
    // =========================
    public SongDAOImpl() {
        this.persist = true;
        load();
    }

    // =========================
    // TEST CONSTRUCTOR (NO FILE IO)
    // =========================
    public SongDAOImpl(boolean testMode) {
        this.persist = !testMode;
        if (this.persist) {
            load();
        }
    }

    @Override
    public void addSong(Song song) {
        songs.add(song);
        if (persist) {
            save();
        }
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

    // =========================
    // FILE I/O
    // =========================
    private void load() {
        File file = new File(FILE);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split(",");
                songs.add(new Song(
                        p[0],
                        p[1],
                        p[2],
                        Integer.parseInt(p[3])
                ));
            }
        } catch (IOException e) {
            System.out.println("Failed to load songs");
        }
    }

    private void save() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE))) {
            for (Song s : songs) {
                pw.println(
                        s.getTitle() + "," +
                        s.getArtist() + "," +
                        s.getGenre() + "," +
                        s.getLengthSeconds()
                );
            }
        } catch (IOException e) {
            System.out.println("Failed to save songs");
        }
    }
}

